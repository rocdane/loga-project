package app.service.manager;

import app.model.Dossier;

import java.util.List;

public interface IManageDossier {
    Dossier createDossier(ManageDossierRequest request);
    Dossier findDossier(Long id);
    Dossier findDossier(String reference);
    List<Dossier> listDossier();
    List<Dossier> listDossier(String reference);
    void editDossier(ManageDossierRequest request, Long id);
    void deleteDossier(Long id);
}
