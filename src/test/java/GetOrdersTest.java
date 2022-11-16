import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static services.Constants.*;

public class GetOrdersTest {
    @Test
    @DisplayName("Test statusCode and list of getting orders")
    @Description("Test correct statusСode (200) and list of orders after all orders getting ")
    public void getAllOrdersTest () {
        String actual = given()
                .spec(REQUEST_SPECIFICATION)
                .when()
                .get(API_GET_ORDER)
                .then().statusCode(200)
                .extract().path("orders").toString();
        Assert.assertNotNull(actual);
    }
}