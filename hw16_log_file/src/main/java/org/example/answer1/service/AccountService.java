package org.example.answer1.service;


import org.example.answer1.canfiguration.SessionFactorySingleton;
import org.example.answer1.entity.Account;
import org.example.answer1.repository.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final Repository<Account> accountRepository = new Repository<>();

    public void create(Account account) {
        logger.info("Entering the account object into the create method...");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(account);
            transaction.commit();
            logger.info("Account object successfully created or updated...");
        } catch (Exception sqlException) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    logger.error("The account object was not created or updated, the transaction was rolled back...");
                } catch (Exception re) {
                    logger.error("Error when trying to rollback transaction in create method");
                }
            }
            logger.error("Original error when executing query in create method");
        } finally {
            session.close();
        }
    }

    public List<Account> findAll() {
        return accountRepository.findAll(Account.class);
    }

    public Account findById(Long id) {
        logger.info("Entering the account object with ID {} in the findById method...", id);
        return accountRepository.findById(Account.class, id);
    }

    public void delete(Account account) {
        logger.info("Entering the account object into the delete method...");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(account);
            transaction.commit();
            logger.info("Account object with ID {} successfully deleted...", account.getId());
        } catch (Exception sqlException) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                    logger.error("The account object with ID {} was not deleted, the transaction was rolled back...", account.getId());
                } catch (Exception re) {
                    logger.error("Error when trying to rollback transaction in delete method");
                }
            }
            logger.error("Original error when executing query in delete method");
        } finally {
            session.close();
        }
    }

    public long deposit(long balance, long money) {
        logger.info("Entering the account object into the deposit method...");
        long inventory = balance;
        inventory += money;
        return inventory;
    }

    public long withdraw(long balance, long money) {
        logger.info("Entering the account object into the withdraw method...");
        long inventory = balance;
        if (money < balance) {
            inventory -= money;
        } else {
            System.out.println("Insufficient balance :(");
            logger.error("Insufficient balance :(");
            return balance;
        }
        return inventory;
    }
}
