package fr.training.trainingea.model;

import java.util.Random;

/**
 *
 * @author shuttle
 */
public class Customer {

    private final static int nbCustomers = new Random().nextInt();
    String login, firstName, lastName, address;
    int age;

    public Customer(String login, String firstName, String lastName, String address, int age) {
        this.login = login + "_" + nbCustomers;
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
