package beans;

import lombok.Getter;
import lombok.Setter;
import persist.Customer;
import repo.CustomerRepository;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@Stateful
public class CustomerRequestBean implements Serializable{

    @Inject
    private CustomerRepository customerRepository;

    public void addCustomer(Customer customer){
        customerRepository.add(customer);
        customerRepository.save();
    }
}
