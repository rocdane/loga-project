package app.container;

import app.agent.Repairer;
import app.model.AuthSession;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class RepairerContainer {

    private static AgentContainer repairerContainer;

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        ProfileImpl impl = new ProfileImpl(false);
        impl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        impl.setParameter(ProfileImpl.CONTAINER_NAME, "REPAIRER");
        repairerContainer = runtime.createAgentContainer(impl);
    }

    public RepairerContainer(){

    }

    public AgentContainer getInstance(){
        return repairerContainer;
    }

    public static void createRepairer(AuthSession authSession, boolean flag){
        try{
            AgentController agentController = repairerContainer.createNewAgent(authSession.getUser().getUsername(), Repairer.class.getName(),new Object[]{flag});
            agentController.start();
        }catch(ControllerException e){
            e.printStackTrace();
        }
    }
}
