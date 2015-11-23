package beans;

import org.junit.Before;
import org.junit.Test;
import persist.LeasingCompany;
import repo.LeaseRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

/**
 * Created by Remco on 23-11-2015.
 */
public class LeaseRequestBeanTest {

    private LeaseRepository leaseRepository;
    private LeaseRequestBean leaseRequestBean;
    private LeasingCompany leasingCompany;

    @Before
    public void setUp(){
        leaseRepository = mock(LeaseRepository.class);
        leaseRequestBean = new LeaseRequestBean();
        leaseRequestBean.setLeaseRepository(leaseRepository);
        leasingCompany = new LeasingCompany();
    }

    @Test
    public void testAddLeasingCompany(){
        assertThat(leaseRequestBean.addLeasingCompany(leasingCompany), is(true));
    }
}
