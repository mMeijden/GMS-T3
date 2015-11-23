package service;


import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.AssertTrue;

import beans.InstructionProcessBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import persist.Instruction;

/**
 * Created by Remco on 19-11-2015.
 */
@Getter
@Setter
@NoArgsConstructor
@ManagedBean(name = "instructionService")
@RequestScoped
public class InstructionService {

    @EJB
    private InstructionProcessBean instructionProcessBean;

    private String license;
    private String email;
    private Instruction instruction;

    /**
     * Constructor for testing purposes.
     * @param ipb Mock of InstructionProcessBean
     */
    public InstructionService(InstructionProcessBean ipb){
        this.instructionProcessBean = ipb;
    }

    /**
     * Initialize bean.
     */
    @PostConstruct
    public void init(){
        instruction = new Instruction();
    }

    /**
     * Create the instruction.
     * @return redirection page
     */
    public String createInstruction(){
        String s =  instructionProcessBean.executeProcess(email, license, instruction);
        return s;
    }

    /**
     * Validates if the instruction starts at a valid time.
     * @return boolean valid
     */
    @AssertTrue
    public boolean isValidAssignDate(){
        boolean apk = instruction.isApk();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instruction.getAssignDate());
        int hours = calendar.get(Calendar.HOUR_OF_DAY);

        if (!apk && hours >= 7 && hours <= 17){
            return true;
        }
        else if (apk && hours >= 7 && hours <= 12){
            return true;
        }
        else {
            return false;
        }
    }
}
