package beans;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.context.FacesContext;

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

    private String returnPage;
    private Car car;
//    private Customer customer;

    /**
     * Constructor for testing purposes.
     * @param irb Mock of InstructionRequestBean
     * @param crb Mock of CarRequestBean
     */
    public InstructionProcessBean(InstructionRequestBean irb, CarRequestBean crb){
        this.instructionRequestBean = irb;
        this.carRequestBean = crb;
    }

    /**
     * If customer and car exist create new Instruction.
     * @param email customer's email
     * @param license car's license
     * @param instruction instruction to be saved
     * @return String return page
     */
    public String executeProcess(String email, String license, Instruction instruction) {
        if (!customerExists(email) || !carExists(license)){
            return returnPage;
        }
        else {
            createInstruction(car, instruction);
        }
        return "instructionConfirmed";
    }

    /**
     * Check if customer exists in DB.
     * @param email customer's email
     * @return boolean exists
     */
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

    /**
     * Check if car exists in DB.
     * @param license car's license
     * @return boolean exists
     */
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

    /**
     * Create instruction in DB.
     * @param car car the instruction relates too
     * @param instruction instruction to be saved
     * @return boolean created
     */
    private boolean createInstruction(Car car, Instruction instruction){
        instruction.setCar(car);
        return instructionRequestBean.createInstruction(instruction);
    }

    public void markCarReady(Instruction instruction){
        instructionRequestBean.markReadyForSample(instruction);
        //to implement

    }

}
