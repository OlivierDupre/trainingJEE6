package fr.training.beans;

import javax.inject.Inject;

/**
 *
 * @author shuttle
 */
@DriverQ
public class Driver {

    String firstName = "Oliv", lastName = "Dudu";
    @Inject
    @Gender
    private String gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName + " " +gender;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
