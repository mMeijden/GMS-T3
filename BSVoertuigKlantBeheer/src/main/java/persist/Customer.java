package persist;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
public class Customer extends AbstractPersistentEntity implements Serializable {

    @NotNull
    private String firstName;
    @NotNull
    private String middleName;
    @NotNull
    private String lastName;
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

    @OneToOne
    @JoinColumn(name = "CUSTOMERID")
    private LeasingCompany leasingCompany;

}