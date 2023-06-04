package com.loga.microservices.sms.service;

import com.loga.microservices.sms.entity.*;

import java.util.List;

public interface ISupplyService {

    /**
     * Regroupement des opérations de logistique
     * */
    Furnisher enregistrerFournisseur(Furnisher furnisher);
    Furnisher afficherFournisseur(Long id);
    List<Furnisher> listFournisseur();
    void supprimerFournisseur(Furnisher furnisher);
    Article afficherFourniture(Long id);
    void modifierFourniture(Article article);
    List<Article> listSpare();
    Order enregistrerCommande(Order order);
    List<Order> listerCommande();
    List<Order> chercherCommande(String immatriculation);
    Delivery enregistrerLivraison(Delivery delivery);
    List<Delivery> listerLivraison();
}
