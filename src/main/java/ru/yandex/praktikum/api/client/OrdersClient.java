package ru.yandex.praktikum.api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class OrdersClient {
    public static final String BASE_PATH_ORDERS = "/api/v1/orders";

    @Step("Get response for order create")
    public static Response getOrderCreateResponse(Object json) {
        return (Response) given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(BASE_PATH_ORDERS);
    }

    @Step("Get response for get orders")
    public static Response getOrdersListResponse() {
        return (Response) given()
                .get(BASE_PATH_ORDERS);
    }
}
