package com.loga.microservices.rms.app.api;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@FeignClient("gateway-server")
public interface ReportServiceProxy {

    @GetMapping(value = "/report-service/{src}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void produceReport(HttpServletResponse response, @PathVariable("src") String src);

    @GetMapping(value = "/report-service/{src}/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity produceReportById(HttpServletResponse response, @PathVariable("src") String src, @PathVariable("id") Long id) ;

    @GetMapping(value = "/report-service/{src}/date", produces = MediaType.APPLICATION_PDF_VALUE)
    public void produceReportByDate(HttpServletResponse response, @PathVariable("src") String src, @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) ;

    @GetMapping(value = "/report-service/{src}/period", produces = MediaType.APPLICATION_PDF_VALUE)
    public void produceReportBetweenDate(HttpServletResponse response, @PathVariable("src") String src, @RequestParam(name = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
                                  @RequestParam(name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end);
}