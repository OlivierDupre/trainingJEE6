package fr.training.beans;

import fr.training.trainingea.model.Vehicle;
import fr.training.trainingea.model.Transport;
import fr.training.trainingea.model.Customer;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * @author shuttle
 */
@Default
@Transport
@FourWheels
public class Car extends Vehicle {

    Customer customer;

    public @Inject
    Car(@DriverQ Driver driver) {
        customer = new Customer();
        customer.setFirstName(driver.getFirstName());
        customer.setLastName(driver.getLastName());
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
