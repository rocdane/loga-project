package com.loga.intelligentservice.app.container;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import com.loga.intelligentservice.app.agent.ManagerAgent;
import org.springframework.stereotype.Component;

@Component
public class ManagerContainer {

    private static AgentContainer managerContainer;

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        ProfileImpl impl = new ProfileImpl(false);
        impl.setParameter(ProfileImpl.MAIN_PORT,"21090");
        impl.setParameter(ProfileImpl.CONTAINER_NAME, "REPAIRERS");
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
