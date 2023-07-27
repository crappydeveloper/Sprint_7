import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.restassured.RestAssured;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import ru.yandex.praktikum.Courier;
import ru.yandex.praktikum.Service;
import ru.yandex.praktikum.api.client.CourierClient;


public class CourierLoginTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = Service.BASE_URI;
        CourierClient.getCreateCourierResponse(new Courier("sadfss", "1qaz2wsx", "gusenitsa"));
    }

    @Test
    @DisplayName("Авторизация курьера")
    public void loginCourierTest() {
        Response loginCourierResponse = CourierClient.getLoginCourierResponse(new Courier("sadfss", "1qaz2wsx"));

        loginCourierResponse.then().assertThat().statusCode(200)
                .and()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Попытка авторизации курьера с несуществующим логином")
    public void loginNonexistentCourierTest() {
        Response loginNonexistentCourierResponse = CourierClient.getLoginCourierResponse(new Courier("yhrbvferdsgruy", "1qaz2wsxx"));

        loginNonexistentCourierResponse.then().assertThat().statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Попытка авторизации курьера с некорректным паролем")
    public void loginCourierIncorrectDataTest() {
        Response loginCourierIncorrectDataResponse = CourierClient.getLoginCourierResponse(new Courier("sadfss", "1qaz2wsxx"));

        loginCourierIncorrectDataResponse.then().assertThat().statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Попытка авторизации курьера без указания необходимых полей")
    public void loginCourierNoRequiredFieldTest() {
        Response loginCourierNoRequiredFieldResponse = CourierClient.getLoginCourierResponse(new Courier("sadfss", ""));

        loginCourierNoRequiredFieldResponse.then().assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @After
    public void tearDown() {
        CourierClient.deleteCourierByLoginAndPassword(new Courier("sadfss", "1qaz2wsx"));
    }
}