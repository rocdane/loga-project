package com.loga.reportservice.vendor.config;

import com.loga.reportservice.service.IReportingService;
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
    private IReportingService reportingService;

    @Bean
    CommandLineRunner init() {
        return  args -> {
            log.info("Report Service initialization --- ");
        };
    }
}
