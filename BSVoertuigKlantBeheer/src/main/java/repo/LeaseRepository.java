package repo;

import java.util.List;

import javax.ejb.Stateful;

import persist.LeasingCompany;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Stateful
public class LeaseRepository extends AbstractRepository<LeasingCompany> {
    @Override
    public List<LeasingCompany> getAll() {
        return super.getAll(LeasingCompany.class);
    }

    @Override
    public LeasingCompany findById(Long idToFind) {
        return super.findById(LeasingCompany.class, idToFind);
    }
}
