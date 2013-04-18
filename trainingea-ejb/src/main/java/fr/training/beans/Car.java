package fr.training.beans;

import fr.training.trainingea.model.Customer;
import java.util.Random;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * @author shuttle
 */
@Default
@Transport
@FourWheels
public class Car extends Vehicle {

    private final static String[] GENDER = {"female", "male"};

    @Produces
    @Gender
    public String randomGender() {
        return GENDER[new Random().nextInt(2)];
    }
    Customer customer;

    public @Inject
    Car(@DriverQ Driver driver) {
        customer = new Customer();
        customer.setFirstName(driver.getFirstName());
        customer.setFirstName(driver.getLastName());
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
