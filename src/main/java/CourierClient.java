import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client{

    private static final String PATH_CREATE_COURIER = "/api/v1/courier";
    private static final String PATH_LOGIN_COURIER = "/api/v1/courier/login";
    private static final String PATH_DELETE_COURIER = "/api/v1/courier/{id}";

    public ValidatableResponse create(Courier courier) {
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(PATH_CREATE_COURIER)
                .then();

    }

    public ValidatableResponse login(CourierCredentials credentials) {
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(PATH_LOGIN_COURIER)
                .then();
    }

    public ValidatableResponse delete(int id) {
        return given()
                .spec(getSpec())
                .when()
                .delete(PATH_DELETE_COURIER, id)
                .then();
    }
}
