package get_request;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Get02 {
    //Print all "available" pets on console by using "https://petstore.swagger.io/" documentation.

    /*
    Given
        https://petstore.swagger.io/v2/pet/findByStatus?status=available
    When
        User sends Get request to Url
    Then
        HTTP Status Code is 200
    And
        Print all "available" pets on console
     */
    @Test
    public void get02(){
        //Set the url
        String url="https://petstore.swagger.io/v2/pet/findByStatus?status=available";

        //set the expected data

        //send the req
        Response response=given().when().get(url);
        // Print all "available" pets on console
        response.prettyPrint();

        //Do assertion
        response.then().assertThat().statusCode(200);

    }

}
