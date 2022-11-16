import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.CourierGenerator;
import services.CourierRequests;
import pojo.Courier;
import pojo.Credentials;

public class LoginCourierTest {
    Courier courier = CourierGenerator.randomCourier();
    CourierRequests courierRequests = new CourierRequests();

    @Before
    public void getData() {
        courierRequests.createCourier(courier);
    }

    @Test
    @DisplayName("Test of logging courier's statusCode")
    @Description("Check correct statusCode (200) when logging courier. (positive test)")
    public void loginCourierPositiveTestGetStatusCode() {
        Credentials credentials = new Credentials(courier.getLogin(),courier.getPassword());
        courierRequests.loginCourier(credentials)
                .then().statusCode(200);
    }

    @Test
    @DisplayName("Test of logging courier's id ")
    @Description("Check that after logging courier message have correct not null courier id. Positive test.")
    public void loginCourierPositiveTestGetCourierId() {
        Credentials credentials = new Credentials(courier.getLogin(),courier.getPassword());
        Assert.assertNotNull(courierRequests.getCourierId(credentials));
    }

    @After
    public void deleteData () {
        courierRequests.deleteCourier(courier);
    }
}