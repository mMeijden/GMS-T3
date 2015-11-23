package beans;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import persist.Instruction;
import repo.InstructionRepository;
import util.InstructionStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Remco on 21-11-2015.
 */
public class InstructionRequestBeanTest {

    private InstructionRepository instructionRepository;
    private InstructionRequestBean instructionRequestBean;
    private Instruction instruction;

    @Before
    public void setUp() {
        instructionRepository = mock(InstructionRepository.class);
        instructionRequestBean = new InstructionRequestBean(instructionRepository);
        instruction = new Instruction();
    }

    @Test
    public void testCreateInstruction() {
        assertThat(instructionRequestBean.createInstruction(instruction), is(true));
    }

    @Test
    public void testGetOpenInstructions(){
        ArrayList<Instruction> list = new ArrayList<>();
        instruction.setStatus(InstructionStatus.OPEN);
        list.add(instruction);
        when(instructionRepository.getOpen()).thenReturn(list);

        assertThat(instructionRequestBean.getOpenInstructions(), is(list));
    }

    @Test
    public void testAlterInstructionStatus(){
        instruction.setStatus(InstructionStatus.OPEN);
        doNothing().when(instructionRepository).update(instruction);
        InstructionStatus newStatus = InstructionStatus.DONE;
        instructionRequestBean.alterInstructionStatus(instruction, newStatus);
        assertThat(instruction.getStatus(), is(newStatus));
    }
}
