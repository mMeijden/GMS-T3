package repo;

import java.util.List;

import javax.ejb.Stateful;

import persist.Instruction;

/**
 * Created by Remco on 19-11-2015.
 */
@Stateful
public class InstructionRepository extends AbstractRepository<Instruction> {

    @Override
    public List<Instruction> getAll() {
        return super.getAll(Instruction.class);
    }
}
