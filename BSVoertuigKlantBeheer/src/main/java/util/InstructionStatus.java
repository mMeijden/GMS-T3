package util;

/**
 * Created by Remco on 23-11-2015.
 */
public enum InstructionStatus {
    OPEN("Open"),
    DONE("Done"),
    CLOSED("Closed"),
    IN_PROGRESS("In progress"),
    APK("APK"),
    SAMPLE("Sample");

    private final String STATUS;

    private InstructionStatus(String status){
        this.STATUS = status;
    }

    public String getSTATUS(){
        return STATUS;
    }
}
