package beans;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import persist.Instruction;
import repo.InstructionRepository;

/**
 * Created by Remco on 19-11-2015.
 * <p/>
 * Handles the database access for instructions.
 */
@Getter
@Setter
@Stateful
public class InstructionRequestBean implements Serializable {

    @Inject
    private InstructionRepository instructionRepository;
    private static final Logger LOGGER = LogManager.getLogger(InstructionRequestBean.class.getName());

    /**
     * Create new instruction.
     * @param assignDate the date the instruction starts
     * @param mileage the mileage of the vehicle
     * @param apk does the vehicle come for APK yes/no?
     * @param sample will the APK be confirmed by the RDW yes/no?
     * @param description description of the activities that need to be performed
     * @return boolean succeeded
     */
    public boolean createInstruction(Date assignDate, int mileage, boolean apk, boolean sample, String description){
        try {
            Instruction instruction = new Instruction(assignDate, mileage, apk, sample, description);
            instructionRepository.add(instruction);
            instructionRepository.save();
            return true;
        }
        catch (EJBException e){
            LOGGER.warn(e.getMessage(), e);
            return false;
        }
    }
}
