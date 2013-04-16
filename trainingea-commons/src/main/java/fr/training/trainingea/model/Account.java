package fr.training.trainingea.model;

/**
 *
 * @author shuttle
 */
public class Account {

    private Customer owner;
    private float amount;

    public Account(Customer owner) {
        this(owner, 0);
    }

    public Account(Customer owner, float amount) {
        this.owner = owner;
        this.amount = amount;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    
}
