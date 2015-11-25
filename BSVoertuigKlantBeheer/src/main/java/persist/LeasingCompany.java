package persist;


import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class LeasingCompany extends AbstractPersistentEntity implements Serializable {

    @NotNull
    private String companyName;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leasingCompany")
    private List<Car> cars;

}
