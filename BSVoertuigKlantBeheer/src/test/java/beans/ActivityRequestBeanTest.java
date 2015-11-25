package beans;

import org.junit.Before;
import org.junit.Test;
import persist.Activity;
import repo.ActivityRepository;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by Remco on 24-11-2015.
 */
public class ActivityRequestBeanTest {

    private ActivityRepository ar;
    private ActivityRequestBean arb;
    private Activity activity;

    @Before
    public void setUp(){
        ar = mock(ActivityRepository.class);
        arb = new ActivityRequestBean();
        arb.setActivityRepository(ar);
        activity = new Activity();
    }

    @Test
    public void testCreateActivity(){
        assertThat(arb.createActivity(activity), is(true));
    }

    @Test
    public void testUpdateActivity(){
        assertThat(arb.updateActivity(activity), is(true));
    }

    @Test
    public void testDeleteActivity(){
        assertThat(arb.deleteActivity(activity), is(true));
    }
}
