package repo;

import persist.Car;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;

import java.util.List;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Stateful
public class CarRepository extends AbstractRepository<Car> {

    @Override
    public List<Car> getAll() {
        return super.getAll(Car.class);
    }

    public Car findByLicense(String license){
        return getEm().createNamedQuery("findByLicense", Car.class)
                .setParameter("license", license)
                .getSingleResult();
    }
}
