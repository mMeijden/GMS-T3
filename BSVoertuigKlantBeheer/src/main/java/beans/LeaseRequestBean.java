package beans;


import lombok.Getter;
import lombok.Setter;
import persist.Customer;
import persist.LeasingCompany;
import repo.CustomerRepository;
import repo.LeaseRepository;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@Stateful
public class LeaseRequestBean implements Serializable{

    @Inject
    private LeaseRepository leaseRepository;


    public void addLeasingCompany(LeasingCompany leasingCompany){
        leaseRepository.add(leasingCompany);
        leaseRepository.save();

    }

}
