package vendor.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Classe de configuration de hibernate/jpa.
 * TODO:Faire en sorte de réaliser une injection de dépendance à partir de cette classe.
 * @author rochdane sabi
 */
public class HibernateJpa {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return sessionFactory;
    }
}
