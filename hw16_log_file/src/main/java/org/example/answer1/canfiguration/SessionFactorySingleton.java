package org.example.answer1.canfiguration;


import org.example.answer1.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionFactorySingleton {
    private static final Logger logger = LoggerFactory.getLogger(SessionFactorySingleton.class);

    private SessionFactorySingleton() {}

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Account.class)
                    .addAnnotatedClass(Card.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        logger.info("Entering the getInstance method for returning a sessionFactory object...");
        return LazyHolder.INSTANCE;
    }
}
