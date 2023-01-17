package api.model;

import java.util.List;

public class Orders {
    private String firstName;

    private String lastName;

    private String address;

    private String metroStation;

    private String phone;

    private Number rentTime;

    private String deliveryDate;

    private String comment;

    private List<String> color;

    public Orders(String firstName, String lastName, String address, String metroStation, String phone, Number rentTime, String deliveryDate, String comment, List color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }
}
