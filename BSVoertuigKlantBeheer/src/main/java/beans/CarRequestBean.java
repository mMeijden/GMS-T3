package beans;

import lombok.Getter;
import lombok.Setter;
import repo.CarRepository;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Stateful
@Getter
@Setter
public class CarRequestBean implements Serializable {

@Inject
   private CarRepository carRepository;

    public void Test(){}

}
