package persist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Remco on 24-11-2015.
 * <p/>
 * Model for activities.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GMS_ACTIVITY")
@Entity
public class Activity extends AbstractPersistentEntity implements Serializable {

    @NotNull
    private Date assignDate;
    private Date signOffDate;
    @Min(0)
    private int hoursSpent;
    private String activities;
    @Transient
    private boolean edited;

    @ManyToOne
    @JoinColumn(name = "INSTRUCTION_ID")
    private Instruction instruction;
}
