package rest;

import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class OrderResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    //Read this line from a settings-file  since used several places
    private static final String TEST_DB = "jdbc:mysql://localhost:3307/caone_test";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
//    private final Car m1 = new Car(1980, "Volvo", "m1", 2000, LocalDate.of(2012, 12, 05), "F", "N");
//    private final Car m2 = new Car(1975, "BMW", "m2", 2010, LocalDate.of(2013, 11, 05), "E", "S");
//    private final Car m3 = new Car(1970, "Toyota", "m3", 2015, LocalDate.of(2011, 1, 1), "D", "A");

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        httpServer = startServer();

        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;

        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Ordrer.deleteAllRows").executeUpdate();
//            em.createNativeQuery("DELETE FROM Car").executeUpdate();

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Create a test that verifies that the server is up (similar to the “Hello
     * World” response)
     */
//    @Test
//    public void testServerIsUp() {
//        System.out.println("testServerIsUp");
//        System.out.println("Testing is server UP");
//        given()
//                .when()
//                .get("api/order")
//                .then()
//                .statusCode(200);
//    }

    /**
     * This test assumes the database contains two rows
     *
     * @throws Exception
     */
//    @Test
//    public void testDummyMsg() throws Exception {
//        System.out.println("testDummyMsg");
//        given()
//                .contentType("application/json")
//                .get("api/order/").then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode())
//                .body("msg", equalTo("Hello World"));
//    }

    /**
     *
     * @throws Exception
     */
//    @Test
//    public void testCount() throws Exception {
//        System.out.println("testCount");
//        given()
//                .contentType("application/json")
//                .get("/cars/count").then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode())
//                .body("count", equalTo(3));
//    }

    /**
     * @throws Exception
     */
//    @Test
//    public void testGetAll() throws Exception {
//        System.out.println("testGetAll");
//        given()
//                .contentType("application/json")
//                .get("/cars/all").then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode())
//                .body("year", hasItems(1980, 1975, 1970));
//    }

}
