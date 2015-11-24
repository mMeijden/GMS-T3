package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import beans.InstructionProcessBean;
import beans.InstructionRequestBean;
import org.junit.Before;
import org.junit.Test;
import persist.Instruction;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Remco on 21-11-2015.
 */
public class InstructionServiceTest {

    private InstructionProcessBean instructionProcessBean;
    private InstructionRequestBean instructionRequestBean;
    private InstructionService instructionService;
    private SimpleDateFormat df;

    @Before
    public void setUp() {
        instructionProcessBean = mock(InstructionProcessBean.class);
        instructionRequestBean = mock(InstructionRequestBean.class);
        instructionService = new InstructionService(instructionProcessBean, instructionRequestBean);
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
}
