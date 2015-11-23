package beans;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Stateful;
import javax.inject.Inject;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Stateful
public class InstructionRequestBean implements Serializable {

    @Inject
    private InstructionRepository instructionRepository;

    /**
     * Constructor for testing purposes.
     * @param ir mock of InstructionRepository class
     */
    public InstructionRequestBean(InstructionRepository ir){
        this.instructionRepository = ir;
    }

    /**
     * Save new instruction too DB.
     * @param instruction the instruction too save
     * @return boolean succeeded
     */
    public boolean createInstruction(Instruction instruction){
        instructionRepository.add(instruction);
        instructionRepository.save();
        return true;
    }
    //TODO: TO IMPLEMENT
    public void enableSample(){

    }

    /**
     * Updates the instruction when the instruction was a MOT test.
     * This method is called after the SOAP call
     * @param instruction the instruction with the updated MOT test value.
     */

    public void markReadyForSample(Instruction instruction){
        instructionRepository.update(instruction);
        }

}
