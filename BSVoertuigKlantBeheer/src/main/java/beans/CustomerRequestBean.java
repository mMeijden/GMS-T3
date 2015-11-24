package beans;

import java.io.Serializable;
import java.sql.SQLDataException;
import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import persist.Customer;
import repo.CustomerRepository;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@Stateful
public class CustomerRequestBean implements Serializable {

    @Inject
    private CustomerRepository customerRepository;

    /**
     * Add customer to the DB.
     * @param customer the customer to add
     * @return boolean succeeded
     */
    public boolean addCustomer(Customer customer) {
        try {
            customerRepository.add(customer);
            customerRepository.save();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public List<Customer> getAllCustomers(){
       return customerRepository.getAll();
    }

    public void updateCustomer(Customer customer){
        customerRepository.update(customer);
    }
}
