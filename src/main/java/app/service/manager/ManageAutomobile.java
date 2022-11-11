package app.service.manager;

import app.dao.AutomobileRepository;
import app.model.Automobile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManageAutomobile implements IManageAutomobile{

    private final AutomobileRepository automobileRepository;

    /**
     * Cette méthode permet d'enregistrer un objet Automobile dans la base de données. Elle retourne l'objet Automobile ainsi créé.
     * @param request
     * @return Automobile
     */
    @Override
    public Automobile createAutomobile(ManageAutomobileRequest request) {
        return automobileRepository.save(new Automobile());
    }

    @Override
    public List<Automobile> listAutomobile() {
        return automobileRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Automobile dans la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Automobile> listAutomobile(Long client) {
        return automobileRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner tous les objets Automobile dans la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Automobile> listAutomobile(String immatriculation) {
        return automobileRepository.findAllByImmatriculationContaining(immatriculation);
    }

    /**
     * Cette méthode permet de sélectionner un objet Automobile de la base de données.
     * @param id
     * @return Automobile
     */
    @Override
    public Automobile findAutomobile(Long id) {
        return automobileRepository.findById(id).get();
    }

    /**
     * Cette méthode permet de sélectionner un objet Automobile de la base de données.
     * @param immatriculation
     * @return Automobile
     */
    @Override
    public Automobile findAutomobile(String immatriculation) {
        return automobileRepository.findByImmatriculation(immatriculation).get();
    }

    /**
     * Cette méthode permet de mettre à jour un objet Automobile de la base de données.
     * @param id
     */
    @Override
    public void editAutomobile(ManageAutomobileRequest request, Long id) {
        //TODO : edit automobile
    }

    /**
     * Cette méthode permet de supprimer un objet Automobile de la base de données.
     * @param automobile
     */
    @Override
    public void deleteAutomobile(Long automobile) {
        automobileRepository.deleteById(automobile);
    }
}
