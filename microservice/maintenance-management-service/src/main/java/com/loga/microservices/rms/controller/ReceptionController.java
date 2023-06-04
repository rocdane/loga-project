package com.loga.microservices.rms.controller;

import com.loga.microservices.rms.app.api.ReportServiceProxy;
import com.loga.microservices.rms.entity.Reception;
import com.loga.microservices.rms.service.IReceptionService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maintenance-service")
public class ReceptionController {

    @Autowired
    private IReceptionService repairReception;

    @Autowired
    private ReportServiceProxy reportServiceProxy;

    @PostMapping(path = "/receptions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reception> create(@RequestBody Reception reception){
        return ResponseEntity.ok(repairReception.create(reception));
    }

    @GetMapping(path = "/receptions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reception read(@PathVariable Long id){
        return repairReception.read(id);
    }

    @GetMapping(path = "/report/reception/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(HttpServletResponse response, @PathVariable Long id) {
        reportServiceProxy.produceReportById(response, "reception", id).getBody();
    }
}
