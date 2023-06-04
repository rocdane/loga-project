package com.loga.microservices.wms.app.container;

import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import startup.loga.app.agent.ManagerAgent;

public class ManagerContainer {

    private static AgentContainer managerContainer;

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        ProfileImpl impl = new ProfileImpl(false);
        impl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        impl.setParameter(ProfileImpl.CONTAINER_NAME, "MANAGER");
        managerContainer = runtime.createAgentContainer(impl);
        createManager("DEFAULT_MANAGER");
    }

    public ManagerContainer(){
    }

    public AgentContainer getInstance(){
        return managerContainer;
    }

    public static void createManager(String name){
        try{
            AgentController agentController = managerContainer.createNewAgent(name, ManagerAgent.class.getName(),new Object[]{});
            agentController.start();
        }catch(ControllerException e){
            e.printStackTrace();
        }
    }

    public static String getManager(String name) throws ControllerException {
        return managerContainer.getAgent(name).getName();
    }
}
