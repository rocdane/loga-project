package com.loga.microservices.sms.app.agent;

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
import lombok.SneakyThrows;
import startup.loga.app.ontology.AutoMaintenanceOntology;

import java.util.ArrayList;
import java.util.List;

public class MerchantAgent extends Agent {

    private transient List<AID> merchants,managers;
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
        serviceDesc.setType("MERCHANTS");
        serviceDesc.setName("MERCHANT");

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
                    for (DFAgentDescription dfAgentDescription:resultat
                         ) {
                        managers.add(dfAgentDescription.getName());
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
         * waiting for repair REQUEST
         */
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @SneakyThrows
            @Override
            public void action() {
                //TODO : 4 - analyse repair request from REPAIRER
                MessageTemplate message = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);

                ACLMessage request = receive(message);

                if (request != null){
                    Long repair = (Long) request.getContentObject();

                    System.out.println(request.getContent() + " - " + repair);

                    // TODO : send order CFP to all merchant

                    // TODO : create repair report & send email with report attachment
                }else
                    block();
            }
        });

        /**
         * waiting for order CFP
         */
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                //TODO: 5 - analyse order and send reply with PROPOSAL
            }
        });

        /**
         * waiting for manager accept or reject proposal
         */
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                //TODO : 7 - analyse order's decision 'REJECT' or 'ACCEPT'
                // if accept then send delivery request else send information
            }
        });

        /**
         * waiting for 'accept' or 'reject' proposal
         */
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                //TODO : 8 - switch case send delivery information
                MessageTemplate message = MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL),
                        MessageTemplate.MatchPerformative(ACLMessage.REJECT_PROPOSAL));
                ACLMessage response = receive(message);
                if (response!=null){
                    System.out.println(response.getContent());
                }else
                    block();
            }
        });

        /**
         * waiting for manager information
         */
        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                MessageTemplate message = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                ACLMessage information = receive(message);
                if (information!=null){
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
