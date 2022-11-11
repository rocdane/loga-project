package vendor.io;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import vendor.security.Database;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Report
{
    private static Report report;

    private final Database ds;

    private Report() {
        this.ds = new Database();
    }

    public static Report getInstance(){
        if(report==null){
            report = new Report();
        }
        return report;
    }

    public void createReport(String source, String query) throws Exception {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(Report.class.getResourceAsStream(source));
            JRDesignQuery jrDesignQuery;
            (jrDesignQuery = new JRDesignQuery()).setText(query);
            jasperDesign.setQuery(jrDesignQuery);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, this.ds.getConnection());
            JasperViewer jasperViewer;
            (jasperViewer = new JasperViewer(jasperPrint, false)).setVisible(true);
        }
        catch (Exception e) {
            throw new Exception("Impossible de créer le rapport : \n" + e.getMessage());
        }
    }

    public void createReport(String source, int id) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("ID",id);
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(Report.class.getResourceAsStream(source));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,param,this.ds.getConnection());
            JasperViewer jasperViewer;
            (jasperViewer = new JasperViewer(jasperPrint, false)).setVisible(true);
        }
        catch (Exception e) {
            throw new Exception("Impossible de créer le rapport : \n" + e.getMessage());
        }
    }

    public void createReport(String source, Date debut, Date fin) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("DATEDEBUT",debut);
        param.put("DATEFIN",fin);
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(Report.class.getResourceAsStream(source));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,param,this.ds.getConnection());
            JasperViewer jasperViewer;
            (jasperViewer = new JasperViewer(jasperPrint, false)).setVisible(true);
        }
        catch (Exception e) {
            throw new Exception("Impossible de créer le rapport : \n" + e.getMessage());
        }
    }

    public void createReport(String source, Date date) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("EXERCICE",date);
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(Report.class.getResourceAsStream(source));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,param,this.ds.getConnection());
            JasperViewer jasperViewer;
            (jasperViewer = new JasperViewer(jasperPrint, false)).setVisible(true);
        }
        catch (Exception e) {
            throw new Exception("Impossible de créer le rapport : \n" + e.getMessage());
        }
    }
}
