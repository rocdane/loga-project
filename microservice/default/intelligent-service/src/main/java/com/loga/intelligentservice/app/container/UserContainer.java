package com.loga.intelligentservice.app.container;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import com.loga.intelligentservice.app.agent.UserAgent;
import org.springframework.stereotype.Component;

@Component
public class UserContainer {

    private static AgentContainer userContainer;

    public AgentContainer getInstance(){
        return userContainer;
    }

    public static void create(String name){
        try{
            AgentController agentController = userContainer.createNewAgent(name, UserAgent.class.getName(),new Object[]{});
            agentController.start();
        } catch(ControllerException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        ProfileImpl impl = new ProfileImpl(false);
        impl.setParameter(ProfileImpl.MAIN_PORT,"21090");
        impl.setParameter(ProfileImpl.CONTAINER_NAME,"USERS");
        userContainer = runtime.createAgentContainer(impl);
        create("DEFAULT_USER");
    }
}
