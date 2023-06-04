package com.loga.microservices.datamanagementservices.vendor.config;

import com.loga.microservices.datamanagementservices.service.etl.IEtlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Loader {

    private static final Logger log = LoggerFactory.getLogger(Loader.class);

    @Autowired
    private IEtlService etlService;

    @Bean
    CommandLineRunner init() {
        return  args -> {
            log.info("DMS initialization --- ");
        };
    }
}
