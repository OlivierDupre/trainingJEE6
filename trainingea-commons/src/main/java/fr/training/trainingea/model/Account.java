package fr.training.trainingea.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author shuttle
 */
@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @JoinColumns({
        @JoinColumn(name = "owner_firstName", referencedColumnName = "firstName"),
        @JoinColumn(name = "owner_lastName", referencedColumnName = "lastName")
    })
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer owner;
    @JoinColumns({
        @JoinColumn(name = "ownerSecondary_firstName", referencedColumnName = "firstName"),
        @JoinColumn(name = "ownerSecondary_lastName", referencedColumnName = "lastName")
    })
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer ownerSecondary;
    private float amount;

    public Account() {
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public void setOwnerSecondary(Customer ownerSecondary) {
        this.ownerSecondary = ownerSecondary;
    }

    public Customer getOwnerSecondary() {
        return ownerSecondary;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
