package persist;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "CAR_ID")
    private Car car;

    /**
     * Constructor.
     * @param date the date the instruction starts
     * @param mileage the mileage of the vehicle
     * @param apk does the vehicle come for APK yes/no?
     * @param sample will the APK be confirmed by the RDW yes/no?
     * @param description description of the activities that need to be performed
     */
    public Instruction(Date date, int mileage, boolean apk, boolean sample, String description){
        this.assignDate = date;
        this.mileage = mileage;
        this.apk = apk;
        this.sample = sample;
        this.description = description;
    }
}
