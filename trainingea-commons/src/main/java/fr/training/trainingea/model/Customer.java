package fr.training.trainingea.model;

import java.io.Serializable;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author shuttle
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static long nbCustomers = Math.abs(new Random().nextInt());
    @Id
    private String login;
    @Column(name = "firstName")
    private String firstName;
    private String lastName, address;
    private int age;

    public Customer() {
    }

    public Customer(String login) {
        this(login, "", "", "", 0);
    }

    public Customer(String login, String firstName, String lastName, String address, int age) {
        this.login = login + "_" + nbCustomers++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
}
