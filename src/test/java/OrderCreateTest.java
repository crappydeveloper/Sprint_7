import io.restassured.response.Response;
import io.restassured.RestAssured;

import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import org.junit.Before;
import org.junit.Test;

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

    @Parameterized.Parameters
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
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void orderCreateColorsTest() {
        Response response = Order.orderCreate(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);

        response.then().assertThat().body("track", notNullValue())
                .and()
                .statusCode(201);
    }
}
