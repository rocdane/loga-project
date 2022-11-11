package app.service.marketer;

import app.model.Purchase;

import java.util.Date;
import java.util.List;

public interface IMarketPurchase {
    Purchase enregistrerAchat(Purchase purchase);
    List<Purchase> listerAchat();
    List<Purchase> listerAchat(Date date);
    List<Purchase> listerAchat(Date debut, Date fin);
    Purchase afficherAchat(long id);
}
