package get_request;

import base_url.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get04b extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/8065
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
                    {
                            "firstname": "John",
                            "lastname": "Smith",
                            "totalprice": 111,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                            },
                            "additionalneeds": "Breakfast"
                        }
     */
    @Test
    public void get04() {
        //set the url
        spec.pathParams("1", "booking", "2", 8065);
        //set the expected data

        //send the req.
        Response response = given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();

        //Do assertion
        JsonPath jsonPath=response.jsonPath();
        //Soft assertion
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(response.statusCode(),200);
        softAssert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        softAssert.assertEquals(jsonPath.getString("firstname"),"John");
        softAssert.assertEquals(jsonPath.getString("lastname"),"Smith");
        softAssert.assertEquals(jsonPath.getInt("totalprice"),111);
        softAssert.assertEquals(jsonPath.getBoolean("depositpaid"),true);
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2018-01-01");
        softAssert.assertEquals( jsonPath.getString("bookingdates.checkout"),"2019-01-01");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"),"Breakfast");

        softAssert.assertAll();
    }
}
