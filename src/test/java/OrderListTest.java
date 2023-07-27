import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.Service;
import ru.yandex.praktikum.api.client.OrdersClient;
import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = Service.BASE_URI;
    }

    @Test
    @DisplayName("Получение списка заказов")
    public void getOrdersListTest() {
        Response response = OrdersClient.getOrdersListResponse();

        response.then().assertThat().statusCode(200)
                .and()
                .body("orders", notNullValue());
    }
}
