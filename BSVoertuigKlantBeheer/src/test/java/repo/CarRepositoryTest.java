package repo;

import org.junit.Before;
import org.junit.Test;
import persist.Car;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Remco on 23-11-2015.
 */
public class CarRepositoryTest {

    private CarRepository carRepository;
    private ArrayList<Car> cars;
    private EntityManager em;

    @Before
    public void setUp(){
        carRepository = new CarRepository();
        em = mock(EntityManager.class);
        carRepository.setEm(em);
    }

    @Test
    public void testGetAll(){
        Car car = new Car();
        cars = new ArrayList<>();
        cars.add(car);
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        CriteriaQuery<Car> cq = mock(CriteriaQuery.class);
        Root<Car> root = mock(Root.class);
        TypedQuery<Car> tq = mock(TypedQuery.class);

        when(em.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(Car.class)).thenReturn(cq);
        when(cq.from(Car.class)).thenReturn(root);
        when(em.createQuery(cq)).thenReturn(tq);
        when(tq.getResultList()).thenReturn(cars);

        assertThat(carRepository.getAll(), is(cars));
    }

    @Test
    public void testFindByLicense(){
        Car car = new Car();
        car.setLicense("test01");

        TypedQuery<Car> tq = mock(TypedQuery.class);
        when(carRepository.getEm().createNamedQuery("findByLicense", Car.class)).thenReturn(tq);
        when(tq.setParameter("license", "test01")).thenReturn(tq);
        when(tq.getSingleResult()).thenReturn(car);

        assertThat(carRepository.findByLicense("test01"), is(car));
    }
}
