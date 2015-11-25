package beans;

import lombok.Getter;
import lombok.Setter;
import persist.Customer;
import repo.CustomerRepository;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

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

    public Customer findByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        return customerRepository.findByEmail(email);
    }


}
