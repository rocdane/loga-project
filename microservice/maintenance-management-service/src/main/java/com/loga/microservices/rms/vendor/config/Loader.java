package com.loga.microservices.rms.vendor.config;

import com.loga.microservices.rms.entity.Notice;
import com.loga.microservices.rms.entity.Reception;
import com.loga.microservices.rms.service.IReceptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class Loader {

    @Autowired
    private IReceptionService receptionService;

    private static final Logger log = LoggerFactory.getLogger(Loader.class);

    @Bean
    CommandLineRunner init(){

        Notice notice = new Notice();
        notice.setCode("code");
        notice.setCheckpoint("checkpoint 1001");
        notice.setStatus("OK");

        Reception reception = new Reception();
        reception.setDossier("dossier");
        reception.setDescription("Réception");
        reception.setMileage(223222);
        reception.addNotice(notice);
        reception.setReference(new SimpleDateFormat("yyyy-MM-dd").format(new Date())+reception.getDossier());

        if(receptionService.create(reception) != null) {

            return args -> {
                log.info("Maintenance Service Initialization ----");
            };
        }
        else
            return args -> {
                log.info("Maintenance Service Initialized !!!");
            };
    }
}
