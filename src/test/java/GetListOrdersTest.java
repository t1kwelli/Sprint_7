import api.client.OrdersClient;
import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class GetListOrdersTest {

    private OrdersClient ordersClient;

    @Before
    public void setUp() {
        ordersClient = new OrdersClient();
    }

    @Test
    @Description("Проверка получения списка заказов")
    public void getListOrderTest() {
        ValidatableResponse responseListOrder = ordersClient.getList();

        int statusCode = responseListOrder.extract().statusCode();
        List orders = responseListOrder.extract().path("orders");

        assertEquals(200, statusCode);
        assertThat(orders, notNullValue());
    }
}
