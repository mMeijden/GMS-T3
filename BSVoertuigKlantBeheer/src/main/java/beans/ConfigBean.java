package beans;

import persist.Car;
import persist.Customer;
import persist.Instruction;
import persist.LeasingCompany;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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



        Car dummyCar = Car.builder()
                .brand("PEUGEOT")
                .license("27NXTX")
                .type("307 1.6V HATCHBACK")
                .instructions(null)
                .customer(null)
                .leasingCompany(null)
                .build();


        Customer dummyCustomer2 = Customer.builder()
                .firstName("Remco")
                .middleName(null)
                .lastName("Westerhoud")
                .city("Den Haag")
                .email("remcowesterhoud@gmail.com")
                .phone("0612345678")
                .streetName("Bierkade")
                .streetNumber("148t")
                .zipCode("2264AW")
                .cars(new ArrayList<>())
                .build();

        List<Car> cars = new ArrayList<>();
        cars.add(dummyCar);

        Customer dummyCustomer1 = Customer.builder()
                .firstName("Matthijs")
                .lastName("Meijden")
                .middleName("van der")
                .city("Leidschendam")
                .email("luppie123@gmail.com")
                .phone("0683231658")
                .streetName("Sint Jozefstraat")
                .streetNumber("35")
                .zipCode("2264xw")
                .cars(cars)
                .build();

        Customer dummyCustomer3 = Customer.builder()
                .firstName("Laurens")
                .middleName(null)
                .lastName("Oomen")
                .city("Sassenheim")
                .email("workaround@gmail.com")
                .phone("0612345676")
                .streetName("PauperDorpstraat")
                .streetNumber("100")
                .zipCode("2289LK")
                .cars(null)
                .build();


        LeasingCompany dummyCompany1 = LeasingCompany.builder()
                .companyName("HOOGVLIET")

                .city("Alphen a/d Rijn")
                .email("info@hoogvliet.com")
                .phone("0172418224")
                .streetName("Maatschapslaan")
                .streetNumber("128a")
                .zipCode("1547DK")
                .cars(cars)
                .build();

        LeasingCompany dummyCompany2 = LeasingCompany.builder()
                .companyName("WORKAROUND")
                .city("Leiden")
                .email("info@workaround")
                .phone("0694090921")
                .streetName("teststraat")
                .streetNumber("1a")
                .zipCode("1547DW")
                .cars(null)
                .build();


        dummyCar.setCustomer(dummyCustomer1);
        em.persist(dummyCar);

        em.persist(dummyCompany1);
        em.persist(dummyCompany2);
        em.persist(dummyCustomer1);
        em.persist(dummyCustomer2);
        em.persist(dummyCustomer3);
        em.flush();


    }
}
