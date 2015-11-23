package persist;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.*;

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
    @OneToOne(mappedBy = "leasingCompany")
    private Customer customer;
    @NotNull
    private String streetName;
    @NotNull
    private long streetNumber;
    @NotNull
    private String zipCode;
    @NotNull
    private String city;
    @NotNull
    private String phone;
    @NotNull
    private String email;
}
