package app.controller;

import app.model.Atelier;
import app.service.manager.IManageGarage;
import app.service.manager.ManageGarageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/garage")
public class GarageController {

    IManageGarage manageGarageService;

    @GetMapping("/create")
    public Atelier create(@RequestBody ManageGarageRequest request){
        return manageGarageService.createAtelier(request);
    }
}
