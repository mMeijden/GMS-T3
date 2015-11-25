package service;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import beans.CarRequestBean;
import lombok.Getter;
import lombok.Setter;
import persist.Car;
import persist.Customer;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@ManagedBean(name = "carService")
@RequestScoped
public class CarService implements Serializable {

    private Car car;

    @EJB
    private CarRequestBean carRequestBean;




    @PostConstruct
    public void init() {
        car = new Car();
        car.setCustomer(new Customer());
    }

    /**
     * Add car too DB.
     * @return boolean succeeded
     */
    public boolean addCar()
    {
        return carRequestBean.addCar(car);
    }

}
