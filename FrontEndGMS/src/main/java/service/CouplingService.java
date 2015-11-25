package service;

import beans.CarRequestBean;
import beans.CouplingProcessBean;
import beans.CustomerRequestBean;
import lombok.Getter;
import lombok.Setter;
import persist.Car;
import persist.Customer;

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
