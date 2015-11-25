package service;

import beans.CustomerRequestBean;
import lombok.Getter;
import lombok.Setter;
import persist.Customer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;

/**
 * Created by @author Matthijs van der Meijden on 22-11-2015.
 */
@Getter
@Setter
@Named("customerService")
@SessionScoped
public class CustomerService implements Serializable {

    private Customer customer;


    @EJB
    private CustomerRequestBean customerRequestBean;

    @PostConstruct
    public void init() {
        customer = new Customer();

    }

    public void createCustomer() {
        customerRequestBean.addCustomer(customer);

    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = customerRequestBean.getAllCustomers();
        return list;
    }

    public void enableCustomerEdit() {
        this.customer.setEdited(true);
    }

    public void disableCustomerEdit() {
        this.customer.setEdited(false);
    }

    public void saveChanges() {
        disableCustomerEdit();
        customerRequestBean.updateCustomer(customer);
    }

    public String viewCustomer(Customer customer) {
        this.customer = new Customer();
        this.customer = customerRequestBean.findByEmail(customer.getEmail());
        return "viewCustomer";

    }

}