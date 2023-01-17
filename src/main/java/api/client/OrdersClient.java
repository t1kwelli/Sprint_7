package api.client;

import api.model.Orders;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrdersClient extends Client {

    private static final String PATH_CREATE_ORDER = "/api/v1/orders";
    private static final String PATH_GET_LIST_ORDER = "/api/v1/orders";

    @Step("Создание заказа")
    public ValidatableResponse create(Orders orders) {
        return given()
                .spec(getSpec())
                .body(orders)
                .when()
                .post(PATH_CREATE_ORDER)
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getList() {
        return given()
                .spec(getSpec())
                .get(PATH_GET_LIST_ORDER)
                .then();
    }
}
