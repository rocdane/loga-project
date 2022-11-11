package app.service.marketer;

import app.model.Article;

import java.util.List;

/**
 * Interface de l'application LoGa représentant les opérations du service Vente
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 * @since 2.0
 */
public interface IMarketStock {
    Article enregistrerArticle(Article article);
    List<Article> listerArticle();
    List<Article> chercherArticle(String text);
    Article afficherArticle(long id);
    Article afficherArticle(String txt);
    boolean modifierArticle(Article up);
}
