package beans;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import example.ApkCaller;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import persist.Car;
import persist.Instruction;
import util.InstructionStatus;

/**
 * Created by Remco on 19-11-2015.
 * <p/>
 * Handles the process of creating an instruction.
 */
@Getter
@Setter
@NoArgsConstructor
@Stateful
public class InstructionProcessBean {

    @EJB
    private InstructionRequestBean instructionRequestBean;
    @EJB
    private CarRequestBean carRequestBean;
    @EJB
    private ApkCaller apkCaller;

    private String returnPage;
    private Car car;
//    private Customer customer;

    /**
     * If customer and car exist create new Instruction.
     *
     * @param email       customer's email
     * @param license     car's license
     * @param instruction instruction to be saved
     * @return String return page
     */
    public String executeProcess(String email, String license, Instruction instruction) {
        if (!customerExists(email) || !carExists(license)) {
            return returnPage;
        } else {
            createInstruction(car, instruction);
        }
        return FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() + "/customerOverview.xhtml";
    }

    /**
     * Check if customer exists in DB.
     *
     * @param email customer's email
     * @return boolean exists
     */
    private boolean customerExists(String email) {
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

    /**
     * Create instruction in DB.
     *
     * @param car         car the instruction relates too
     * @param instruction instruction to be saved
     * @return boolean created
     */
    private boolean createInstruction(Car car, Instruction instruction) {
        instruction.setCar(car);
        instruction.setSample(false);
        return instructionRequestBean.createInstruction(instruction);
    }

    /**
     * Will check if the car had an APK test or not.
     * If it had sends a message to the RDW to notify that the car is ready for a Sample.
     * The RDW will return a true or false message to mark the car.
     * Finally changes the state of the car.
     *
     * @param instruction the instruction that is updated.
     */
    public void endInstruction(Instruction instruction) {
        if (!instruction.isApk()) {
            instructionRequestBean.alterInstructionStatus(instruction, InstructionStatus.DONE);
        } else {
            if (apkCaller.markReadyForSample(instruction.getCar())) { //SOAP CALL TO RDW
                instructionRequestBean.alterInstructionStatus(instruction, InstructionStatus.SAMPLE);
            } else {
                instructionRequestBean.alterInstructionStatus(instruction, InstructionStatus.DONE);
            }
        }
    }
}
