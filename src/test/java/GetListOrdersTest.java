import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetListOrdersTest {

    private OrdersClient ordersClient;

    @Before
    public void setUp() {
        ordersClient = new OrdersClient();
    }

    @Test
    @Description("Проверка получения списка заказов")
    public void GetListOrderTest() {
        ValidatableResponse responseListOrder = ordersClient.getList();

        List orders = responseListOrder.extract().path("orders");

        assertThat(orders, notNullValue());
    }
}
