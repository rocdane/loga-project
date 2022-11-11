package app.service.manager;

import app.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ManageGarageRequest {
    private String raisonSociale;
    private String mentionLegale;
    private String adresse;
    private String contact;
    private List<Space> spaces;
    private List<Department> departments;
    private List<Finance> finances;
    private List<Article> articles;
    private List<Immobility> immobilities;
}
