package app.controller;

import app.model.Diagnostic;
import app.service.repairer.IRepairDiagnostic;
import app.service.repairer.RepairDiagnosticRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/diagnostic")
@AllArgsConstructor
public class DiagnosticController {
    IRepairDiagnostic repairDiagnosticService;

    @PostMapping("/create")
    public Diagnostic create(RepairDiagnosticRequest request) {
        return repairDiagnosticService.createDiagnostic(request);
    }
}
