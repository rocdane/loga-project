package app.service.manager;

import app.dao.ClientRepository;
import app.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManageClient implements IManageClient{

    private final ClientRepository clientRepository;

    /**
     * Cette méthode permet d'enregistrer un objet Client dans la base de données. Elle retourne l'objet Client ainsi créé.
     * @param request
     * @return Client
     */
    @Override
    public Client createClient(ManageClientRequest request) {
        return clientRepository.save(new Client());
    }

    /**
     * Cette méthode permet de sélectionner les objets Client de la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Client> listClient() {
        return clientRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner les objets Client de la base de données dans une collection.
     * @return List
     */
    @Override
    public List<Client> listClient(String name) {
        return clientRepository.findAll();
    }

    /**
     * Cette méthode permet de sélectionner un objet Client de la base de données.
     * @param id
     * @return Client
     */
    @Override
    public Client findClient(long id) {
        return clientRepository.findById(id).get();
    }

    /**
     * Cette méthode permet de sélectionner un objet Client de la base de données.
     * @param name
     * @return Client
     */
    @Override
    public Client findClient(String name) {
        return clientRepository.findByName(name);
    }

    /**
     * Cette méthode permet de mettre à jour un objet Client dans la base de données.
     * @param request, id
     */
    @Override
    public void editClient(ManageClientRequest request, Long id) {

    }

    /**
     * Cette méthode permet de supprimer un objet Client de la base de données.
     * @param id
     */
    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
