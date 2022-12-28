package org.example.answer1.repository;


import org.example.answer1.canfiguration.SessionFactorySingleton;
import org.example.answer1.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Repository<E extends BaseEntity<Long>> {
    private static final Logger logger = LoggerFactory.getLogger(Repository.class);
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public List<E> findAll(Class<E> e) {
        logger.info("Entering the {} object into the findAll method...", e.getName());
        Session session = sessionFactory.openSession();
        Query<E> query = session.createQuery("from " + e.getName(), e);
        List<E> list = query.getResultList();
        if (list.isEmpty()) {
            logger.info("There are no {} objects...", e.getName());
            return null;
        } else {
            logger.info("A list of {} objects is returned...", e.getName());
            return list;
        }
    }

    public E findById(Class<E> e, Long id) {
        return sessionFactory.openSession().get(e, id);
    }
}
