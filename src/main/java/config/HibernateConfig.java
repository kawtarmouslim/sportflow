package config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {

    private static SessionFactory sessionFactory = getSessionFactory();

    public static SessionFactory getSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        }catch(Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSession() {
        return sessionFactory;
    }
}
