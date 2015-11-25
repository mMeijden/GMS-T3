package service;


import beans.ActivityRequestBean;
import beans.InstructionProcessBean;
import beans.InstructionRequestBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import persist.Activity;
import persist.Instruction;
import util.InstructionStatus;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.validation.constraints.AssertTrue;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    @EJB
    private InstructionRequestBean instructionRequestBean;
    @EJB
    private ActivityRequestBean activityRequestBean;

    private String license;
    private String email;
    private Instruction instruction;
    private Activity activity;

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
        instruction = new Instruction();
        license = "";
        email = "";
        return s;
    }

    /**
     * Validates if the instruction starts at a valid time.
     *
     * @return boolean valid
     */
    @AssertTrue
    public boolean isValidAssignDate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
        boolean apk = instruction.isApk();
        Date date = (Date) value;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);

        if (!apk && (hours <= 7 || hours >= 17)) {
            FacesMessage msg = new FacesMessage("Date validation failed. Date must be after 7am and before 6pm.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } else if (apk && (hours <= 7 || hours >= 12)) {
            FacesMessage msg = new FacesMessage("Date validation failed. Date must be after 7am and before 1pm.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        } else {
            return true;
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
     * Get today's date for the calendar.
     * @return Date today's date
     */
    public Date getToday(){
        return new Date();
    }
}
