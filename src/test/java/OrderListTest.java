import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void getOrdersListTest() {
        Response response = Order.getOrdersList();

        response.then().assertThat().body("orders", notNullValue())
                .and()
                .statusCode(200);
    }
}
