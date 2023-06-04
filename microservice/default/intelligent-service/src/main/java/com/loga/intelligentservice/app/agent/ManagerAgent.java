package com.loga.intelligentservice.app.agent;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.MessageQueue;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import com.loga.intelligentservice.app.ontology.AutoMaintenanceOntology;

import java.util.ArrayList;
import java.util.List;

public class ManagerAgent extends Agent {

    private transient List<AID> managers, repairers, merchants;
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
        serviceDesc.setType("MANAGERS");
        serviceDesc.setName("MANAGER");

        agentDescription.addServices(serviceDesc);

        try {
            DFService.register(this, agentDescription);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();

        addBehaviour(parallelBehaviour);

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

                try {
                    DFAgentDescription[] resultat = DFService.search(this.myAgent, agentDesc);
                    managers = new ArrayList<>();
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

                try {
                    DFAgentDescription[] resultat = DFService.search(this.myAgent, agentDesc);
                    repairers = new ArrayList<>();
                    for (DFAgentDescription dfAgentDescription : resultat) {
                        repairers.add(dfAgentDescription.getName());
                    }
                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * get all merchant Agent every 5 seconds
         */
        parallelBehaviour.addSubBehaviour(new TickerBehaviour(this, 5000) {

            @Override
            protected void onTick() {

                DFAgentDescription agentDesc = new DFAgentDescription();
                ServiceDescription servDesc = new ServiceDescription();
                servDesc.setType("MERCHANTS");
                agentDesc.addServices(servDesc);

                try {
                    DFAgentDescription[] resultat = DFService.search(this.myAgent, agentDesc);
                    merchants = new ArrayList<>();
                    for (DFAgentDescription dfAgentDescription : resultat) {
                        merchants.add(dfAgentDescription.getName());
                    }

                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * waiting for reception REQUEST
         */
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                MessageTemplate message = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
                ACLMessage request = receive(message);

                if (request != null){
                    String reception_id = request.getContent();
                    // todo : create reception report & send email with report attachment
                    ACLMessage reply = request.createReply();
                    reply.setContent("Rapport de réception : "+reception_id+" bien établit !!!");
                    send(reply);
                }else
                    block();
            }
        });

        /**
         * waiting for merchant order's PROPOSAL
         */
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                //TODO : 6 - analyse order's proposal from MERCHANT and reply with ACCEPT OR REJECT
            }
        });

        /**
         * waiting for merchant information
         */
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                MessageTemplate message = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                ACLMessage information = receive(message);
                if (information!=null){
                    //TODO : 10 - analyse order's proposal from MERCHANT and inform repairer
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
        try {
            DFService.deregister(this);
            System.out.println(this.getName()+" : terminated");
        } catch (FIPAException e) {
            e.printStackTrace();
        }
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
