package get_request;

import base_url.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Get02b extends PetStoreBaseUrl {
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
        spec.pathParams("1","pet","2","findByStatus").queryParams("status","available");
        //set the expected data

        //send the req
        Response response=given().spec(spec).when().get("/{1}/{2}");
        // Print all "available" pets on console
        response.prettyPrint();

        //Do assertion
        response.then().assertThat().statusCode(200);

    }

}
