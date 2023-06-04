package com.loga.microservices.sms.app.container;

import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import startup.loga.app.agent.MerchantAgent;

public class MerchantContainer {

    private static AgentContainer marketerContainer;

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        ProfileImpl impl = new ProfileImpl(false);
        impl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        impl.setParameter(ProfileImpl.CONTAINER_NAME, "MERCHANT");
        marketerContainer = runtime.createAgentContainer(impl);
        createMarketer("DEFAULT_MERCHANT");
    }

    public MerchantContainer(){
    }

    public AgentContainer getInstance(){
        return marketerContainer;
    }

    public static void createMarketer(String name){
        try{
            AgentController agentController = marketerContainer.createNewAgent(name, MerchantAgent.class.getName(),new Object[]{});
            agentController.start();
        }catch(ControllerException e){
            e.printStackTrace();
        }
    }
}
