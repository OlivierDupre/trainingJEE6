package fr.training.beans;

import javax.inject.Inject;

/**
 * @author shuttle
 */
@Sport
@FourWheels
@Transport
public class Ferrari extends Car {

    public @Inject
    Ferrari(@DriverQ Driver driver) {
        super(driver);
    }
}
