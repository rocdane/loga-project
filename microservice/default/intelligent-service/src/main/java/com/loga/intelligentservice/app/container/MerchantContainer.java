package com.loga.intelligentservice.app.container;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import com.loga.intelligentservice.app.agent.MerchantAgent;
import org.springframework.stereotype.Component;

@Component
public class MerchantContainer {

    private static AgentContainer marketerContainer;

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        ProfileImpl impl = new ProfileImpl(false);
        impl.setParameter(ProfileImpl.MAIN_PORT,"21090");
        impl.setParameter(ProfileImpl.CONTAINER_NAME, "MERCHANTS");
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
