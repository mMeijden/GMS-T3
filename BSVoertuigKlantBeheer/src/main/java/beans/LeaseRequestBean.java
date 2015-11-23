package beans;


import java.io.Serializable;

import javax.ejb.Stateful;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import persist.LeasingCompany;
import repo.LeaseRepository;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Getter
@Setter
@Stateful
public class LeaseRequestBean implements Serializable {

    @Inject
    private LeaseRepository leaseRepository;


    /**
     * Add LeasingCompany too DB.
     * @param leasingCompany the leasingcomapny to add
     * @return boolean succeeded
     */
    public boolean addLeasingCompany(LeasingCompany leasingCompany) {
        leaseRepository.add(leasingCompany);
        leaseRepository.save();
        return true;
    }

}
