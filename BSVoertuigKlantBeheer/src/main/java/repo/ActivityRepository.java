package repo;

import persist.Activity;

import javax.ejb.Stateful;
import java.util.List;

/**
 * Created by Remco on 24-11-2015.
 */
@Stateful
public class ActivityRepository extends AbstractRepository<Activity> {
    @Override
    public List<Activity> getAll() {
        return super.getAll(Activity.class);
    }

    @Override
    public Activity findById(Long idToFind) {
        return super.findById(Activity.class, idToFind);
    }
}
