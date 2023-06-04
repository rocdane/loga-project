package com.loga.reportservice.controller;

import com.loga.reportservice.service.IReportingService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(path = "/report-service")
public class ReportingController {

    @Autowired
    private IReportingService reportService;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @GetMapping(value = "/{src}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(HttpServletResponse response,
                       @PathVariable("src") String src) throws IOException, JRException {
        JasperPrint jasperPrint = reportService.modelReport(src);
        export(jasperPrint,response);
    }

    @GetMapping(value = "/{src}/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void reportById(HttpServletResponse response, @PathVariable("src") String src,
                           @PathVariable("id") Long id) throws IOException, JRException {
        JasperPrint jasperPrint = reportService.modelReportById(src,id);
        export(jasperPrint,response);
    }

    @GetMapping(value = "/{src}/date", produces = MediaType.APPLICATION_PDF_VALUE)
    public void reportByDate(HttpServletResponse response, @PathVariable("src") String src,
                                               @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) throws IOException, JRException {
        JasperPrint jasperPrint = reportService.modelReportByDate(src,date);
        export(jasperPrint,response);
    }

    @GetMapping(value = "/{src}/period", produces = MediaType.APPLICATION_PDF_VALUE)
    public void reportBetweenDate(HttpServletResponse response, @PathVariable("src") String src,
                                                    @RequestParam(name = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
                                                    @RequestParam(name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) throws IOException, JRException {
        JasperPrint jasperPrint = reportService.modelReportBetweenDate(src,start,end);
        export(jasperPrint,response);
    }

    public void export(JasperPrint jasperPrint, HttpServletResponse response) throws IOException, JRException {
        String path = "report_"+jasperPrint.getName()+"_"+sdf.format(new Date());
        System.out.println(path);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename="+path+".pdf");
    }
}
