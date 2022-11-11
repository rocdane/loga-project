package app.service.marketer;

import app.model.*;

import java.util.List;

public interface IMarketLogistic {

    /**
     * Regroupement des opérations de logistique
     * */
    Furnisher enregistrerFournisseur(Furnisher furnisher);
    Furnisher afficherFournisseur(long id);
    List<Furnisher> listFournisseur();
    void supprimerFournisseur(Furnisher furnisher);
    Spare afficherFourniture(long id);
    void modifierFourniture(Spare spare);
    List<Spare> listSpare();
    Order enregistrerCommande(Order order);
    List<Order> listerCommande();
    List<Order> chercherCommande(String immatriculation);
    Delivery enregistrerLivraison(Delivery delivery);
    List<Delivery> listerLivraison();
}
