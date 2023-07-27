import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.Order;
import ru.yandex.praktikum.Service;
import ru.yandex.praktikum.api.client.OrdersClient;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List<String> color;

    public OrderCreateTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters(name = "Создание заказа. Тестовые данные: {0} {1} {2} {3} {4} {5} {6} {7} {8}")
    public static Object[][] getOrderData() {
        List<String> colorBlack = new ArrayList<>();
        colorBlack.add("BLACK");

        List<String> colorGrey = new ArrayList<>();
        colorGrey.add("GREY");

        List<String> colorBlackAndGrey = new ArrayList<>();
        colorBlackAndGrey.add("BLACK");
        colorBlackAndGrey.add("GREY");

        List<String> colorNo = new ArrayList<>();

        return new Object[][] {
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", colorBlack},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", colorGrey},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", colorBlackAndGrey},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", colorNo},
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = Service.BASE_URI;
    }

    @Test
    @DisplayName("Создание заказа с разными цветами")
    public void orderCreateColorsTest() {
        Response orderCreateResponse = OrdersClient.getOrderCreateResponse(new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color));

        orderCreateResponse.then().assertThat().statusCode(201)
                .and()
                .body("track", notNullValue());
    }
}
