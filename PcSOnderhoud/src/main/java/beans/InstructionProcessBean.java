package beans;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import persist.Car;

/**
 * Created by Remco on 19-11-2015.
 * <p/>
 * Handles the processes of instructions.
 */
@Stateful
public class InstructionProcessBean {

    @EJB
    private InstructionRequestBean instructionRequestBean;
    @EJB
    private CarRequestBean carRequestBean;

    /**
     * TODO fancy javadoc
     * @return boolean succeeded
     */
    public boolean createInstruction(String license, Date assignDate, int mileage, boolean apk, boolean sample, String description){
        Car car = carRequestBean.findByLicense(license);
        boolean succeeded = instructionRequestBean.createInstruction(car, assignDate, mileage, apk, sample, description);
        return succeeded;
    }
}
