package app.container;

import app.agent.Manager;
import app.model.AuthSession;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class ManagerContainer {

    private static AgentContainer managerContainer;

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        ProfileImpl impl = new ProfileImpl(false);
        impl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        impl.setParameter(ProfileImpl.CONTAINER_NAME, "MANAGER");
        managerContainer = runtime.createAgentContainer(impl);
    }

    public ManagerContainer(){

    }

    public AgentContainer getInstance(){
        return managerContainer;
    }

    public static void createManager(AuthSession authSession, boolean flag){
        try{
            AgentController agentController = managerContainer.createNewAgent(authSession.getUser().getUsername(), Manager.class.getName(),new Object[]{flag});
            agentController.start();
        }catch(ControllerException e){
            e.printStackTrace();
        }
    }
}
