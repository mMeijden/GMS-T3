package service;

import beans.CouplingProcessBean;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 * Created by @author Matthijs van der Meijden on 25-11-2015.
 */
@Getter
@Setter
@ManagedBean(name = "couplingService")
@RequestScoped
public class CouplingService {

    private String email;
    private String license;

 @EJB
    CouplingProcessBean couplingProcessBean;


    public void couple(){
        couplingProcessBean.coupleOwnerToCar(license, email);
    }
}
