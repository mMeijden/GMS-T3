package beans;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import persist.Car;
import repo.CarRepository;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@Stateful
public class CarRequestBean implements Serializable {

    @Inject
    private CarRepository carRepository;

    /**
     * Add car to the DB.
     * @param car the car to save@param car
     * @return boolean succeeded
     */
    public boolean addCar(Car car) {
        carRepository.add(car);
        carRepository.save();
        return true;
    }

    public void updateCar(Car car){
        carRepository.update(car);
    }

    /**
     * Find a car by it's license number
     *
     * @param license the license of the car
     * @return the car
     */
    public Car findByLicense(String license) {
        return carRepository.findByLicense(license);
    }


}
