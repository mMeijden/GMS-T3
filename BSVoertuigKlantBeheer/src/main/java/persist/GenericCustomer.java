package persist;

import lombok.*;

import javax.persistence.MappedSuperclass;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class GenericCustomer extends AbstractPersistentEntity {

    private String streetName;
    private long streetNumber;
    private String zipCode;
    private String City;
    private String phone;
    private String email;

}
