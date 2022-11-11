package app.service.repairer;

import app.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RepairReparationRequest {
    private final String reference;
    private final String description;
    private final Integer compteur;
    private final Date dateCreation;
    private final List<Reparation> reparations;
    private final List<Spare> spares;
    private final Dossier dossier;
    private final Profile profile;
    private final Boolean approved;
    private final Boolean billed;
}
