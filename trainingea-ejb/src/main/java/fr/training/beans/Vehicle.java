package fr.training.beans;

import javax.inject.Named;

/**
 * @author shuttle
 */
//Permet de déclarer le fait que c'est un bean qu'on pourra injecter.
//On aurait aussi pu utiliser un Qualifier si on en avait eu un
@Named
public class Vehicle {

    String id;
    int maxSpeed, maxPassengers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }
}
