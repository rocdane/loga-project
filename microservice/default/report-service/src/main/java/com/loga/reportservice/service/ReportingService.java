package com.loga.reportservice.service;

import com.loga.reportservice.vendor.io.Database;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportingService implements IReportingService {

    @Override
    public JasperPrint modelReport(String report) {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/jrxml/"+report+".jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            return JasperFillManager.fillReport(jasperReport,null, Database.getInstance().getConnection());
        } catch (JRException e) {
            e.printStackTrace();
            throw new RuntimeException("Echec de production du rapport : "+e.getMessage());
        }
    }

    @Override
    public JasperPrint modelReportById(String report, Long id) {
        Map<String, Object> param = new HashMap<>();
        param.put("ID",id);
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/jrxml/"+report+".jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            return JasperFillManager.fillReport(jasperReport,param,Database.getInstance().getConnection());
        } catch (JRException e) {
            e.printStackTrace();
            throw new RuntimeException("Echec de production du rapport : "+e.getMessage());
        }
    }

    @Override
    public JasperPrint modelReportByDate(String report, Date date) {
        Map<String, Object> param = new HashMap<>();
        param.put("EXERCICE",date);
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/jrxml/"+report+".jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            return JasperFillManager.fillReport(jasperReport,param, Database.getInstance().getConnection());
        } catch (JRException e) {
            e.printStackTrace();
            throw new RuntimeException("Echec de production du rapport : "+e.getMessage());
        }
    }

    @Override
    public JasperPrint modelReportBetweenDate(String report, Date debut, Date fin) {
        Map<String, Object> param = new HashMap<>();
        param.put("DATEDEBUT",debut);
        param.put("DATEFIN",fin);
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/jrxml/"+report+".jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            return JasperFillManager.fillReport(jasperReport,param, Database.getInstance().getConnection());
        } catch (JRException e) {
            e.printStackTrace();
            throw new RuntimeException("Echec de production du rapport : "+e.getMessage());
        }
    }
}
