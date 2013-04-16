package fr.training.service;

import fr.training.trainingea.model.Customer;
import fr.training.trainingea.service.AccountManagerLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.lang.RuntimeException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author shuttle
 */
@Stateless
@Local(AccountManagerLocal.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class AccountManagerBean implements AccountManagerLocal {

    @Resource(name = "jdbc/training")
    DataSource dataSource;
    @Resource
    UserTransaction userTransaction;

    @Override
    public Customer createCustomer(String login, String firstName, String lastName, String address, int age) {
        Customer customer = new Customer(login, firstName, lastName, address, age);

        try {
            save(customer);
        } catch (SQLException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException ex) {
            Logger.getLogger(AccountManagerBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex); // Pour que ce soit intercepté par le Server d'Appli et qu'il fasse un Rollback
        }

        return customer;
    }

    private void save(Customer customer) throws SQLException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        Connection connection = dataSource.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("insert into customer (login, firstname, lastName, address, age) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, customer.getLogin());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setString(4, customer.getAddress());
            statement.setInt(5, customer.getAge());

            userTransaction.begin();
            statement.execute();
        } finally {
            userTransaction.commit();
            connection.close();
        }
    }
}
