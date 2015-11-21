package beans;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.Serializable;

/**
 * Created by @author Matthijs van der Meijden on 19-11-2015.
 */
@Startup
@Singleton
public class ConfigBean implements Serializable {
//
//    @Inject
//    private CarRequestBean carRequestBean;
//    @Inject
//    private CustomerRequestBean customerRequestBean;
//
//    @PersistenceContext(unitName = "PizzaOracleDomain")
//    private EntityManager em;
//
//    private static final int AMOUNT = 100;
//    private final SecureRandom r = new SecureRandom();
//
//    /**
//     * Initialize dummy data.
//     */
//    @PostConstruct
//    public void init() {
//
//        generateDummyData();
//    }
//
//    private void generateDummyData() {
//        Ingredient peperoni = Ingredient.builder()
//                .name("Peperoni")
//                .amount(r.nextInt(AMOUNT))
//                .measurementType(Ingredient.MeasurementType.GRAM)
//                .extraPrice(r.nextDouble() * AMOUNT)
//                .build();
//        Ingredient sauce = Ingredient.builder()
//                .name("Tomato Sauce")
//                .amount(r.nextInt(AMOUNT))
//                .measurementType(Ingredient.MeasurementType.MILLILITER)
//                .extraPrice(r.nextDouble() * AMOUNT)
//                .build();
//        Ingredient pineapple = Ingredient.builder()
//                .name("Pineapple")
//                .amount(r.nextInt(AMOUNT))
//                .measurementType(Ingredient.MeasurementType.GRAM)
//                .extraPrice(r.nextDouble() * AMOUNT)
//                .build();
//        Ingredient ham = Ingredient.builder()
//                .name("Ham")
//                .amount(r.nextInt(AMOUNT))
//                .measurementType(Ingredient.MeasurementType.GRAM)
//                .extraPrice(r.nextDouble() * AMOUNT)
//                .build();
//
//        em.persist(
//                Pizza.builder()
//                        .name("Margherita")
//                        .ingredients(
//                                Arrays.asList(
//                                        peperoni,
//                                        sauce
//                                )
//                        )
//                        .price(r.nextDouble() * AMOUNT)
//                        .build()
//        );
//
//        em.persist(
//                Pizza.builder()
//                        .name("Hawaii")
//                        .ingredients(
//                                Arrays.asList(
//                                        pineapple,
//                                        ham,
//                                        sauce
//                                )
//                        )
//                        .price(r.nextDouble() * AMOUNT)
//                        .build()
//        );
//
//        em.flush();
//
//    }
//}
//


}
