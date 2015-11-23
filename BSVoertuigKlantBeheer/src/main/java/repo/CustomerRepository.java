package repo;

import persist.Customer;

import javax.ejb.Stateful;
import java.util.List;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Stateful
public class CustomerRepository extends AbstractRepository<Customer> {
    @Override
    public List<Customer> getAll() {
        return super.getAll(Customer.class);
    }
}
