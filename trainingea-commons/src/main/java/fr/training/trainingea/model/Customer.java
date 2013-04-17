package fr.training.trainingea.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author shuttle
 */
@Entity
@Table(name = "customer")
@IdClass(CustomerPK.class)
@NamedQuery(name="Customer.findAllAccounts", query = "select a from Account a where a.owner = :customer")
public class Customer implements Serializable {

    // Gestion identique à celle de l'OPTIMISTIC LOCK.
    @Version
    private int version;
    
    @Id
    @Column(name = "firstName")
    private String firstName;
    @Id
    @Column(name = "lastName")
    private String lastName;
    private String address;
    private int age;
    @OneToMany(mappedBy = "owner")
    Set<Account> principalAccounts;
    @OneToMany(mappedBy = "ownerSecondary")
    Set<Account> secondaryAccounts;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String address, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Account> getPrincipalAccounts() {
        return principalAccounts;
    }

    public void setPrincipalAccounts(Set<Account> principalAccounts) {
        this.principalAccounts = principalAccounts;
    }

    public Set<Account> getSecondaryAccounts() {
        return secondaryAccounts;
    }

    public void setSecondaryAccounts(Set<Account> secondaryAccounts) {
        this.secondaryAccounts = secondaryAccounts;
    }

    public int getNbAccounts() {
        return getPrincipalAccounts().size() + getSecondaryAccounts().size();
    }

    public void addPrimaryAccount(Account account) {
        getPrincipalAccounts().add(account);
    }

    public void addSecondaryAccount(Account account) {
        getSecondaryAccounts().add(account);
    }
}
