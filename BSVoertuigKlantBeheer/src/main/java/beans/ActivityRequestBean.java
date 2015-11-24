package beans;

import javax.ejb.Stateful;
import javax.inject.Inject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import persist.Activity;
import repo.ActivityRepository;

/**
 * Created by Remco on 24-11-2015.
 */
@Getter
@Setter
@NoArgsConstructor
@Stateful
public class ActivityRequestBean {

    @Inject
    private ActivityRepository activityRepository;

    /**
     * Save new activity in the DB.
     * @param activity activity to save
     * @return booleaan succeeded
     */
    public boolean createActivity(Activity activity){
        activityRepository.add(activity);
        activityRepository.save();
        return true;
    }

    /**
     * Update activity in the DB.
     * @param activity activity to update
     * @return boolean succeeded
     */
    public boolean updateActivity(Activity activity){
        activityRepository.update(activity);
        return true;
    }

    /**
     * Delete activity in the DB.
     * @param activity activity to delete
     * @return boolean succeeded
     */
    public boolean deleteActivity(Activity activity){
        activityRepository.delete(activity);
        return true;
    }
}
