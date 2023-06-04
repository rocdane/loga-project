package com.loga.namingserver.vendor.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Loader {

    private static final Logger log = LoggerFactory.getLogger(Loader.class);

    @Bean
    CommandLineRunner init() {
        return  args -> {
            log.info("LoGA Naming Server Initialization ---");
        };
    }
}
