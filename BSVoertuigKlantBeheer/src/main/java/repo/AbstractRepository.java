package repo;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import persist.AbstractPersistentEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by alex on 11/9/15.
 * <p/>
 * An abstract repository which manages AbstractPersistentEntity's.
 *
 * @param <T> the generic type.
 */
@Getter
@Setter
public abstract class AbstractRepository<T extends AbstractPersistentEntity> {

    @PersistenceContext(name = "my-persistence-unit")
    private EntityManager em;

    @Getter(AccessLevel.PROTECTED)
    private List<T> itemList = new ArrayList<>();

    /**
     * Retrieve all items.
     */
    public abstract List<T> getAll();

    /**
     * Find an item by it's id attribute.
     * @param idToFind id of object
     * @return object
     */
    public abstract T findById(final Long idToFind);

    /**
     * Persist all items in this repository.
     */
    public void save() {
        itemList.forEach(getEm()::persist);
        getEm().flush();
        itemList.clear();
    }

    /**
     * Add an item to this repository.
     * <p/>
     * Note: call {@link #save()} to persist added items.
     *
     * @param item the item to add.
     */
    public void add(final T item) {
        itemList.add(item);
    }


    /**
     * Update an item in this repository.
     *
     * @param item the item to update.
     */
    public void update(final T item) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(item);
        if(constraintViolations.size() > 0){
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while(iterator.hasNext()){
                ConstraintViolation<T> cv = iterator.next();
                String message = cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage();
                System.err.println(message);
            }
        }
        else {
            getEm().merge(item);
        }
    }

    /**
     * Delete an item in this repository.
     *
     * @param item the item to delete
     */
    public void delete(final T item) {
        T t = getEm().merge(item);
        getEm().remove(t);
    }

    /**
     * Retrieve all items from the persistence unit.
     *
     * @param clazz the class type of item
     * @return a list of items, empty if none found
     */
    protected List<T> getAll(final Class<T> clazz) {
        List<T> response;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> t = cq.from(clazz);
        cq.select(t);
        TypedQuery<T> q = em.createQuery(cq);
        response = q.getResultList();
        return response;
    }

    /**
     * Find an item by its id attribute.
     *
     * @param clazz    the item's class.
     * @param idToFind the id to find
     * @return the item, or null
     */
    protected T findById(final Class<T> clazz, final Long idToFind) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);

        cq.where(
                cb.equal(root.get("id"), idToFind)
        );
        TypedQuery<T> q = em.createQuery(cq);
        return q.getSingleResult();
    }
}
