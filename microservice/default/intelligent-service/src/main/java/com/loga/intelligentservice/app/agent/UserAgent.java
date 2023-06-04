package com.loga.intelligentservice.app.agent;

import com.loga.intelligentservice.app.ontology.AutoMaintenanceOntology;
import com.loga.intelligentservice.controller.IntelligenceController;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class UserAgent extends GuiAgent {

    private transient List<AID> managers, repairers, merchants;
    private final Codec codec = new SLCodec();
    private final Ontology ontology = AutoMaintenanceOntology.getInstance();

    @Override
    protected void setup() {
        super.setup();

        IntelligenceController.setAgent(this);

        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();

        addBehaviour(parallelBehaviour);

        // get all repairers every 5 seconds
        parallelBehaviour.addSubBehaviour(new TickerBehaviour(this, 5000) {

            @Override
            protected void onTick() {
                DFAgentDescription agentDesc = new DFAgentDescription();
                ServiceDescription servDesc = new ServiceDescription();
                servDesc.setType("repairer");
                agentDesc.addServices(servDesc);

                try {
                    DFAgentDescription[] resultat = DFService.search(this.myAgent, agentDesc);
                    repairers = new ArrayList<>();
                    for (DFAgentDescription dfAgentDescription:resultat
                         ) {
                        repairers.add(dfAgentDescription.getName());
                    }
                } catch (FIPAException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        // get all managers every 5 seconds
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

                    for (DFAgentDescription dfAgentDescription:resultat
                    ) {
                        managers.add(dfAgentDescription.getName());
                    }
                } catch (FIPAException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        // get all merchants every 5 seconds
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
                    for (DFAgentDescription dfAgentDescription:resultat) {
                        merchants.add(dfAgentDescription.getName());
                    }
                } catch (FIPAException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        // Waiting for information from other AGENT
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour(){
            @Override
            public void action(){
                MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                ACLMessage information = receive(template);

                if (information!=null){
                    String[] message = information.getContent().split("\n");
                    for (String str:message) {
                        System.out.println(str);
                        // TODO : ReceptionController.addDysfunction(str);
                    }
                }else
                    block();
            }
        });

        // Waiting for propose from other AGENT
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour(){
            @Override
            public void action(){
                MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.PROPOSE);
                ACLMessage information = receive(template);

                if (information!=null){
                    String[] message = information.getContent().split("\n");
                    for (String str:message) {
                        //TODO : ReceptionController.addMaintenance(str);
                        System.out.println(str);
                    }
                }else
                    block();
            }
        });
    }

    @SneakyThrows
    @Override
    public void onGuiEvent(GuiEvent guiEvent) {

        //TODO : organize interaction according to USER action

        switch (guiEvent.getType()) {
            case 1 -> {
                // Prepare request
                ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                request.setOntology("Auto-Maintenance-Ontology");
                request.setContent((String) guiEvent.getParameter(0));
                // Send request
                send(request);
            }
            case 2 ->{
                ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                cfp.setContent((String) guiEvent.getParameter(0));
                send(cfp);
            }
        }
    }
}
