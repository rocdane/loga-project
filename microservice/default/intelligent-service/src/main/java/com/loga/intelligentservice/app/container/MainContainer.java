package com.loga.intelligentservice.app.container;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;
import org.springframework.stereotype.Component;

@Component
public class MainContainer {

    public MainContainer() {

    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        Properties properties = new Properties();
        properties.setProperty(Profile.GUI,"false");
        properties.setProperty(Profile.MAIN_PORT,"21090");
        properties.setProperty(Profile.CONTAINER_NAME,"LoGA");
        ProfileImpl impl = new ProfileImpl(properties);
        AgentContainer container = runtime.createMainContainer(impl);

        try {
            container.start();
        } catch (ControllerException e) {
            e.printStackTrace();
        }
    }
}
