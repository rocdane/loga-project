package app.service.marketer;

import app.dao.SaleRepository;
import app.model.Article;
import app.model.Sale;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class MarketSale implements IMarketSale{

    private final SaleRepository saleRepository;
    /**
     * TODO:Cette méthode permet d'enregistrer un objet Vente de la base de données.
     * @param sale
     * @return Vente
     * @see Article
     */
    @Override
    public Sale enregistrerVente(Sale sale) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Vente de la base de données.
     * @return List
     */
    @Override
    public List<Sale> listerVente() {
        return saleRepository.findAll();
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Vente de la base de données.
     * @param date
     * @return List
     */
    @Override
    public List<Sale> listerVente(Date date) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Vente de la base de données.
     * @param debut,fin
     * @return List
     */
    @Override
    public List<Sale> listerVente(Date debut, Date fin) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner un objet Vente de la base de données.
     * @param id
     * @return Vente
     */
    @Override
    public Sale afficherVente(long id) {
        return null;
    }
}
