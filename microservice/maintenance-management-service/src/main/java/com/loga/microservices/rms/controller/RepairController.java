package com.loga.microservices.rms.controller;

import com.loga.microservices.rms.app.api.ReportServiceProxy;
import com.loga.microservices.rms.entity.Repair;
import com.loga.microservices.rms.entity.Spare;
import com.loga.microservices.rms.entity.Task;
import com.loga.microservices.rms.service.IRepairService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/maintenance-service")
public class RepairController {

    @Autowired
    private IRepairService repairReparation;

    @Autowired
    private ReportServiceProxy reportServiceProxy;

    @PostMapping(path = "/repairs", produces = MediaType.APPLICATION_JSON_VALUE)
    public Repair create(@RequestBody Repair repair){
        return repairReparation.createRepair(repair);
    }

    @GetMapping(path = "/repairs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Repair read(@PathVariable Long id){
        return repairReparation.findRepair(id);
    }

    @GetMapping(path = "/repairs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Repair> read(){
        return repairReparation.listRepair();
    }

    @GetMapping(path = "/repairs/periods", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Repair> read(@RequestBody Date start, @RequestBody Date end){
        return repairReparation.listRepair(start,end);
    }

    @GetMapping(path = "/repairs/reference/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Repair>  read(@PathVariable("reference") String reference){
        return repairReparation.listRepair(reference);
    }

    @PutMapping(path = "/repairs/{id}")
    public void edit(@RequestBody Repair repair, @PathVariable Long id){
        repairReparation.editRepair(repair,id);
    }

    @PutMapping(path = "/repairs/order/{id}")
    public void order(@PathVariable Long id){
        repairReparation.orderRepair(id);
    }

    @DeleteMapping(path = "/repairs/{id}")
    public void delete(@PathVariable Long id){
        repairReparation.deleteRepair(id);
    }

    @PutMapping(path = "/repairs/task/{id}")
    public void editTask(@RequestBody Task task, @PathVariable Long id){
        repairReparation.editTask(task,id);
    }

    @DeleteMapping(path = "/repairs/task/{id}")
    public void deleteTask(@PathVariable Long id){
        repairReparation.deleteTask(id);
    }

    @PutMapping(path = "/repairs/spare/{id}")
    public void editSpare(@RequestBody Spare spare, @PathVariable Long id){
        repairReparation.editSpare(spare,id);
    }

    @DeleteMapping(path = "/repairs/spare/{id}")
    public void deleteSpare(@PathVariable Long id){
        repairReparation.deleteSpare(id);
    }

    @GetMapping(path = "/report/repair/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void report(HttpServletResponse response, @PathVariable Long id) {
        reportServiceProxy.produceReportById(response, "repair", id).getBody();
    }
}
