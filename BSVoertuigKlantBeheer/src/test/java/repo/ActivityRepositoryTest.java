package repo;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import persist.Activity;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Remco on 24-11-2015.
 */
public class ActivityRepositoryTest {

    private ActivityRepository ar;
    private Activity activity;
    private EntityManager em;

    @Before
    public void setUp(){
        ar = new ActivityRepository();
        activity = new Activity();
        em = mock(EntityManager.class);
        ar.setEm(em);
    }

    @Test
    public void testGetAll(){
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        CriteriaQuery<Activity> cq = mock(CriteriaQuery.class);
        Root<Activity> root = mock(Root.class);
        TypedQuery<Activity> tq = mock(TypedQuery.class);
        ArrayList<Activity> list = new ArrayList<>();
        list.add(activity);

        when(em.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(Activity.class)).thenReturn(cq);
        when(cq.from(Activity.class)).thenReturn(root);
        when(em.createQuery(cq)).thenReturn(tq);
        when(tq.getResultList()).thenReturn(list);

        assertThat(ar.getAll(), is(list));
    }

    @Test
    public void testFindById(){
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        CriteriaQuery<Activity> cq = mock(CriteriaQuery.class);
        Root<Activity> root = mock(Root.class);
        TypedQuery<Activity> tq = mock(TypedQuery.class);
        activity.setId(1l);

        when(em.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(Activity.class)).thenReturn(cq);
        when(cq.from(Activity.class)).thenReturn(root);
        when(em.createQuery(cq)).thenReturn(tq);
        when(tq.getSingleResult()).thenReturn(activity);

        assertThat(ar.findById(1l), is(activity));
    }

    @Test
    public void testDelete(){
        ar.delete(activity);
    }
}
