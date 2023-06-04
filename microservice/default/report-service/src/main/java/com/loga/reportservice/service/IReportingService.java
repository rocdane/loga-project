package com.loga.reportservice.service;

import net.sf.jasperreports.engine.JasperPrint;

import java.util.Date;

public interface IReportingService {
    JasperPrint modelReport(String report);
    JasperPrint modelReportById(String report, Long id);
    JasperPrint modelReportByDate(String report, Date date);
    JasperPrint modelReportBetweenDate(String report, Date start, Date end);
}
