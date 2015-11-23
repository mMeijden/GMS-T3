package beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import persist.Instruction;
import repo.InstructionRepository;
import util.InstructionStatus;

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
     *
     * @param ir mock of InstructionRepository class
     */
    public InstructionRequestBean(InstructionRepository ir) {
        this.instructionRepository = ir;
    }

    /**
     * Save new instruction too DB.
     *
     * @param instruction the instruction too save
     * @return boolean succeeded
     */
    public boolean createInstruction(Instruction instruction) {
        instructionRepository.add(instruction);
        instructionRepository.save();
        return true;
    }

    /**
     * Get a list of open instructions.
     *
     * @return list of instructions
     */
    public List<Instruction> getOpenInstructions() {
        List<Instruction> list = instructionRepository.getOpen();
        return list;
    }


    /**
     * Alters the status of an instruction.
     */
    public void alterInstructionStatus(Instruction instruction, InstructionStatus status) {
        instruction.setStatus(status);
        instructionRepository.update(instruction);
    }
}
