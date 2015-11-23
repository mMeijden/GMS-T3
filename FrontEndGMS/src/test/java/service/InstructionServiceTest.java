package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Before
    public void setUp(){
        instructionProcessBean = mock(InstructionProcessBean.class);
        instructionRequestBean = mock(InstructionRequestBean.class);
        instructionService = new InstructionService(instructionProcessBean, instructionRequestBean);
    }

    @Test
    public void testCreateInstruction(){
        when(instructionProcessBean.executeProcess(null, null, null)).thenReturn("returnPage");
        assertThat(instructionService.createInstruction(), is("returnPage"));
    }

    @Test
    public void testIsValidAssignDateNoAPK() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        Date dateNoAPK = df.parse("22-11-2500 15:00");
        Instruction instruction = new Instruction(dateNoAPK, 0, false, false, "Test");
        instructionService.setInstruction(instruction);
        assertThat(instructionService.isValidAssignDate(), is(true));

        Date dateEarly = df.parse("22-11-2500 06:59");
        instruction.setAssignDate(dateEarly);
        assertThat(instructionService.isValidAssignDate(), is(false));

        Date dateLate = df.parse("22-11-2500 18:00");
        instruction.setAssignDate(dateLate);
        assertThat(instructionService.isValidAssignDate(), is(false));
    }

    @Test
    public void testIsValidAssignDateAPK() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        Date dateAPK = df.parse("22-11-2500 11:00");
        Instruction instruction = new Instruction(dateAPK, 0, true, false, "Test");
        instructionService.setInstruction(instruction);
        assertThat(instructionService.isValidAssignDate(), is(true));

        Date dateEarly = df.parse("22-11-2500 06:59");
        instruction.setAssignDate(dateEarly);
        assertThat(instructionService.isValidAssignDate(), is(false));

        Date dateAPKLate = df.parse("22-11-2500 13:00");
        instruction.setAssignDate(dateAPKLate);
        assertThat(instructionService.isValidAssignDate(), is(false));
    }
}
