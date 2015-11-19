package beans;

import repo.CustomerRepository;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
public class CustomerRequestBean implements Serializable{

    @Inject
    private CustomerRepository customerRepository;
}
