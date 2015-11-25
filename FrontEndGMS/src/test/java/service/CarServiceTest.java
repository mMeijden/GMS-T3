package service;

import beans.CarRequestBean;
import org.junit.Before;
import org.junit.Test;
import persist.Car;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Remco on 23-11-2015.
 */
public class CarServiceTest {

    private CarRequestBean carRequestBean;
    private CarService carService;
    private Car car;

    @Before
    public void setUp(){
        carRequestBean = mock(CarRequestBean.class);
        carService = new CarService();
        carService.setCarRequestBean(carRequestBean);
        car = new Car();
    }

//    @Test
//    public void testAddCar(){
//        when(carRequestBean.addCar(car)).thenReturn(true);
//
//        carService.setCar(car);
//        assertThat(carService.addCar(), is(true));
//    }
}
