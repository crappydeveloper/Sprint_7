import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;


public class CourierCreateTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void createCourierTest() {
        Response response = Courier.createCourier("sadfss", "1qaz2wsx", "gusenitsa");

        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
    }

    @Test
    public void createDuplicateCourierTest() {
        Courier.createCourier("sadfss", "1qaz2wsx", "gusenitsa");
        Response response = Courier.createCourier("sadfss", "1qaz2wsx", "gusenitsa");

        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

    @Test
    public void createCourierNoRequiredFieldTest() {
        Response response = Courier.createCourier("sadfss", "", "gusenitsa");

        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }

    @Test
    public void createCourierLoginDuplicateTest() {
        Courier.createCourier("sadfss", "1qaz2wsx", "gusenitsa");
        Response response = Courier.createCourier("sadfss", "1qazaaa2wsx", "gusenitsaa");

        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

    @After
    public void tearDown() {
        Courier.deleteCourierByLoginAndPassword("sadfss", "1qaz2wsx");
    }
}
