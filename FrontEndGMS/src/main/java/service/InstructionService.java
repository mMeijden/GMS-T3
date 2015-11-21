package service;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import beans.InstructionProcessBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import persist.Instruction;

/**
 * Created by Remco on 19-11-2015.
 */
@Getter
@Setter
@NoArgsConstructor
@ManagedBean(name = "instructionService")
@RequestScoped
public class InstructionService {

    @EJB
    private InstructionProcessBean instructionProcessBean;

    private String license;
    private String email;
    private Instruction instruction;

    /**
     * Constructor for testing purposes.
     * @param ipb Mock of InstructionProcessBean
     */
    public InstructionService(InstructionProcessBean ipb){
        this.instructionProcessBean = ipb;
    }

    /**
     * Initialize bean.
     */
    @PostConstruct
    public void init(){
        instruction = new Instruction();
    }

    /**
     * Create the instruction.
     * @return redirection page
     */
    public String createInstruction(){
        String s =  instructionProcessBean.executeProcess(email, license, instruction);
        return s;
    }
}
