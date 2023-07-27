import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import ru.yandex.praktikum.Courier;
import ru.yandex.praktikum.Service;
import ru.yandex.praktikum.api.client.CourierClient;


public class CourierCreateTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = Service.BASE_URI;
    }

    @Test
    @DisplayName("Создание нового курьера")
    public void createCourierTest() {
        Response createCourierResponse = CourierClient.getCreateCourierResponse(new Courier("sadfss", "1qaz2wsx", "gusenitsa"));

        createCourierResponse.then().assertThat().statusCode(201)
                .and()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Попытка создания дубликата существующего курьера")
    public void createDuplicateCourierTest() {
        CourierClient.getCreateCourierResponse(new Courier("sadfss", "1qaz2wsx", "gusenitsa"));
        Response response = CourierClient.getCreateCourierResponse(new Courier("sadfss", "1qaz2wsx", "gusenitsa"));

        response.then().assertThat().statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Попытка создания курьера без указания необходимых полей")
    public void createCourierNoRequiredFieldTest() {
        Response noRequiredFieldResponse = CourierClient.getCreateCourierResponse(new Courier("sadfss", "", "gusenitsa"));

        noRequiredFieldResponse.then().assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Попытка создания курьера с уже существующим логином")
    public void createCourierLoginDuplicateTest() {
        CourierClient.getCreateCourierResponse(new Courier("sadfss", "1qaz2wsx", "gusenitsa"));
        Response loginDuplicateResponse = CourierClient.getCreateCourierResponse(new Courier("sadfss", "1qazaaa2wsx", "gusenitsaa"));

        loginDuplicateResponse.then().assertThat().statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void tearDown() {
        CourierClient.deleteCourierByLoginAndPassword(new Courier("sadfss", "1qaz2wsx"));
    }
}
