package com.loga.limitsservice.controller;

import com.loga.limitsservice.app.factory.Limits;
import com.loga.limitsservice.vendor.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;

    @GetMapping(path = "/limits",produces = MediaType.APPLICATION_JSON_VALUE)
    public Limits retrieveLimitsFromConfigurations(){
        return new Limits(configuration.getMaximum(),configuration.getMinimum());
    }
}
