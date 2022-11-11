package app.service.repairer;

import app.model.Default;
import app.model.Dossier;
import app.model.Profile;
import app.model.Solution;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class RepairDiagnosticRequest {
    private final String description;
    private final Integer compteur;
    private final Date dateCreation;
    private final List<Default> defaults;
    private final List<Solution> solutions;
    private final Profile profile;
    private final Dossier dossier;
}
