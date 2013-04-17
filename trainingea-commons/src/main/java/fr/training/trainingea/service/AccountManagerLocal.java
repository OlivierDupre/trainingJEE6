package fr.training.trainingea.service;

import fr.training.trainingea.model.Account;
import fr.training.trainingea.model.Customer;
import fr.training.trainingea.model.CustomerPK;
import java.util.List;

/**
 * @author shuttle
 */
public interface AccountManagerLocal {

    Customer createCustomer(String firstName, String lastName, String address, int age);

    Account createAccount(Customer owner);

    Customer findCustomer(String firstName, String lastName);

    void addPrimaryAccount(CustomerPK customerPK, float deposite);

    List<Account> findAccountsForCustomer(String firstName, String lastName);

    void updateCustomer(Customer customer);
}
