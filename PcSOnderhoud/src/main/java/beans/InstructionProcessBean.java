package beans;

import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * Created by Remco on 19-11-2015.
 * <p/>
 * Handles the processes of instructions.
 */
@Stateful
public class InstructionProcessBean {

    @EJB
    private InstructionRequestBean instructionRequestBean;
    @EJB
    private CarRequestBean carRequestBean;

    /**
     * TODO fancy javadoc
     * @return boolean succeeded
     */
    public boolean createInstruction(String license){

        return false;
    }
}
