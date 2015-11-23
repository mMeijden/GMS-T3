package beans;

import lombok.Getter;
import lombok.Setter;
import persist.Car;
import repo.CarRepository;


import javax.ejb.Stateful;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@Stateful
public class CarRequestBean implements Serializable {

    @Inject
   private CarRepository carRepository;

    public void addCar(Car car){
        carRepository.add(car);
        carRepository.save();
    }

    /**
     * Find a car by it's license number
     * @param license the license of the car
     * @return the car
     */
    public Car findByLicense(String license){
        return carRepository.findByLicense(license);
    }

}
