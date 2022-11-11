package app.service.manager;

import app.model.Automobile;
import app.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ManageDossierRequest {
    private final String reference;
    private final Client client;
    private final Automobile automobile;
}
