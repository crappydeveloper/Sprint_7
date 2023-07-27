package ru.yandex.praktikum.api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {
    public static final String BASE_PATH_COURIER = "/api/v1/courier";
    public static final String BASE_PATH_COURIER_LOGIN = "/api/v1/courier/login";

    @Step("Get response for create courier")
    public static Response getCreateCourierResponse(Object json) {
        return (Response) given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(BASE_PATH_COURIER);
    }

    @Step("Get response for courier login")
    public static Response getLoginCourierResponse(Object json) {
        return (Response) given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(BASE_PATH_COURIER_LOGIN);
    }

    @Step("Delete courier using login and password")
    public static void deleteCourierByLoginAndPassword(Object json) {
        Integer id = getLoginCourierResponse(json)
                .then().extract().body().path("id");

        given()
                .delete(BASE_PATH_COURIER + "/" + id);
    }
}
