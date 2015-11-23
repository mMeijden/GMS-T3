package repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import persist.Car;
import persist.Instruction;
import util.InstructionStatus;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Remco on 21-11-2015.
 */
public class InstructionRepositoryTest {

    private InstructionRepository instructionRepository;
    private EntityManager em;
    private ArrayList<Instruction> instructions;

    @Before
    public void setUp() {
        instructionRepository = new InstructionRepository();
        em = mock(EntityManager.class);
        instructionRepository.setEm(em);
    }

    @Test
    public void testGetAll() {
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        CriteriaQuery<Instruction> cq = mock(CriteriaQuery.class);
        Root<Instruction> root = mock(Root.class);
        TypedQuery<Instruction> tq = mock(TypedQuery.class);

        Instruction instruction = new Instruction();
        instructions = new ArrayList<>();
        instructions.add(instruction);

        when(em.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(Instruction.class)).thenReturn(cq);
        when(cq.from(Instruction.class)).thenReturn(root);
        when(em.createQuery(cq)).thenReturn(tq);
        when(tq.getResultList()).thenReturn(instructions);
        assertThat(instructionRepository.getAll(), is(instructions));
    }

    @Test
    public void testAdd() {
        instructionRepository.add(new Instruction());
    }

    @Test
    public void testSave() {
        instructionRepository.save();
    }

    @Test
    public void testGetOpen(){
        TypedQuery<Instruction> tq = mock(TypedQuery.class);
        when(instructionRepository.getEm().createNamedQuery("getOpenInstructions", Instruction.class)).thenReturn(tq);
        when(tq.setParameter("done", InstructionStatus.DONE)).thenReturn(tq);
        when(tq.setParameter("closed", InstructionStatus.CLOSED)).thenReturn(tq);
        when(tq.getResultList()).thenReturn(instructions);

        assertThat(instructionRepository.getOpen(), is(instructions));
    }
}
