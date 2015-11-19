package service;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import beans.InstructionProcessBean;
import lombok.Getter;
import lombok.Setter;
import persist.Instruction;

/**
 * Created by Remco on 19-11-2015.
 */
@Getter
@Setter
@ManagedBean(name = "instructionService")
@RequestScoped
public class InstructionService {

    @EJB
    private InstructionProcessBean instructionProcessBean;

    private String license;
    private String email;
    private Instruction instruction;

    /**
     * Initialize bean.
     */
    @PostConstruct
    public void init(){
        instruction = new Instruction();
    }

    /**
     * Try and create the instruction.
     * @return redirection page
     */
    public String createInstruction(){
        return instructionProcessBean.executeProcess(email, license, instruction);
    }
}
