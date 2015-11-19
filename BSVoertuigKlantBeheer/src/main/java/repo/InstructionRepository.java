package repo;

import java.util.List;

import persist.Instruction;

/**
 * Created by Remco on 19-11-2015.
 */
public class InstructionRepository extends AbstractRepository<Instruction> {

    @Override
    public List<Instruction> getAll() {
        return super.getAll(Instruction.class);
    }
}
