package example;

import persist.Car;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.security.SecureRandom;

/**
 * Created by @author Matthijs van der Meijden on 23-11-2015.
 */
@Stateless
@WebService
public class ApkCaller {

    @WebMethod
    public boolean markReadyForSample(Car car) {
        //car unused yet. Should persist license plate to RDW.
        SecureRandom sample = new SecureRandom();
        int getal = sample.nextInt(2);
        if (getal == 1) {
            return true;
        } else {
            return false;
        }

    }
}
