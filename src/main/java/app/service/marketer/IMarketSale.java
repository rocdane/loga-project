package app.service.marketer;

import app.model.Sale;

import java.util.Date;
import java.util.List;

public interface IMarketSale {
    Sale enregistrerVente(Sale sale);
    List<Sale> listerVente();
    List<Sale> listerVente(Date date);
    List<Sale> listerVente(Date debut, Date fin);
    Sale afficherVente(long id);
}
