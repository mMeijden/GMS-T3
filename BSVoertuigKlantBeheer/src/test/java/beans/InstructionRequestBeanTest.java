package beans;

import org.junit.Before;
import org.junit.Test;
import persist.Instruction;
import repo.InstructionRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

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
}
