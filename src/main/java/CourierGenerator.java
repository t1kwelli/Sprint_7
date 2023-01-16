import com.github.javafaker.Faker;

public class CourierGenerator {

    public static Courier getDefault() {
        return new Courier("testkdd", "123", "Dima");
    }

    public static Courier getWithoutLogin() {
        return new Courier("", "123", "Dima");
    }

    public static Courier getWithoutPassword() {
        return new Courier("testkdd", "", "Dima");
    }

    public static Courier getWithIncorrectLogin() {
        return new Courier("testkd", "123", "Dima");
    }

    public static Courier getWithIncorrectPassword() {
        return new Courier("testkdd", "1234", "Dima");
    }
}
