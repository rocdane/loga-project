package app.controller;

import app.model.Automobile;
import app.service.manager.IManageAutomobile;
import app.service.manager.ManageAutomobileRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/automobile")
public class AutomobileController {

    IManageAutomobile manageAutomobile;

    @PostMapping("/create")
    public Automobile create(@RequestBody ManageAutomobileRequest request){
        return manageAutomobile.createAutomobile(request);
    }

    @GetMapping("/read/{id}")
    public Automobile read(@PathVariable Long id){
        return manageAutomobile.findAutomobile(id);
    }

    @GetMapping("/read/{immatriculation}")
    public Automobile read(@PathVariable String immatriculation){
        return manageAutomobile.findAutomobile(immatriculation);
    }

    @PutMapping("/edit/{id}")
    public void edit(@RequestBody ManageAutomobileRequest request, @PathVariable Long id){
        manageAutomobile.editAutomobile(request,id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        manageAutomobile.deleteAutomobile(id);
    }
}
