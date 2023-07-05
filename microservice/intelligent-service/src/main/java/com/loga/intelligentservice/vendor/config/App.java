package com.loga.intelligentservice.vendor.config;

import com.loga.intelligentservice.service.IIntelligenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.loga.intelligentservice.app.api.JenaAPI;

@Configuration
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Autowired
    private IIntelligenceService intelligenceService;

    @Bean
    CommandLineRunner init(){
        return  args -> {
            LOGGER.info("Ontology : "+ JenaAPI.getInstance().getURI());
        };
    }
}
