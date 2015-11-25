package service;

import beans.ActivityRequestBean;
import beans.InstructionProcessBean;
import beans.InstructionRequestBean;
import org.junit.Before;
import org.junit.Test;
import persist.Activity;
import persist.Instruction;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by Remco on 25-11-2015.
 */
public class InstructionViewServiceTest {

    private InstructionViewService instructionViewService;
    private InstructionRequestBean instructionRequestBean;
    private InstructionProcessBean instructionProcessBean;
    private ActivityRequestBean activityRequestBean;

    @Before
    public void setUp(){
        instructionViewService = new InstructionViewService();
        instructionRequestBean = mock(InstructionRequestBean.class);
        instructionProcessBean = mock(InstructionProcessBean.class);
        activityRequestBean = mock(ActivityRequestBean.class);
        instructionViewService.setInstructionProcessBean(instructionProcessBean);
        instructionViewService.setInstructionRequestBean(instructionRequestBean);
        instructionViewService.setActivityRequestBean(activityRequestBean);
    }

    @Test
    public void testStartInstruction(){
        instructionViewService.startInstruction();
    }

    @Test
    public void testEndInstruction(){
        instructionViewService.endInstruction();
    }

    @Test
    public void testViewInstruction(){
        Instruction instruction = new Instruction();
        assertThat(instructionViewService.viewInstruction(instruction), is("instructionView"));
        assertThat(instructionViewService.getInstruction(), is(instruction));
    }

    @Test
    public void testAddNewActivity(){
        ActivityRequestBean arb = mock(ActivityRequestBean.class);
        Instruction instruction = new Instruction();
        instructionViewService.setInstruction(instruction);
        instructionViewService.setActivityRequestBean(arb);
        instruction.setActivities(new ArrayList<>());
        instructionViewService.addNewActivity();
        assertNotNull(instructionViewService.getInstruction().getActivities().get(0));
    }

    @Test
    public void testEditActivity(){
        Activity activity = new Activity();
        activity.setEdited(false);
        instructionViewService.editActivity(activity);
        assertThat(activity.isEdited(), is(true));
    }

    @Test
    public void testSaveActivity(){
        Activity activity = new Activity();
        activity.setEdited(true);
        instructionViewService.saveActivity(activity);
        assertThat(activity.isEdited(), is(false));
    }

    @Test
    public void testRemoveActivity(){
        Instruction instruction = new Instruction();
        ArrayList<Activity> list = new ArrayList<>();
        Activity activity = new Activity();
        list.add(activity);
        instruction.setActivities(list);
        instructionViewService.setInstruction(instruction);
        instructionViewService.removeActivity(activity);
        assertThat(instruction.getActivities().isEmpty(), is(true));
    }
}
