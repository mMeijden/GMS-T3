package persist;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import util.InstructionStatus;

/**
 * Created by Remco on 19-11-2015.
 * <p/>
 * Model for instructions.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GMS_INSTRUCTION")
@Entity
@NamedQueries(
        @NamedQuery(
                name = "getOpenInstructions",
                query = "SELECT i FROM Instruction i "
                        + "WHERE i.status NOT IN (:done, :closed) ORDER BY i.assignDate"
        )
)
public class Instruction extends AbstractPersistentEntity implements Serializable{

    @NotNull
    @Future
    private Date assignDate;
    @NotNull
    private int mileage;
    @NotNull
    private boolean apk;
    @NotNull
    private boolean sample;
    @NotNull
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private InstructionStatus status;

    @ManyToOne
    @JoinColumn(name = "CAR_ID")
    private Car car;

    /**
     * Constructor.
     * @param date the date the instruction starts
     * @param mileage the mileage of the vehicle
     * @param apk does the vehicle come for APK yes/no?
     * @param description description of the activities that need to be performed
     */
    public Instruction(Date date, int mileage, boolean apk, String description){
        this.assignDate = date;
        this.mileage = mileage;
        this.apk = apk;
        this.sample = false;
        this.description = description;
        this.status = InstructionStatus.OPEN;
    }
}
