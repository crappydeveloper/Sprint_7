import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Courier {
    private String login;
    private String password;

    private String fullName;


    public Courier(String login, String password, String fullName) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
    }

    public Courier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Courier() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public static Response createCourier(String login, String password, String firstName) {
        Courier json = new Courier(login, password, firstName);

        return (Response) given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier");
    }

    public static Response loginCourier(String login, String password) {
        Courier json = new Courier(login, password);

        return (Response) given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier/login");
    }

    public static void deleteCourierByLoginAndPassword(String login, String password) {
        Integer id = Courier.loginCourier(login, password)
                .then().extract().body().path("id");

        given()
                .delete("/api/v1/courier/" + id);
    }
}
