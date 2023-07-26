import io.restassured.response.Response;
import io.restassured.RestAssured;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;


public class CourierLoginTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        Courier.createCourier("sadfss", "1qaz2wsx", "gusenitsa");
    }

    @Test
    public void loginCourierTest() {
        Response response = Courier.loginCourier("sadfss", "1qaz2wsx");

        response.then().assertThat().body("id", notNullValue())
                .and()
                .statusCode(200);
    }

    @Test
    public void loginNonexistentCourierTest() {
        Response response = Courier.loginCourier("yhrbvferdsgruy", "1qaz2wsxx");

        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

    @Test
    public void loginCourierIncorrectDataTest() {
        Response response = Courier.loginCourier("sadfss", "1qaz2wsxx");

        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

    @Test
    public void loginCourierNoRequiredFieldTest() {
        Response response = Courier.loginCourier("sadfss", "");

        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }

    @After
    public void tearDown() {
        Courier.deleteCourierByLoginAndPassword("sadfss", "1qaz2wsx");
    }
}