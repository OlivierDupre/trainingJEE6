package fr.training.service;

import fr.training.beans.Car;
import fr.training.trainingea.model.Transport;
import fr.training.trainingea.model.Vehicle;
import fr.training.trainingea.model.Account;
import fr.training.trainingea.model.Constants;
import fr.training.trainingea.model.Customer;
import fr.training.trainingea.model.CustomerPK;
import fr.training.trainingea.service.AccountManagerLocal;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author shuttle
 */
@WebService
@Stateless
@Local(AccountManagerLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@DeclareRoles({Constants.CLIENT, Constants.MANAGER})
@RolesAllowed(Constants.MANAGER) // Le manager peut exécuter toutes les méthodes pour lesquelles aucun @RolesAllowed n'est spécifié
public class AccountManagerBean implements AccountManagerLocal {

    @PersistenceContext
    EntityManager entityManager;
    @Inject
    @Transport
    Instance<Vehicle> vehicles;

    @Override
    public Customer createCustomer(String firstName, String lastName, String address, int age) {
        return createCustomer(new Customer(firstName, lastName, address, age));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        for (Vehicle vehicle : vehicles) {
            System.out.println("Vehicle: " + vehicle);
            if (vehicle instanceof Car) {
                Customer vehicleCustomer = ((Car) vehicle).getCustomer();
                System.out.printf("Driver: %s %s\n", vehicleCustomer.getFirstName(), vehicleCustomer.getLastName());
            }
        }

        entityManager.persist(customer);

        return customer;
    }

    @Override
    public Account createAccount(Customer owner) {
        Account account = new Account();
        account.setOwner(owner);

        entityManager.persist(account);

        return account;
    }

    @Override
    @RolesAllowed({Constants.CLIENT, Constants.MANAGER}) // Seuls les rôles client et manager peuvent faire ça. -> Annule et la déclaration faite pour la classe.
    public Customer findCustomer(String firstName, String lastName) {
        return entityManager.find(Customer.class, new CustomerPK(firstName, lastName));
    }

    @Override
    public List<Account> findAccountsForCustomer(String firstName, String lastName) {
        TypedQuery<Account> accounts = entityManager.createNamedQuery("Customer.findAllAccounts", Account.class);

        // entityManager.getReference permet de récupérer la clef primaire de l'objet sans charger aucun des objets liés (LAZY ou même EAGER)
        accounts.setParameter("customer", entityManager.getReference(Customer.class, new CustomerPK(firstName, lastName)));

        return accounts.getResultList();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED) // Valeur par défaut
    public void addPrimaryAccount(CustomerPK customerPK, float deposite) {
        Customer customer = entityManager.find(Customer.class, customerPK, LockModeType.PESSIMISTIC_WRITE);
        // LockModeType.PESSIMISTIC_READ -> J'autorise la lecture mais pas l'écriture.
        // LockModeType.PESSIMISTIC_WRITE -> J'interdit la lecture.
        // LockModeType.OPTIMISTIC -> Principe identique à SVN par défaut.

        Account account = new Account();
        account.setAmount(deposite);
        // Pour générer correctement la requête UPDATE sur le table
        account.setOwner(customer);

        // Pour conserver le cache de niveau 2 cohérent
        customer.addPrimaryAccount(account);

        entityManager.persist(account);
    }

    @Override
    public void updateCustomer(Customer customer) {
        entityManager.merge(customer);
    }
}
