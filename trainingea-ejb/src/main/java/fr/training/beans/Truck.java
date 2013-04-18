package fr.training.beans;

import fr.training.trainingea.model.Vehicle;
import fr.training.trainingea.model.Transport;
import java.util.Random;
import javax.enterprise.inject.Produces;

/**
 * @author shuttle
 */
@Transport
@SoManyWheels
public class Truck extends Vehicle {

    private final static String[] GENDER = {"female", "male"};

    @Produces
    @Gender
    public String randomGender() {
        return GENDER[new Random().nextInt(2)];
    }
}
