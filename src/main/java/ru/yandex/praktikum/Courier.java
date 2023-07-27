package ru.yandex.praktikum;

public class Courier {
    private String login;
    private String password;
    private String fullName;
    public static final String BASE_PATH_COURIER = "/api/v1/courier";
    public static final String BASE_PATH_COURIER_LOGIN = "/api/v1/courier/login";

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
}
