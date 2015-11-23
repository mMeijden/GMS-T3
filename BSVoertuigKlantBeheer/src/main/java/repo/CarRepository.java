package repo;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.NoResultException;

import persist.Car;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Stateful
public class CarRepository extends AbstractRepository<Car> {

    @Override
    public List<Car> getAll() {
        return super.getAll(Car.class);
    }

    public Car findByLicense(String license) {
        try {
            return getEm().createNamedQuery("findByLicense", Car.class)
                    .setParameter("license", license)
                    .getSingleResult();
        } catch (NoResultException e) {
            //TODO: Log exception
            return null;
        }
    }
}
