package service;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
 * Created by Remco on 25-11-2015.
 */
@Getter
@Setter
@NoArgsConstructor
@ManagedBean(name = "instructionViewService")
@SessionScoped
public class InstructionViewService {

    @EJB
    private ActivityRequestBean activityRequestBean;
    @EJB
    private InstructionRequestBean instructionRequestBean;
    @EJB
    private InstructionProcessBean instructionProcessBean;

    private Instruction instruction;

    public String viewInstruction(Instruction instruction){
        this.instruction = instruction;
        return "instructionView";
    }


    /**
     * Start an instruction and change it's state.
     *
     */
    public void startInstruction() {
        instructionRequestBean.alterInstructionStatus(instruction, InstructionStatus.IN_PROGRESS);
    }
    /**
     * End instruction and change it's state.
     *
     */
    public void endInstruction() {
        instructionProcessBean.endInstruction(instruction);
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
        instructionRequestBean.updateInstruction(instruction);
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

}
