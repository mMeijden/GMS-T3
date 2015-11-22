package service;

import beans.CustomerRequestBean;
import lombok.Getter;
import lombok.Setter;
import persist.Customer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by @author Matthijs van der Meijden on 22-11-2015.
 */
@Getter
@Setter
@Named("customerService")
@RequestScoped
public class CustomerService implements Serializable {
    private Customer customer;

    @EJB
    private CustomerRequestBean customerRequestBean;

    @PostConstruct
    public void init() {
        customer = new Customer();
    }

    public void createCustomer(){
        customerRequestBean.addCustomer(customer);
    }
}