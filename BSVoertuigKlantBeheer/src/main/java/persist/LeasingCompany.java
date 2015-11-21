package persist;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class LeasingCompany extends GenericCustomer {

    @NotNull
    private String companyName;
    @OneToOne(mappedBy = "leasingCompany")
    private Customer customer;

}
