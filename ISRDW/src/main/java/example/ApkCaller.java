package example;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import persist.Car;
import wsdl.RDWSteekproefWebService;
import wsdl.RDWSteekproefWebService_Service;

/**
 * Created by @author Matthijs van der Meijden on 23-11-2015.
 */
@Stateless
@WebService
public class ApkCaller {

    @WebMethod
    public boolean markReadyForSample(Car car) {
        RDWSteekproefWebService_Service service = new RDWSteekproefWebService_Service();
        RDWSteekproefWebService port = service.getRDWSteekproefWebServicePort();
        boolean sample = port.steekproef(car.getLicense());
        return sample;
    }
}
