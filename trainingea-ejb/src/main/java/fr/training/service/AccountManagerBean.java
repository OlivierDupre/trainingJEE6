package fr.training.service;

import fr.training.trainingea.model.Account;
import fr.training.trainingea.model.Customer;
import fr.training.trainingea.service.AccountManagerLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

/**
 *
 * @author shuttle
 */
@Stateless
@Local(AccountManagerLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountManagerBean implements AccountManagerLocal {

    @Resource(name = "jdbc/training")
    DataSource dataSource;
    @Resource
    EJBContext ejbContext;

    @Override
    public Customer createCustomer(String login, String firstName, String lastName, String address, int age) {
        Customer customer = new Customer(login, firstName, lastName, address, age);

        try {
            save(customer);
        } catch (SQLException ex) {
            Logger.getLogger(AccountManagerBean.class.getName()).log(Level.SEVERE, null, ex);

            // Précise à la transaction qu'il y a un problème et qu'il faudra faire un rollback plutôt qu'un commit
            ejbContext.setRollbackOnly();
        }

        return customer;
    }

    @Override
    public Account createAccount(String login) {
        Account account = new Account(new Customer(login));

        try {
            save(account);
        } catch (SQLException ex) {
            Logger.getLogger(AccountManagerBean.class.getName()).log(Level.SEVERE, null, ex);

            throw new RuntimeException(ex); // Pour que ce soit intercepté par le Server d'Appli et qu'il fasse un Rollback
        }

        return account;
    }

    private void save(Customer customer) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("insert into customer (login, firstname, lastName, address, age) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, customer.getLogin());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setString(4, customer.getAddress());
            statement.setInt(5, customer.getAge());

            statement.execute();
        } finally {
            connection.close();
        }
    }

    private void save(Account account) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("insert into account (login, amount) VALUES (?, ?)");
            statement.setString(1, account.getOwner().getLogin());
            statement.setFloat(2, account.getAmount());

            statement.execute();
        } finally {
            connection.close();
        }
    }
}
