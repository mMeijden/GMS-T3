package persist;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GMS_CAR")
@Entity
@NamedQueries({
        @NamedQuery(
                name = "findByLicense",
                query = "SELECT c FROM Car c WHERE c.license = :license"
        )
})
public class Car extends AbstractPersistentEntity implements Serializable {

    @NotNull
    private String license;
    @NotNull
    private String brand;
    @NotNull
    private String type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car")
    private List<Instruction> instructions;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER_ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name= "LEASECOMPANY_ID")
    private LeasingCompany leasingCompany;
}
