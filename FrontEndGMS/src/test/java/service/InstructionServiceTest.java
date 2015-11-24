package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import beans.ActivityRequestBean;
import beans.InstructionProcessBean;
import beans.InstructionRequestBean;
import org.junit.Before;
import org.junit.Test;
import persist.Activity;
import persist.Instruction;
import util.InstructionStatus;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Remco on 21-11-2015.
 */
public class InstructionServiceTest {

    private InstructionProcessBean instructionProcessBean;
    private InstructionRequestBean instructionRequestBean;
    private ActivityRequestBean activityRequestBean;
    private InstructionService instructionService;
    private SimpleDateFormat df;

    @Before
    public void setUp() {
        instructionProcessBean = mock(InstructionProcessBean.class);
        instructionRequestBean = mock(InstructionRequestBean.class);
        activityRequestBean = mock(ActivityRequestBean.class);
        instructionService = new InstructionService();
        instructionService.setInstructionProcessBean(instructionProcessBean);
        instructionService.setInstructionRequestBean(instructionRequestBean);
        instructionService.setActivityRequestBean(activityRequestBean);
        df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    }

    @Test
    public void testCreateInstruction() {
        when(instructionProcessBean.executeProcess(null, null, null)).thenReturn("returnPage");
        assertThat(instructionService.createInstruction(), is("returnPage"));
    }

    @Test
    public void testIsValidAssignDateNoAPKValid() throws ParseException {
        Date dateNoAPK = df.parse("22-11-2500 15:00");
        Instruction instruction = new Instruction(dateNoAPK, 0, false, "Test");
        instructionService.setInstruction(instruction);
        assertThat(instructionService.isValidAssignDate(null, null, dateNoAPK), is(true));
    }

    @Test(expected = ValidatorException.class)
    public void testIsValidAssignDateNoAPKEarly() throws ParseException {
        Date dateEarly = df.parse("22-11-2500 06:59");
        Instruction instruction = new Instruction(dateEarly, 0, false, "Test");
        instructionService.setInstruction(instruction);
        assertThat(instructionService.isValidAssignDate(null, null, dateEarly), is(false));
    }

    @Test(expected = ValidatorException.class)
    public void testIsValidAssignDateNoAPKLate() throws ParseException {
        Date dateLate = df.parse("22-11-2500 18:00");
        Instruction instruction = new Instruction(dateLate, 0, false, "Test");
        instructionService.setInstruction(instruction);
        assertThat(instructionService.isValidAssignDate(null, null, dateLate), is(false));
    }

    @Test
    public void testIsValidAssignDateAPKValid() throws ParseException {
        Date dateAPK = df.parse("22-11-2500 11:00");
        Instruction instruction = new Instruction(dateAPK, 0, true, "Test");
        instructionService.setInstruction(instruction);
        assertThat(instructionService.isValidAssignDate(null, null, dateAPK), is(true));
    }

    @Test(expected = ValidatorException.class)
    public void testIsValidAssignDateAPKEarly() throws ParseException {
        Date dateEarly = df.parse("22-11-2500 06:59");
        Instruction instruction = new Instruction(dateEarly, 0, true, "Test");
        instructionService.setInstruction(instruction);
        assertThat(instructionService.isValidAssignDate(null, null, dateEarly), is(false));
    }

    @Test(expected = ValidatorException.class)
    public void testIsValidAssignDateAPKLate() throws ParseException {
        Date dateAPKLate = df.parse("22-11-2500 13:00");
        Instruction instruction = new Instruction(dateAPKLate, 0, true, "Test");
        instructionService.setInstruction(instruction);
        assertThat(instructionService.isValidAssignDate(null, null, dateAPKLate), is(false));
    }

    @Test
    public void testGetOpenInstructions(){
        ArrayList<Instruction> list = new ArrayList<>();
        list.add(new Instruction());
        when(instructionRequestBean.getOpenInstructions()).thenReturn(list);
        assertThat(instructionService.getOpenInstructions(), is(list));
    }

    @Test
    public void testStartInstruction(){
        Instruction instruction = new Instruction();
        instructionService.startInstruction(instruction);
    }

    @Test
    public void testEndInstruction(){
        Instruction instruction = new Instruction();
        instructionService.endInstruction(instruction);
    }

    @Test
    public void testViewInstruction(){
        Instruction instruction = new Instruction();
        assertThat(instructionService.viewInstruction(instruction), is("instructionView"));
        assertThat(instructionService.getInstruction(), is(instruction));
    }

    @Test
    public void testAddNewActivity(){
        ActivityRequestBean arb = mock(ActivityRequestBean.class);
        Instruction instruction = new Instruction();
        instructionService.setInstruction(instruction);
        instructionService.setActivityRequestBean(arb);
        instruction.setActivities(new ArrayList<>());
        instructionService.addNewActivity();
        assertNotNull(instructionService.getInstruction().getActivities().get(0));
    }

    @Test
    public void testEditActivity(){
        Activity activity = new Activity();
        activity.setEdited(false);
        instructionService.editActivity(activity);
        assertThat(activity.isEdited(), is(true));
    }

    @Test
    public void testSaveActivity(){
        Activity activity = new Activity();
        activity.setEdited(true);
        instructionService.saveActivity(activity);
        assertThat(activity.isEdited(), is(false));
    }

    @Test
    public void testRemoveActivity(){
        Instruction instruction = new Instruction();
        ArrayList<Activity> list = new ArrayList<>();
        Activity activity = new Activity();
        list.add(activity);
        instruction.setActivities(list);
        instructionService.setInstruction(instruction);
        instructionService.removeActivity(activity);
        assertThat(instruction.getActivities().isEmpty(), is(true));
    }
}
