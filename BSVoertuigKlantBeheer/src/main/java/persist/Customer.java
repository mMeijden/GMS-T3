package persist;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
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


    @OneToOne(mappedBy = "customer")
    private LeasingCompany leasingCompany;

}