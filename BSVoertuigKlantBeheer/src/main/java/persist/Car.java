package persist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car extends AbstractPersistentEntity implements Serializable {

    @NotNull
    private String license;
    @NotNull
    private String brand;
    @NotNull
    private String type;


}
