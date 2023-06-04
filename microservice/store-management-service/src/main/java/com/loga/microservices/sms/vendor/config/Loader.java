package com.loga.microservices.sms.vendor.config;

import com.loga.microservices.sms.entity.Product;
import com.loga.microservices.sms.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Loader {

    @Autowired
    private IProductService productService;

    private static final Logger log = LoggerFactory.getLogger(Loader.class);

    @Bean
    CommandLineRunner init() {
        if(productService.create(new Product("produit de référence"))!=null)
            return args -> {
                log.info("SMS Initialization ----");
            };
        else
            return args -> {
                log.info("SMS Initialized !!!");
            };
    }
}

