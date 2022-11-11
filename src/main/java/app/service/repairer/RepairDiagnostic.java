package app.service.repairer;

import app.dao.DiagnosticRepository;
import app.model.Default;
import app.model.Diagnostic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RepairDiagnostic implements IRepairDiagnostic{

    private final DiagnosticRepository diagnosticRepository;

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Diagnostic dans la base de données.
     * @param request
     * @return Diagnostic
     * @see Default , Solution
     */
    @Override
    public Diagnostic createDiagnostic(RepairDiagnosticRequest request) {
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setDescription(request.getDescription());
        diagnostic.setCompteur(request.getCompteur());
        diagnostic.setProfile(request.getProfile());
        diagnostic.setDossier(request.getDossier());
        diagnostic.setDefaults(request.getDefaults());
        diagnostic.setSolutions(request.getSolutions());
        return diagnosticRepository.save(new Diagnostic());
    }
}
