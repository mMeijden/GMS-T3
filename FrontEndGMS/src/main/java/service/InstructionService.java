package service;


import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.AssertTrue;

import beans.InstructionProcessBean;
import beans.InstructionRequestBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import persist.Instruction;
import util.InstructionStatus;

/**
 * Created by Remco on 19-11-2015.
 */
@Getter
@Setter
@NoArgsConstructor
@ManagedBean(name = "instructionService")
@SessionScoped
public class InstructionService {

    @EJB
    private InstructionProcessBean instructionProcessBean;
    @EJB
    private InstructionRequestBean instructionRequestBean;

    private String license;
    private String email;
    private Instruction instruction;

    /**
     * Constructor for testing purposes.
     *
     * @param ipb Mock of InstructionProcessBean
     */
    public InstructionService(InstructionProcessBean ipb, InstructionRequestBean irb) {
        this.instructionProcessBean = ipb;
        this.instructionRequestBean = irb;
    }

    /**
     * Initialize bean.
     */
    @PostConstruct
    public void init() {
        instruction = new Instruction();
        instruction.setStatus(InstructionStatus.OPEN);
    }

    /**
     * Create the instruction.
     *
     * @return redirection page
     */
    public String createInstruction() {
        String s = instructionProcessBean.executeProcess(email, license, instruction);
        return s;
    }

    /**
     * Validates if the instruction starts at a valid time.
     *
     * @return boolean valid
     */
    @AssertTrue
    public boolean isValidAssignDate() {
        boolean apk = instruction.isApk();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instruction.getAssignDate());
        int hours = calendar.get(Calendar.HOUR_OF_DAY);

        if (!apk && hours >= 7 && hours <= 17) {
            return true;
        } else if (apk && hours >= 7 && hours <= 12) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get list of Instructions that aren't closed or done.
     *
     * @return list of instructions
     */
    public List<Instruction> getOpenInstructions() {
        List<Instruction> list = instructionRequestBean.getOpenInstructions();
        return list;
    }

    /**
     * Start an instruction and change it's state.
     *
     * @param instruction instruction to start
     */
    public void startInstruction(Instruction instruction) {
        instructionRequestBean.alterInstructionStatus(instruction, InstructionStatus.IN_PROGRESS);
    }

    /**
     * End instruction and change it's state.
     *
     * @param instruction instruction to end
     */
    public void endInstruction(Instruction instruction) {
        instructionProcessBean.endInstruction(instruction);
    }

    public String viewInstruction(Instruction instruction){
        this.instruction = instruction;
        return "instructionView";
    }
}
