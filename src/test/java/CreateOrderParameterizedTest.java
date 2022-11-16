import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import services.OrderRequests;
import pojo.Order;
import pojo.OrderId;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final Number rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;
    OrderId orderId = new OrderId();

    public CreateOrderParameterizedTest(String firstName, String lastName, String address, String metroStation, String phone, Number rentTime, String deliveryDate, String comment, String[] color) {
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

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                { "Имя", "Фамилия", "Адрес", "Братиславская", "+89097556565", 2, "2022-11-30", "Комментарий", new String[] {}},
                { "Имя", "Фамилия", "Адрес", "Марьино", "+89097556565", 2, "2022-11-30", "Комментарий", new String[] {"BLACK"}},
                { "Имя", "Фамилия", "Адрес", "Марьина роща", "+89097556565", 3, "2022-11-30", "Комментарий", new String[] {"GREY"}},
                { "Имя", "Фамилия", "Адрес", "Парк Победы", "+89097556565", 3, "2022-11-30", "Комментарий", new String[] {"GREY","BLACK"}}
        };
    }

    @Test
    @DisplayName("Test statusCode of creating order")
    @Description("Test correct statusCode (201) when create new order")
    public void createOrderTest() {
        Order order = new Order(firstName,lastName, address, metroStation, phone,rentTime, deliveryDate, comment, color);
        String track = OrderRequests.createOrder(order)
                .then().statusCode(201)
                .extract().path("track").toString();
        Assert.assertNotNull(track);
        orderId.setTrack(track);
    }
    @After
    public void deleteData () {
        OrderRequests.cancelOrder(orderId);
    }
}