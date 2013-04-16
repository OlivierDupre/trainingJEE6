package fr.training.trainingea.service;

import fr.training.trainingea.model.Account;
import fr.training.trainingea.model.Customer;

/**
 * @author shuttle
 */
public interface AccountManagerLocal {

    Customer createCustomer(String login, String firstName, String lastName, String address, int age);

    Account createAccount(Customer owner);
}
