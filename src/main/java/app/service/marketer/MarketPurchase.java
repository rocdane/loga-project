package app.service.marketer;

import app.dao.PurchaseRepository;
import app.model.Article;
import app.model.Purchase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class MarketPurchase implements IMarketPurchase{

    private final PurchaseRepository purchaseRepository;

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Achat dans la base de données.
     * @param purchase
     * @return Achat
     * @see Article
     */
    @Override
    public Purchase enregistrerAchat(Purchase purchase) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Achat de la base de données.
     * @return List
     */
    @Override
    public List<Purchase> listerAchat() {
        return purchaseRepository.findAll();
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Achat de la base de données.
     * @param date
     * @return List
     */
    @Override
    public List<Purchase> listerAchat(Date date) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Achat de la base de données.
     * @param debut,fin
     * @return List
     */
    @Override
    public List<Purchase> listerAchat(Date debut, Date fin) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner un objet Achat de la base de données.
     * @param id
     * @return Achat
     */
    @Override
    public Purchase afficherAchat(long id) {
        return null;
    }
}
