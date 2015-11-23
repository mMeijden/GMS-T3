package repo;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.NoResultException;

import persist.Instruction;
import util.InstructionStatus;

/**
 * Created by Remco on 19-11-2015.
 */
@Stateful
public class InstructionRepository extends AbstractRepository<Instruction> {

    @Override
    public List<Instruction> getAll() {
        return super.getAll(Instruction.class);
    }

    /**
     * Get list of open instructions.
     *
     * @return list of instructions
     */
    public List<Instruction> getOpen() {
        try {
            return getEm().createNamedQuery("getOpenInstructions", Instruction.class)
                    .setParameter("done", InstructionStatus.DONE)
                    .setParameter("closed", InstructionStatus.CLOSED)
                    .getResultList();
        } catch (NoResultException e) {
            //TODO: Log exception
            return null;
        }
    }
}
