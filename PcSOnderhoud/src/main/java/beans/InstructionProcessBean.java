package beans;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import persist.Car;
import persist.Instruction;

/**
 * Created by Remco on 19-11-2015.
 * <p/>
 * Handles the process of creating an instruction.
 */
@Stateful
public class InstructionProcessBean {

    @EJB
    private InstructionRequestBean instructionRequestBean;
    @EJB
    private CarRequestBean carRequestBean;

    private String returnPage;
    private Car car;
//    private Customer customer;

//    /**
//     * TODO fancy javadoc
//     * @return boolean succeeded
//     */
//    public boolean createInstruction(String license, Date assignDate, int mileage, boolean apk, boolean sample, String description){
//        Car car = carRequestBean.findByLicense(license);
//        boolean succeeded = instructionRequestBean.createInstruction(car, assignDate, mileage, apk, sample, description);
//        return succeeded;
//    }

    public String executeProcess(String email, String license, Instruction instruction) {
        if (!customerExists(email) || !carExists(license)){
            return returnPage;
        }
        else {
            createInstruction(car, instruction);
        }
        return "index.xhtml";
    }

    private boolean customerExists(String email){
        return true;
    }

    private boolean carExists(String license){
        car = carRequestBean.findByLicense(license);
        if (car == null){
            return false;
        }
        else {
            return true;
        }
    }

    private boolean createInstruction(Car car, Instruction instruction){
        instruction.setCar(car);
        return instructionRequestBean.createInstruction(instruction);
    }

    private void resetProcess() {

    }
}
