package fr.training.service;

import fr.training.trainingea.model.Account;
import fr.training.trainingea.model.Customer;
import fr.training.trainingea.service.AccountManagerLocal;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author shuttle
 */
@Stateless
@Local(AccountManagerLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountManagerBean implements AccountManagerLocal {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Customer createCustomer(String login, String firstName, String lastName, String address, int age) {
        Customer customer = new Customer(login, firstName, lastName, address, age);

        entityManager.persist(customer);

        return customer;
    }

    @Override
    public Account createAccount(Customer owner) {
        Account account = new Account(owner);

        entityManager.persist(account);

        return account;
    }
}
