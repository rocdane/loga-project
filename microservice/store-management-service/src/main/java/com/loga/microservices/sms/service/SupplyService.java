package com.loga.microservices.sms.service;

import com.loga.microservices.sms.entity.Article;
import com.loga.microservices.sms.entity.Delivery;
import com.loga.microservices.sms.entity.Furnisher;
import com.loga.microservices.sms.entity.Order;
import com.loga.microservices.sms.repository.ProductRepository;
import com.loga.microservices.sms.repository.FurnisherRepository;
import com.loga.microservices.sms.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyService implements ISupplyService {

    @Autowired
    private FurnisherRepository furnisherRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

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
    public Furnisher afficherFournisseur(Long id) {
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
    public Article afficherFourniture(Long id) {
        return null;
    }

    /**
     * TODO : Cette méthode permet de modifier un objet Fourniture dans la base de données.
     * @param article
     */
    @Override
    public void modifierFourniture(Article article) {
        //todo:edit spare
    }

    @Override
    public List<Article> listSpare() {
        return null;
    }

    /**
     * TODO : Cette méthode permet d'enregistrer un objet Commande dans la base de donnnées.
     * @param order
     * @return Commande
     * @see Article
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
     * @see Article
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
