package app.controller;

import app.model.Client;
import app.service.manager.IManageClient;
import app.service.manager.ManageClientRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/client")
public class ClientController {

    IManageClient manageClientService;

    @PostMapping("/create")
    public Client create(@RequestBody ManageClientRequest request){
        return manageClientService.createClient(request);
    }

    @GetMapping("/read/{id}")
    public Client find(@PathVariable Long id){
        return manageClientService.findClient(id);
    }

    @GetMapping("/read/{name}")
    public Client find(@PathVariable String name){
        return manageClientService.findClient(name);
    }

    @GetMapping("/read")
    public List list(){
        return manageClientService.listClient();
    }

    @GetMapping("/read/{name}")
    public List list(@PathVariable String name){
        return manageClientService.listClient(name);
    }

    @PutMapping("/edit/{id}")
    public void edit(@RequestBody ManageClientRequest request, @PathVariable Long id){
        manageClientService.editClient(request,id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        manageClientService.deleteClient(id);
    }
}
