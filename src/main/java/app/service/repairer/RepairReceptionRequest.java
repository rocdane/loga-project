package app.service.repairer;

import app.model.Dossier;
import app.model.Notice;
import app.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RepairReceptionRequest {
    private final Integer compteur;
    private final String commentaire;
    private final String observation;
    private final Date dateCreation;
    private final List<Notice> notices;
    private final Profile profile;
    private final Dossier dossier;
}
