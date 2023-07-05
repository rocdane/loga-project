package com.loga.microservices.rms.controller;

import com.loga.microservices.rms.app.api.CustomerServiceProxy;
import com.loga.microservices.rms.app.api.IntelligentServiceProxy;
import com.loga.microservices.rms.app.api.ReportServiceProxy;
import com.loga.microservices.rms.app.factory.Dossier;
import com.loga.microservices.rms.entity.Diagnosis;
import com.loga.microservices.rms.service.IDiagnosisService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-service")
public class DiagnosisController {

    private final Logger logger = LoggerFactory.getLogger(DiagnosisController.class);

    @Autowired
    private IDiagnosisService repairDiagnostic;

    @Autowired
    private ReportServiceProxy reportService;

    @Autowired
    private CustomerServiceProxy customerProxy;

    @Autowired
    private IntelligentServiceProxy intelligentServiceProxy;

    @GetMapping(path = "/resolve/{words}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Object> processDiagnosis(@PathVariable String words){
        return intelligentServiceProxy.processDiagnosis(words);
    }

    @PostMapping(path = "/diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diagnosis> create(@RequestBody Diagnosis diagnosis) {
        Diagnosis created = repairDiagnostic.create(diagnosis);
        return ResponseEntity.ok(created);
    }

    @GetMapping(path = "/diagnosis/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Diagnosis read(@PathVariable Long id) {
        return repairDiagnostic.read(id);
    }

    @GetMapping(path = "/report/diagnosis/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(HttpServletResponse response, @PathVariable Long id) {
        reportService.produceReportById(response, "diagnosis", id).getBody();
    }

    @GetMapping(path = "/diagnosis/dossiers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CircuitBreaker(name = "customer-api", fallbackMethod = "fallBackResponse")
    public ResponseEntity<Dossier> retrieveDossier(@PathVariable Long id){
        logger.info("call customer api !!!");
        Dossier dossier = customerProxy.retrieveDossier(id);
        return ResponseEntity.ok(dossier);
    }

    public String fallBackResponse(Exception ex){
        return "Fall Back response : "+ex.getMessage();
    }
}
