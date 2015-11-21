package service;

import beans.InstructionProcessBean;
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
    private InstructionService instructionService;

    @Before
    public void setUp(){
        instructionProcessBean = mock(InstructionProcessBean.class);
        instructionService = new InstructionService(instructionProcessBean);
    }

    @Test
    public void testCreateInstruction(){
        when(instructionProcessBean.executeProcess(null, null, null)).thenReturn("returnPage");
        assertThat(instructionService.createInstruction(), is("returnPage"));
    }

}
