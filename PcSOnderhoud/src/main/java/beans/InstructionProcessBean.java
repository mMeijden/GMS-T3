package beans;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import example.ApkCaller;
import lombok.NoArgsConstructor;
import persist.Car;
import persist.Instruction;

/**
 * Created by Remco on 19-11-2015.
 * <p/>
 * Handles the process of creating an instruction.
 */
@NoArgsConstructor
@Stateful
public class InstructionProcessBean {

    @EJB
    private InstructionRequestBean instructionRequestBean;
    @EJB
    private CarRequestBean carRequestBean;

    @Inject
    private ApkCaller apkCaller;

    private String returnPage;
    private Car car;
//    private Customer customer;

    public InstructionProcessBean(InstructionRequestBean irb, CarRequestBean crb){
        this.instructionRequestBean = irb;
        this.carRequestBean = crb;
    }

    public String executeProcess(String email, String license, Instruction instruction) {
        if (!customerExists(email) || !carExists(license)){
            return returnPage;
        }
        else {
            createInstruction(car, instruction);
        }
        return "instructionConfirmed";
    }

    private boolean customerExists(String email){
//        TODO: Uncomment once customers are implemented and modify unitTest
//        customer = customerRequestBean.findbyEmail(email);
//        if (customer == null){
//            returnPage = "../customer/createCustomer.xhtml";
//            return false;
//        }
//        else{
//            return true;
//        }
        return true;
    }

    private boolean carExists(String license){
        car = carRequestBean.findByLicense(license);
        if (car == null){
            returnPage = "createCar";
            return false;
        }
        else {
            return true;
        }
    }

    private boolean createInstruction(Car car, Instruction instruction){
        instruction.setCar(car);
        instruction.setSample(false);
        return instructionRequestBean.createInstruction(instruction);
    }

    /**
     * Sends a message to the RDW to notify that the car is ready for a Sample.
     * The RDW will return a true or false message to mark the car.
     * @param instruction the instruction that is updated.
     */
    public void markCarReady(Instruction instruction){

        if(apkCaller.markReadyForSteekProef(instruction.getCar())){ // SOAP CALL TO RDW.

            instructionRequestBean.markReadyForSample(instruction);
        }


    }

}
