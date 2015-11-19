package beans;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Stateful;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import persist.Car;
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

    /**
     * Create new instruction.
     *
     * @param car         the car the instruction is assigned too
     * @param assignDate  the date the instruction starts
     * @param mileage     the mileage of the vehicle
     * @param apk         does the vehicle come for APK yes/no?
     * @param sample      will the APK be confirmed by the RDW yes/no?
     * @param description description of the activities that need to be performed
     * @return boolean succeeded
     */
    public boolean createInstruction(Car car, Date assignDate, int mileage, boolean apk, boolean sample, String description) {
        Instruction instruction = new Instruction(assignDate, mileage, apk, sample, description, car);
        instructionRepository.add(instruction);
        instructionRepository.save();
        return true;
    }
}
