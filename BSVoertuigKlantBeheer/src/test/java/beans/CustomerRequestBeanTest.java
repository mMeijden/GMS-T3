package beans;

import org.junit.Before;
import org.junit.Test;
import persist.Customer;
import repo.CustomerRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

/**
 * Created by Remco on 23-11-2015.
 */
public class CustomerRequestBeanTest {

    private CustomerRepository customerRepository;
    private CustomerRequestBean customerRequestBean;
    private Customer customer;

    @Before
    public void setUp(){
        customerRepository = mock(CustomerRepository.class);
        customerRequestBean = new CustomerRequestBean();
        customerRequestBean.setCustomerRepository(customerRepository);
        customer = new Customer();
    }

    @Test
    public void testAddCustomer(){
        assertThat(customerRequestBean.addCustomer(customer), is(true));
    }
}
