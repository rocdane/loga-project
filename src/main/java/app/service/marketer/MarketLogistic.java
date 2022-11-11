package app.service.marketer;

import app.dao.ArticleRepository;
import app.dao.FurnisherRepository;
import app.dao.OrderRepository;
import app.model.Delivery;
import app.model.Furnisher;
import app.model.Order;
import app.model.Spare;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MarketLogistic implements IMarketLogistic{

    private final FurnisherRepository furnisherRepository;
    private final ArticleRepository articleRepository;
    private final OrderRepository orderRepository;

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Fournisseur dans la base de données.
     * @param furnisher
     * @return Fournisseur
     */
    @Override
    public Furnisher enregistrerFournisseur(Furnisher furnisher) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner un objet Fournisseur de la base de données.
     * @param id
     * @return Fournisseur
     */
    @Override
    public Furnisher afficherFournisseur(long id) {
        return null;
    }

    @Override
    public List<Furnisher> listFournisseur() {
        return null;
    }

    /**
     * TODO:Cette méthode permet de supprimer un objet Fournisseur de la base de données.
     * @param furnisher
     */
    @Override
    public void supprimerFournisseur(Furnisher furnisher) {
        //todo: delete fournisseur
    }

    /**
     * TODO:Cette méthode permet de sélectionner un objet Fourniture de la base de données.
     * @param id
     * @return Fourniture
     */
    @Override
    public Spare afficherFourniture(long id) {
        return null;
    }

    /**
     * TODO : Cette méthode permet de modifier un objet Fourniture dans la base de données.
     * @param spare
     */
    @Override
    public void modifierFourniture(Spare spare) {
        //todo:edit spare
    }

    @Override
    public List<Spare> listSpare() {
        return null;
    }

    /**
     * TODO : Cette méthode permet d'enregistrer un objet Commande dans la base de donnnées.
     * @param order
     * @return Commande
     * @see Spare
     */
    @Override
    public Order enregistrerCommande(Order order) {
        return null;
    }

    /**
     * TODO : Cette méthode permet de sélectionner tous les objets Commande de la base de données dans une collection.
     * @return
     */
    @Override
    public List<Order> listerCommande() {
        return null;
    }

    @Override
    public List<Order> chercherCommande(String immatriculation) {
        return null;
    }

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Livraison dans la base de données.
     * @param delivery
     * @return Livraison
     * @see Spare
     */
    @Override
    public Delivery enregistrerLivraison(Delivery delivery) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Livraison de la base de données.
     * @return List
     */
    @Override
    public List<Delivery> listerLivraison() {
        return null;
    }

}
