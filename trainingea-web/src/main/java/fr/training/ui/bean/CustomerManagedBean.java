package fr.training.ui.bean;

import fr.training.trainingea.model.Customer;
import fr.training.trainingea.service.AccountManagerLocal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author shuttle
 */
@ManagedBean
@RequestScoped
public class CustomerManagedBean {

    private Customer customer = new Customer();
    private HtmlCommandButton saveButton;
    @EJB
    private AccountManagerLocal accountManager;

    /**
     * Creates a new instance of CustomerManagedBean
     */
    public CustomerManagedBean() {
    }

    public String getFirstName() {
        return customer.getFirstName();
    }

    public void setFirstName(String firstName) {
        this.customer.setFirstName(firstName);
    }

    public String getLastName() {
        return customer.getLastName();
    }

    public void setLastName(String lastName) {
        this.customer.setLastName(lastName);
    }

    public String getAddress() {
        return customer.getAddress();
    }

    public void setAddress(String address) {
        this.customer.setAddress(address);
    }

    public int getAge() {
        return customer.getAge();
    }

    public void setAge(int age) {
        this.customer.setAge(age);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public HtmlCommandButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(HtmlCommandButton saveButton) {
        this.saveButton = saveButton;
    }

    public void save() {
        accountManager.createCustomer(customer);
        saveButton.setStyle("color: green;");
    }

    public void loadCustomer() {
        if (customer != null && customer.getFirstName() != null && customer.getLastName() != null) {
            customer = accountManager.findCustomer(customer.getFirstName(), customer.getLastName());
            saveButton.setStyle("color: red;");
        }
    }
}
