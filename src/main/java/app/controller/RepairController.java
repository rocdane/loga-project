package app.controller;

import app.model.Repair;
import app.service.repairer.IRepairReparation;
import app.service.repairer.RepairReparationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/v1/repair")
public class RepairController {
    IRepairReparation repairReparation;

    @PostMapping("/create")
    public Repair create(@RequestBody RepairReparationRequest request){
        return repairReparation.createRepair(request);
    }

    @GetMapping("/read/{id}")
    public Repair read(@PathVariable Long id){
        return repairReparation.findRepair(id);
    }

    @GetMapping("/read")
    public List read(){
        return repairReparation.listRepair();
    }

    @GetMapping("/read/{ref}")
    public List read(@PathVariable String ref){
        return repairReparation.listRepair(ref);
    }

    @PutMapping("/edit/{id}")
    public void edit(@RequestBody RepairReparationRequest request, @PathVariable Long id){
        repairReparation.editRepair(request, id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(Long id){
        repairReparation.deleteRepair(id);
    }
}
