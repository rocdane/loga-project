package app.controller;

import app.model.Dossier;
import app.service.manager.IManageDossier;
import app.service.manager.ManageDossierRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/dossier")
public class DossierController {

    IManageDossier manageDossierService;

    @PostMapping("/create")
    public Dossier create(@RequestBody ManageDossierRequest request){
        return manageDossierService.createDossier(request);
    }

    @GetMapping("/read")
    public List read(){
        return manageDossierService.listDossier();
    }

    @GetMapping("/read/{id}")
    public Dossier read(@PathVariable Long id){
        return manageDossierService.findDossier(id);
    }

    @GetMapping("/read/{reference}")
    public Dossier read(@PathVariable String reference){
        return manageDossierService.findDossier(reference);
    }

    @PutMapping("/update/{dossier}")
    public void edit(@RequestBody ManageDossierRequest request, Long dossier){
        manageDossierService.editDossier(request,dossier);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        manageDossierService.deleteDossier(id);
    }
}
