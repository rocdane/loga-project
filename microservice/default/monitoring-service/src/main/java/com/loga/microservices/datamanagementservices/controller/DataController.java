package com.loga.microservices.datamanagementservices.controller;

import com.loga.microservices.datamanagementservices.service.etl.IEtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/monitoring-service")
public class DataController {

    @Autowired
    private IEtlService etlService;

    @PutMapping(path = "/load-data/all")
    public void loadAllData(){
    }

    @PutMapping(path = "/load-data/customers")
    public void loadCustomerData(){
    }

    @PutMapping(path = "/load-data/maintenance")
    public void loadMaintenanceData(){
    }
}
