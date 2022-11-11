package app.controller;

import app.model.Reception;
import app.service.repairer.IRepairReception;
import app.service.repairer.RepairReceptionRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/reception")
@AllArgsConstructor
public class ReceptionController {

    IRepairReception repairReception;

    @PostMapping("/create")
    public Reception create(RepairReceptionRequest request){
        return repairReception.createReception(request);
    }
}
