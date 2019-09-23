package facades;

import java.time.LocalDate;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//@Disabled
public class TheFacadeTest {

    private static EntityManagerFactory emf;
    private static TheFacade facade;
//    private static Car m1 = new Car(1980, "Volvo", "m1", 2000, LocalDate.of(2012, 12, 05), "F", "N");
//    private static Car m2 = new Car(1975, "BMW", "m2", 2010, LocalDate.of(2013, 11, 05), "E", "S");

    public TheFacadeTest() {
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMMENDED strategy
     */
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = TheFacade.getTheFacade(emf);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Ordrer.deleteAllRows").executeUpdate();
//            em.persist(m1);
//            em.persist(m2);

            em.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            em.close();
        }
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }


}
