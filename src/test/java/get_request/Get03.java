package get_request;

import base_url.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get03 extends PetStoreBaseUrl {
     /*
    Print all "available" pets on console by using"https://petstore.swagger.io/" documentation.
    There should be more than 30 "available" pets, "fish" and "doggie" pet names must exist between pets.
    */

    /*
    Given
        https://petstore.swagger.io/v2/pet/findByStatus?status=available
    When
        User sends Get request to the Url
    Then
        HTTP Status Code is 200
    And
       "fish" and "doggie" pet names must exist between pets.
    And
        There should be more than 30 "available" pets
     */
    @Test
    public void get03(){
        //set the url
        spec.pathParams("1","pet","2","findByStatus").queryParams("status","available");
        //set the expected data
        //send the req
        Response response=given().spec(spec).when().get("/{1}/{2}");
        // Print all "available" pets on console
        response.prettyPrint();

        //Do ass
        response.then().assertThat().statusCode(200).
                body("name",hasItems("fish", "doggie"),"id",hasSize(Matchers.greaterThan(30)));

    }
}
