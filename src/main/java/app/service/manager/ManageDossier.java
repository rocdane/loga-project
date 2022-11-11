package app.service.manager;

import app.dao.DossierRepository;
import app.model.Automobile;
import app.model.Dossier;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManageDossier implements IManageDossier{

    private final DossierRepository dossierRepository;

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Dossier dans la base de données.
     * @param request
     * @return Dossier
     * @see Automobile , Client
     */
    @Override
    public Dossier createDossier(ManageDossierRequest request) {
        //TODO : create new dossier
        return dossierRepository.save(new Dossier());
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Dossier de la base de données.
     * @return List
     */
    @Override
    public List<Dossier> listDossier() {
        return dossierRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Dossier de la base de données.
     * @param immatriculation
     * @return List
     */
    @Override
    public List<Dossier> listDossier(String immatriculation) {
        return dossierRepository.findDossierByImmatriculationNamedParams(immatriculation);
    }

    /**
     * Cette méthode permet de sélectionner un objet Dossier de la base de données.
     * @param id
     * @return Dossier
     */
    @Override
    public Dossier findDossier(Long id) {
        return dossierRepository.findById(id).get();
    }

    public Dossier findDossier(String reference){
        return dossierRepository.findByReference(reference);
    }

    public void editDossier(ManageDossierRequest request, Long id){
        //TODO : update dossier
    }

    /**
     * Cette méthode permet de supprimer un objet Dossier de la base de données.
     * @param dossier
     */
    @Override
    public void deleteDossier(Long dossier) {
        dossierRepository.deleteById(dossier);
    }
}
