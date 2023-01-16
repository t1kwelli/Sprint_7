import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class LoginCourierTest {

    private Courier courier;
    private CourierClient courierClient;
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefault();
        courierClient.create(courier);
    }

    @After
    public void cleanUp() {
        if(id > 0)
        courierClient.delete(id);
    }

    @Test
    @Description("Проверка на то что курьер может авторизоваться и на то что успешный запрос возвращает id")
    public void SuccessLoginCourierTest() {
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        id = loginResponse.extract().path("id");

        int statusCode = loginResponse.extract().statusCode();
        int id = loginResponse.extract().path("id");

        assertEquals(200, statusCode);
        assertThat(id, notNullValue());
    }

    @Test
    @Description("Проверка авторизации курьера без поля \"логин\" (на то что поле обязательное и если его нет, то возвращается ошибка)")
    public void LoginCourierWithoutLogin() {
        courier = CourierGenerator.getWithoutLogin();
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        String message = loginResponse.extract().path("message");
        int statusCode = loginResponse.extract().statusCode();

        assertEquals("Недостаточно данных для входа", message);
        assertEquals(400, statusCode);
    }

    @Test
    @Description("Проверка авторизации курьера без поля \"Пароль\" (на то что поле обязательное и если его нет, то возвращается ошибка)")
    public void LoginCourierWithoutPassword() {
        courier = CourierGenerator.getWithoutPassword();
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        String message = loginResponse.extract().path("message");
        int statusCode = loginResponse.extract().statusCode();

        assertEquals("Недостаточно данных для входа", message);
        assertEquals(400, statusCode);
    }

    @Test
    @Description("Проверка авторизации курьера с несуществующим/неверным логином")
    public void LoginCourierWithIncorrectLogin() {
        courier = CourierGenerator.getWithIncorrectLogin();
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        String message = loginResponse.extract().path("message");
        int statusCode = loginResponse.extract().statusCode();

        assertEquals("Учетная запись не найдена", message);
        assertEquals(404, statusCode);
    }

    @Test
    @Description("Проверка авторизации курьера с несуществующим/неверным паролем")
    public void LoginCourierWithIncorrectPassword() {
        courier = CourierGenerator.getWithIncorrectPassword();
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        String message = loginResponse.extract().path("message");
        int statusCode = loginResponse.extract().statusCode();

        assertEquals("Учетная запись не найдена", message);
        assertEquals(404, statusCode);
    }
}
