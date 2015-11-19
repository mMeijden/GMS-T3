package persist;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@MappedSuperclass
public class GenericCustomer extends AbstractPersistentEntity{

    private String streetName;
    private Long streetNumber;
    private String zipCode;
    private String City;
    private String phone;
    private String email;

}
