package com.loga.microservices.sms.service;

import com.loga.microservices.sms.entity.Article;
import com.loga.microservices.sms.entity.Sale;
import com.loga.microservices.sms.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TradeService implements ITradeService {

    @Autowired
    private SaleRepository saleRepository;

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Vente de la base de données.
     * @param sale
     * @return Vente
     * @see Article
     */
    @Override
    public Sale create(Sale sale) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Vente de la base de données.
     * @return List
     */
    @Override
    public List<Sale> list() {
        return saleRepository.findAll();
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Vente de la base de données.
     * @param date
     * @return List
     */
    @Override
    public List<Sale> list(Date date) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Vente de la base de données.
     * @param debut,fin
     * @return List
     */
    @Override
    public List<Sale> list(Date debut, Date fin) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner un objet Vente de la base de données.
     * @param id
     * @return Vente
     */
    @Override
    public Sale read(Long id) {
        return null;
    }
}
