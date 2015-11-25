package beans;

import example.ApkCaller;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import persist.Car;
import persist.Customer;

import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * Created by @author Matthijs van der Meijden on 25-11-2015.
 */
@Getter
@Setter
@NoArgsConstructor
@Stateful
public class CouplingProcessBean {
    @EJB
    private CustomerRequestBean customerRequestBean;
    @EJB
    private CarRequestBean carRequestBean;
    private String returnPage;

  //  private String returnPage;
    private Car car;
    private Customer customer;

    public String coupleOwnerToCar(String license, String email){
        if(customerExists(email)){

            if(carExists(license)){
                customer.addCarToList(car);
                car.setCustomer(customer);
                return returnPage = "index";
            }else{
                return "createCar";
            }
        }else{
            return "createCustomer";
        }
    }

    private boolean customerExists(String email) {

        customer = customerRequestBean.findByEmail(email);
        if (customer == null) {
            returnPage = "createCustomer";
            return false;
        } else {
            return true;
        }

    }

    /**
     * Check if car exists in DB.
     *
     * @param license car's license
     * @return boolean exists
     */
    private boolean carExists(String license) {
        car = carRequestBean.findByLicense(license);
        if (car == null) {
            returnPage = "createCar";
            return false;
        } else {
            return true;
        }
    }

}
