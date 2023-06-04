package com.loga.microservices.wms.controller;

import com.loga.microservices.wms.entity.Workshop;
import com.loga.microservices.wms.entity.Employee;
import com.loga.microservices.wms.service.IWorkshopService;
import com.loga.microservices.wms.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping(path = "/loga/v1/workshop")
public class ResourceController {

    @Autowired
    private IResourceService manageResource;

    @Autowired
    private IWorkshopService manageGarageService;

    @PostMapping(path = "/ateliers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Workshop create(@RequestBody Workshop workshop){
        return manageGarageService.createAtelier(workshop);
    }

    @PostMapping(path = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
    Employee createProfile(@RequestBody Employee employee) {
        return manageResource.createProfile(employee);
    }

    @GetMapping(path = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Employee> listProfile() {
        return manageResource.listProfile();
    }

    @GetMapping(path = "/profiles/search/{txt}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Employee> listProfile(@PathVariable String txt) {
        return manageResource.listProfile(txt);
    }

    @GetMapping(path = "/profiles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Employee readProfile(@PathVariable Long id) {
        return manageResource.findProfile(id);
    }
    @PutMapping(path = "/profiles/{id}")
    void editProfile(@RequestBody Employee up, @PathVariable Long id) {
        manageResource.editProfile(up,id);
    }

    @DeleteMapping(path = "/profiles/{id}")
    void deleteProfile(@PathVariable Long id) {
        manageResource.deleteProfile(id);
    }
}
