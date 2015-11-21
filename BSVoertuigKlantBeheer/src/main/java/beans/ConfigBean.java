package beans;

import persist.Customer;
import persist.LeasingCompany;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Startup
@Singleton
public class ConfigBean implements Serializable {


    @PersistenceContext(unitName = "my-persistence-unit")
    private EntityManager em;

    /**
     * Initialize dummy data.
     */
    @PostConstruct

    public void init() {

        generateDummyData();
    }

    private void generateDummyData() {
        Customer dummyCustomer1 = new Customer();
        dummyCustomer1.setFirstName("Matthijs");
        dummyCustomer1.setLastName("Meijden");
        dummyCustomer1.setMiddleName("van der");
        dummyCustomer1.setCity("Leidschendam");
        dummyCustomer1.setEmail("luppie123@gmail.com");
        dummyCustomer1.setPhone("0683231658");
        dummyCustomer1.setStreetName("Sint Jozefstraat");
        dummyCustomer1.setStreetNumber(35);
        dummyCustomer1.setZipCode("2264xw");


        LeasingCompany dummyCompany1 = LeasingCompany.builder()
                .companyName("HOOGVLIET")
                .customer(dummyCustomer1)
                .city("Alphen a/d Rijn")
                .email("info@hoogvliet.com")
                .phone("0172418224")
                .streetName("Maatschapslaan")
                .streetNumber(128)
                .zipCode("1547DK")
                .build();


        dummyCustomer1.setLeasingCompany(dummyCompany1);
        em.persist(dummyCompany1);
        em.persist(dummyCustomer1);
        em.flush();


    }
}
