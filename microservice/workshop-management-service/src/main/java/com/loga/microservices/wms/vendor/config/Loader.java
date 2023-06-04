package com.loga.microservices.wms.vendor.config;

import com.loga.microservices.wms.entity.Employee;
import com.loga.microservices.wms.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Loader {

    @Autowired
    private ResourceService resourceService;

    private static final Logger log = LoggerFactory.getLogger(Loader.class);

    @Bean
    CommandLineRunner init(){

        if(resourceService.createProfile(new Employee("SABI","rochdane","rocdanesabi@gmcplus.org"))!=null)
            return args -> {
                log.info("WMS Initialization ----");
            };
        else
            return args -> {
                log.info("WMS Initialized !!!");
            };
    }
}
