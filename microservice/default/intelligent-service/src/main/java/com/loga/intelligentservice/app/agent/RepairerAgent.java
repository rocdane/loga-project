package com.loga.intelligentservice.app.agent;

import com.loga.intelligentservice.app.ontology.concept.*;
import com.loga.intelligentservice.app.ontology.predicate.Elabore;
import com.loga.intelligentservice.service.Diagnosis;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.MessageQueue;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.SneakyThrows;
import com.loga.intelligentservice.app.ontology.AutoMaintenanceOntology;
import com.loga.intelligentservice.app.ontology.JenaAPI;

import java.util.ArrayList;
import java.util.List;

public class RepairerAgent extends Agent {

    private List<AID> repairers, managers;
    private final Codec codec = new SLCodec();
    private final Ontology ontology = AutoMaintenanceOntology.getInstance();

    @Override
    protected void setup() {
        super.setup();

        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);

        DFAgentDescription agentDescription = new DFAgentDescription();
        agentDescription.setName(getAID());

        ServiceDescription serviceDesc = new ServiceDescription();
        serviceDesc.setType("REPAIRERS");
        serviceDesc.setName("REPAIRER");

        agentDescription.addServices(serviceDesc);

        try {
            DFService.register(this, agentDescription);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();

        addBehaviour(parallelBehaviour);

        /**
         * get all manager Agents every 5 seconds
         */
        parallelBehaviour.addSubBehaviour(new TickerBehaviour(this, 5000) {

            @Override
            protected void onTick() {

                DFAgentDescription agentDesc = new DFAgentDescription();
                ServiceDescription servDesc = new ServiceDescription();
                servDesc.setType("MANAGERS");
                agentDesc.addServices(servDesc);
                managers = new ArrayList<>();

                try {
                    DFAgentDescription[] resultat = DFService.search(this.myAgent, agentDesc);

                    for (DFAgentDescription dfAgentDescription : resultat) {
                        managers.add(dfAgentDescription.getName());
                    }
                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * get all repairer Agent every 5 seconds
         */
        parallelBehaviour.addSubBehaviour(new TickerBehaviour(this, 5000) {

            @Override
            protected void onTick() {

                DFAgentDescription agentDesc = new DFAgentDescription();
                ServiceDescription servDesc = new ServiceDescription();
                servDesc.setType("REPAIRERS");
                agentDesc.addServices(servDesc);
                repairers = new ArrayList<>();
                try {
                    DFAgentDescription[] resultat = DFService.search(this.myAgent, agentDesc);

                    for (DFAgentDescription dfAgentDescription : resultat) {
                        repairers.add(dfAgentDescription.getName());
                    }
                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * get all manager Agent every 5 seconds
         */
        parallelBehaviour.addSubBehaviour(new TickerBehaviour(this, 5000) {

            @Override
            protected void onTick() {

                DFAgentDescription agentDesc = new DFAgentDescription();
                ServiceDescription servDesc = new ServiceDescription();
                servDesc.setType("MANAGERS");
                agentDesc.addServices(servDesc);
                managers = new ArrayList<>();
                try {
                    DFAgentDescription[] resultat = DFService.search(this.myAgent, agentDesc);

                    for (DFAgentDescription dfAgentDescription : resultat) {
                        managers.add(dfAgentDescription.getName());
                    }
                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * waiting for dysfunction QUERY
         */
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @SneakyThrows
            @Override
            public void action() {
                // Receive and process diagnosis's REQUEST
                MessageTemplate message = MessageTemplate.and(
                        MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
                        MessageTemplate.MatchOntology(ontology.getName()));

                ACLMessage request = receive(message);

                if (request != null){

                    List<Diagnosis> diagnoses = JenaAPI.getInstance().query(request.getContent());

                    ACLMessage inform = request.createReply();

                    if(diagnoses.isEmpty()){
                        inform.setContent("Information indisponible");
                    }else {

                        jade.util.leap.List maintenances = new jade.util.leap.ArrayList();

                        for (Diagnosis diagnosis:diagnoses){
                            Dysfonctionnement dysfunction = new Dysfonctionnement();
                            dysfunction.setTitre(diagnosis.getDysfunction());
                            Maintenance maintenance = new Maintenance();
                            maintenance.setDysfonctionnement(dysfunction);
                            maintenance.setAction(diagnosis.getMaintenance());
                            maintenances.add(maintenance);
                        }

                        Diagnostic diagnostic = new Diagnostic();
                        diagnostic.setMaintenances(maintenances);

                        Elabore elabore = new Elabore();
                        elabore.setDiagnostic(diagnostic);

                        getContentManager().fillContent(inform,elabore);
                    }
                    send(inform);
                }else
                    block();
            }

        });

        /**
         * Receive and process diagnosis maintenance Call For Proposal(CFP)
         */
        parallelBehaviour.addSubBehaviour(new OneShotBehaviour() {

            @Override
            public void action() {
                MessageTemplate message = MessageTemplate.and(
                        MessageTemplate.MatchPerformative(ACLMessage.CFP),
                        MessageTemplate.MatchOntology(ontology.getName()));

                try {
                    ACLMessage cfp = receive(message);

                    jade.util.leap.List maintenances = new jade.util.leap.ArrayList();

                    List<Diagnosis> diagnoses = JenaAPI.getInstance().query(cfp.getContent());

                    Dysfonctionnement dysfonctionnement = new Dysfonctionnement();
                    dysfonctionnement.setSymptome(cfp.getContent());

                    Maintenance maintenance = new Maintenance();
                    maintenance.setDysfonctionnement(dysfonctionnement);

                    maintenances.add(maintenance);

                    Diagnostic diagnostic = new Diagnostic();
                    diagnostic.setMaintenances(maintenances);

                    Elabore elabore = new Elabore();
                    elabore.setReparateur(getAID());
                    elabore.setDiagnostic(diagnostic);

                    for (AID repairer:repairers) {
                        cfp.addReceiver(repairer);
                    }

                    ACLMessage proposal = new ACLMessage(ACLMessage.PROPOSE);
                    getContentManager().fillContent(proposal,elabore);
                    send(proposal);

                } catch (Codec.CodecException | OntologyException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        });

        /**
         * waiting for repair information from MANAGER
         */
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {

            @Override
            public void action() {
                MessageTemplate message = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                ACLMessage information = receive(message);
                if (information!=null){
                    //TODO : 11 - analyse repair information and inform USER
                    System.out.println(information.getContent());
                }else
                    block();
            }
        });
    }

    @Override
    protected MessageQueue createMessageQueue() {
        return super.createMessageQueue();
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        System.out.println(this.getName()+" : terminated");
    }

    @Override
    protected void beforeMove() {
        super.beforeMove();
        System.out.println(this.getName()+" : migration");
    }

    @Override
    protected void afterMove() {
        super.afterMove();
        System.out.println(this.getName()+" : migrated");
    }
}