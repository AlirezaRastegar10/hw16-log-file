package org.example.answer2.repository;

import org.example.answer2.configuration.SessionFactorySingleton;
import org.example.answer2.entity.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class FileRepository {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public void create(File file) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(file);
        session.close();
    }

}
