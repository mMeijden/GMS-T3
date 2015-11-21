package persist;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Customer extends GenericCustomer implements Serializable {

    @NotNull
    private String firstName;
    @NotNull
    private String middleName;
    @NotNull
    private String lastName;

    @OneToOne
    @JoinColumn(name = "CUSTOMERID")
    private LeasingCompany leasingCompany;

    //@Builder
    public Customer(String streetName, long streetNumber, String zipCode, String city, String phone, String email,String firstName, String middleName, String lastName) {
        super(streetName, streetNumber, zipCode, city, phone, email);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }


}