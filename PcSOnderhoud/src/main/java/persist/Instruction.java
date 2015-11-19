package persist;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Remco on 19-11-2015.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instruction {

    @NotNull
    @Future
    private Date date;
    @NotNull
    private int mileage;
    @NotNull
    private boolean apk;
    private boolean sample;
    @NotNull
    private String instructionDescription;
}
