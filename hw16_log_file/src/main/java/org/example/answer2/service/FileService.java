package org.example.answer2.service;

import org.example.answer2.configuration.SessionFactorySingleton;
import org.example.answer2.entity.File;
import org.example.answer2.repository.FileRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FileService {
    private final FileRepository fileRepository = new FileRepository();
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public void create(File file) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            fileRepository.create(file);
            transaction.commit();
        } catch (Exception sqlException) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception re) {
                    System.out.println(re.getMessage());
                }
            }
            System.out.println(sqlException.getMessage());
        } finally {
            session.close();
        }
    }
}
