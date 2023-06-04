package com.loga.intelligentservice.app.container;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import com.loga.intelligentservice.app.agent.RepairerAgent;
import org.springframework.stereotype.Component;

@Component
public class RepairerContainer {

    private static AgentContainer repairerContainer;

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        ProfileImpl impl = new ProfileImpl(false);
        impl.setParameter(ProfileImpl.MAIN_PORT,"21090");
        impl.setParameter(ProfileImpl.CONTAINER_NAME, "REPAIRERS");
        repairerContainer = runtime.createAgentContainer(impl);
        createRepairer("DEFAULT_REPAIRER");
    }

    public RepairerContainer(){
    }

    public AgentContainer getInstance(){
        return repairerContainer;
    }

    public static void createRepairer(String name){
        try{
            AgentController agentController = repairerContainer.createNewAgent(name, RepairerAgent.class.getName(),new Object[]{});
            agentController.start();
        }catch(ControllerException e){
            e.printStackTrace();
        }
    }
}
