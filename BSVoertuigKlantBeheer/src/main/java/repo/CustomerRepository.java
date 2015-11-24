package repo;

import java.util.List;

import javax.ejb.Stateful;

import persist.Customer;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Stateful
public class CustomerRepository extends AbstractRepository<Customer> {
    @Override
    public List<Customer> getAll() {
        return super.getAll(Customer.class);
    }

    @Override
    public Customer findById(Long idToFind) {
        return super.findById(Customer.class, idToFind);
    }
}
