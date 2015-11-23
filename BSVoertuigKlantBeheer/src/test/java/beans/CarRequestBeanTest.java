package beans;

import org.junit.Before;
import org.junit.Test;
import persist.Car;
import repo.CarRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Remco on 23-11-2015.
 */
public class CarRequestBeanTest {

    private CarRepository carRepository;
    private CarRequestBean carRequestBean;
    private Car car;

    @Before
    public void setUp(){
        carRepository = mock(CarRepository.class);
        carRequestBean = new CarRequestBean();
        carRequestBean.setCarRepository(carRepository);
        car = new Car();
    }

    @Test
    public void testAddCar(){
        assertThat(carRequestBean.addCar(car), is(true));
    }

    @Test
    public void findByLicense(){
        car.setLicense("test01");
        when(carRepository.findByLicense(car.getLicense())).thenReturn(car);
        assertThat(carRequestBean.findByLicense("test01"), is(car));
    }
}
