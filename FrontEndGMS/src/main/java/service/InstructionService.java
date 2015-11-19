package service;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import beans.InstructionProcessBean;
import lombok.Getter;
import lombok.Setter;

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
    private Date assignDate;
    private int mileage;
    private boolean apk;
    private String description;

    public void createInstruction(){
        instructionProcessBean.createInstruction(license, assignDate, mileage, apk, false, description);
    }
}
