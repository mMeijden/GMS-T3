package example;

import persist.Car;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Random;

/**
 * Created by @author Matthijs van der Meijden on 23-11-2015.
 */
@WebService()
public class ApkCaller {

    @WebMethod
    public boolean markReadyForSteekProef(){
        Random steekproef = new Random();
        int getal = steekproef.nextInt(1);
            if(getal == 1){
                return true;
            }else{
                return false;
        }

    }
}
