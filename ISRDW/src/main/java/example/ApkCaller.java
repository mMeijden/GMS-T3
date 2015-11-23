package example;

import java.security.SecureRandom;

import javax.jws.WebMethod;
import javax.jws.WebService;

import persist.Car;

/**
 * Created by @author Matthijs van der Meijden on 23-11-2015.
 */
@WebService()
public class ApkCaller {

    @WebMethod
    public boolean markReadyForSample(Car car) {
        //car unused yet. Should persist license plate to RDW.
        SecureRandom sample = new SecureRandom();
        int getal = sample.nextInt(1);
        if (getal == 1) {
            return true;
        } else {
            return false;
        }

    }
}
