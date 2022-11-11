package app.service.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ManageClientRequest {
    private final String raisonSociale;
    private final String type;
    private final String mentionLegale;
    private final String adresse;
    private final String ville;
    private final String contact;
}
