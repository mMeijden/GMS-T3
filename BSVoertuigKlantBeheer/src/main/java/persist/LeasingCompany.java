package persist;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class LeasingCompany extends GenericCustomer {

    @NotNull
    private String companyName;
    @OneToOne(mappedBy = "leasingCompany")
    private Customer customer;


    @Builder
    public LeasingCompany(String streetName, long streetNumber, String zipCode, String city, String phone, String email, String companyName, Customer customer) {
        super(streetName, streetNumber, zipCode, city, phone, email);
        this.companyName = companyName;
        this.customer = customer;
    }

}
