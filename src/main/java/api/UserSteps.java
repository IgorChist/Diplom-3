package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.EndPoints.*;
import static io.restassured.RestAssured.given;

public class UserSteps {
    @Step("Send POST request to api/auth/register")
    public Response createUser (User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(HOST)
                .body(user)
                .when()
                .post(CREATE_USER);
    }
    @Step("Send POST request to api/auth/login")
    public Response loginUser (User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(HOST)
                .body(user)
                .when()
                .post(LOGIN_USER);
    }
    @Step("Send DELETE request to api/auth/user")
    public Response deleteUser (String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .baseUri(HOST)
                .when()
                .delete(DELETE_USER);
    }
}
