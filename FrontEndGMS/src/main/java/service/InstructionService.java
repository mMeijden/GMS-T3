package service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.validation.constraints.AssertTrue;

import beans.ActivityRequestBean;
import beans.InstructionProcessBean;
import beans.InstructionRequestBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import persist.Activity;
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
    @EJB
    private ActivityRequestBean activityRequestBean;

    private String license;
    private String email;
    private Instruction instruction;
    private Activity activity;

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

    /**
     * Add new activity too the instruction.
     */
    public void addNewActivity(){
        Activity new_activity = new Activity();
        new_activity.setEdited(true);
        new_activity.setAssignDate(new Date());
        new_activity.setHoursSpent(0);
        new_activity.setInstruction(instruction);
        instruction.getActivities().add(new_activity);
        activityRequestBean.createActivity(new_activity);
    }

    /**
     * Make activity editable.
     */
    public void editActivity(Activity activity){
        activity.setEdited(true);
    }

    /**
     * Save activity changes in DB.
     * @param activity the activity which needs to be saved
     */
    public void saveActivity(Activity activity){
        activity.setEdited(false);
        activityRequestBean.updateActivity(activity);
    }

    /**
     * Remove activity from instruction.
     * @param activity the activity which needs to be removed
     */
    public void removeActivity(Activity activity){
        instruction.getActivities().remove(activity);
        activityRequestBean.deleteActivity(activity);
    }

    /**
     * Sign off activity.
     * @param activity the activity which needs to be signed off
     */
    public void signOffActivity(Activity activity){
        activity.setSignOffDate(new Date());
        activityRequestBean.updateActivity(activity);
    }

    /**
     * Get today's date for the calendar.
     * @return Date today's date
     */
    public Date getToday(){
        return new Date();
    }
}
