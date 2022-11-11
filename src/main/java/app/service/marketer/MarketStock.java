package app.service.marketer;

import app.dao.*;
import app.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation de l'interface IMarket représentant les opérations du service Vente
 * @author rochdane sabi
 * @email rocdanesabi@gmail.com
 */
@Service
@AllArgsConstructor
public class MarketStock implements IMarketStock {
    private final ArticleRepository articleRepository;

    /**
     * TODO:Cette méthode permet d'enregistrer un objet Article dans la base de données.
     * @param article
     * @return Article
     */
    @Override
    public Article enregistrerArticle(Article article) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Article de la base de données.
     * @return List
     */
    @Override
    public List<Article> listerArticle() {
        return (List<Article>) articleRepository.findAll();
    }

    /**
     * TODO:Cette méthode permet de sélectionner tous les objets Article de la base de données.
     * @return List
     */
    @Override
    public List<Article> chercherArticle(String text) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de sélectionner un objet Article de la base de données.
     * @param id
     * @return Article
     */
    @Override
    public Article afficherArticle(long id) {
        return null;
    }

    @Override
    public Article afficherArticle(String txt) {
        return null;
    }

    /**
     * TODO:Cette méthode permet de mettre à jour un objet Article de la base de données.
     * @param up
     */
    @Override
    public boolean modifierArticle(Article up) {
        return false;
    }
}
