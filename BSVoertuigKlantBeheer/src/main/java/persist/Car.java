package persist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car extends AbstractPersistentEntity implements Serializable {

    @Id
    private String license;
    @NotNull
    private String brand;
    @NotNull
    private String type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car")
    private List<Instruction> instructions;

}
