import api.client.OrdersClient;
import api.model.Orders;
import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private Orders orders;
    private OrdersClient ordersClient;

    @Before
    public void setUp() {
        ordersClient = new OrdersClient();
        orders = new Orders("Naruto", "Uchiha", "Konoha, 142 apt.",
                "metro4", "+7 800 355 35 35", 5, "2020-06-06",
                "Saske, come back to Konoha", color);
    }
    private final List <String> color;

    public CreateOrderTest(List <String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}")
    public static Object[][] getColorData () {
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("BLACK", "GREY")},
                {List.of("GREY")},
                {List.of("")},
        };
    }

    @Test
    @Description("Проверка создания заказа")
    public void createOrderTest() {
        ValidatableResponse responseOrder = ordersClient.create(orders);

        int statusCode = responseOrder.extract().statusCode();
        int track = responseOrder.extract().path("track");

        assertEquals(201, statusCode);
        assertThat(track, notNullValue());
    }
}
