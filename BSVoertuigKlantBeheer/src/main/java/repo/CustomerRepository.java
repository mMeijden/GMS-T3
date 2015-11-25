package repo;

import persist.Customer;

import javax.ejb.Stateful;
import javax.persistence.NoResultException;
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

    @Override
    public Customer findById(Long idToFind) {
        return super.findById(Customer.class, idToFind);
    }

    public Customer findByEmail(String email) {
        try {
            return getEm().createNamedQuery("findByEmail", Customer.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            //TODO: Log exception
            return null;
        }
    }


}
