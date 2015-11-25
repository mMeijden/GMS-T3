package persist;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@MappedSuperclass
public class GenericCustomer extends AbstractPersistentEntity {
    //TODO; Refactor this class to 0
    private String streetName;
    private Long streetNumber;
    private String zipCode;
    private String City;
    private String phone;
    private String email;

}
