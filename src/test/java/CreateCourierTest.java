import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CreateCourierTest {

    private Courier courier;
    private CourierClient courierClient;
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefault();
    }

    @After
    public void cleanUp() {
        if(id > 0)
        courierClient.delete(id);
    }

    @Test
    @Description("Проверка на то что курьера можно создать (на то что возвращается правильный код ответа при создании и на то что при успешном создании возвращается \"ok: true\")")
    public void SuccessCreateCourierTest() {
        ValidatableResponse response = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        id = loginResponse.extract().path("id");

        int statusCode = response.extract().statusCode();

        String message = response.extract().body().asString();

        assertEquals(201, statusCode);
        assertEquals("{\"ok\":true}", message);
    }

    @Test
    @Description("Проверка на создание курьера с уже существующим логином")
    public void CreateCourierWithExistLogin() {
        courierClient.create(courier);
        ValidatableResponse response = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        id = loginResponse.extract().path("id");

        String message = response.extract().path("message");

        assertEquals("Этот логин уже используется. Попробуйте другой.", message);
    }


    @Test
    @Description("Проверка на создание курьера без логина (то что поле \"Логин\" обязательное и то что возвращается ошибка, если поля нет)")
    public void CreateCourierWithoutLogin() {
        courier = CourierGenerator.getWithoutLogin();
        ValidatableResponse response = courierClient.create(courier);

        String message = response.extract().path("message");

        assertEquals("Недостаточно данных для создания учетной записи", message);
    }

    @Test
    @Description("Проверка на создание курьера без пароля (то что поле \"Логин\" обязательное и то что возвращается ошибка, если поля нет)")
    public void CreateCourierWithoutPassword() {
        courier = CourierGenerator.getWithoutPassword();
        ValidatableResponse response = courierClient.create(courier);

        String message = response.extract().path("message");

        assertEquals("Недостаточно данных для создания учетной записи", message);
    }

}
