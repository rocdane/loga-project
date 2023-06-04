package com.loga.microservices.sms.service;

import com.loga.microservices.sms.entity.Product;
import com.loga.microservices.sms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation de l'interface IMarket représentant les opérations du service Vente
 * @author rochdane sabi
 * @email rocdanesabi@gmail.com
 */
@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Article dans la base de données.
     * @param article
     * @return Article
     */
    @Override
    public Product create(Product article) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Article de la base de données.
     * @return List
     */
    @Override
    public List<Product> list() {
        return (List<Product>) productRepository.findAll();
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Article de la base de données.
     * @return List
     */
    @Override
    public List<Product> search(String text) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner un objet Article de la base de données.
     * @param id
     * @return Article
     */
    @Override
    public Product read(Long id) {
        return null;
    }

    @Override
    public Product read(String txt) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de mettre à jour un objet Article de la base de données.
     * @param up
     */
    @Override
    public boolean edit(Product up) {
        return false;
    }
}
