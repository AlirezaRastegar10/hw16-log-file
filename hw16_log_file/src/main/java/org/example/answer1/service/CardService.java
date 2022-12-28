package org.example.answer1.service;


import org.example.answer1.canfiguration.SessionFactorySingleton;
import org.example.answer1.entity.Card;
import org.example.answer1.repository.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CardService {
    private static final Logger logger = LoggerFactory.getLogger(CardService.class);
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final Repository<Card> cardRepository = new Repository<>();

    public void create(Card card) {
        logger.info("Entering the card object into the create method...");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(card);
            transaction.commit();
            logger.info("Card object successfully created or updated...");
        } catch (Exception sqlException) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    logger.error("The Card object was not created or updated, the transaction was rolled back...");
                } catch (Exception re) {
                    logger.error("Error when trying to rollback transaction in create method");
                }
            }
            logger.error("Original error when executing query in create method");
        } finally {
            session.close();
        }
    }

    public Card findById(Long id) {
        logger.info("Entering the card object with ID {} in the findById method...", id);
        return cardRepository.findById(Card.class, id);
    }

    public void delete(Card card) {
        logger.info("Entering the card object into the delete method...");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(card);
            transaction.commit();
            logger.info("Card object with ID {} successfully deleted...", card.getId());
        } catch (Exception sqlException) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    logger.error("The card object with ID {} was not deleted, the transaction was rolled back...", card.getId());
                } catch (Exception re) {
                    logger.error("Error when trying to rollback transaction in delete method");
                }
            }
            logger.error("Original error when executing query in delete method");
        } finally {
            session.close();
        }
    }
}
