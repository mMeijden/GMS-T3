package persist;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GMS_CUSTOMER")
@Entity
@Builder
@NamedQueries({
        @NamedQuery(
                name = "findByEmail",
                query = "SELECT c FROM Customer c WHERE c.email = :email"
        )
})
public class Customer extends AbstractPersistentEntity implements Serializable {

    @NotNull
    private String firstName;
    private String middleName;
    @NotNull
    private String lastName;
    @NotNull
    private String streetName;
    @NotNull
    private String streetNumber;
    @NotNull
    private String zipCode;
    @NotNull
    private String city;
    @NotNull
    private String phone;
    @NotNull
    private String email;

    @Transient
    private boolean edited = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Car> cars;

    public void addCarToList(Car car){
        cars.add(car);
    }
}