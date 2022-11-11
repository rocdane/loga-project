package app.service.repairer;

import app.model.Diagnostic;

public interface IRepairDiagnostic {

    Diagnostic createDiagnostic(RepairDiagnosticRequest request);
}
