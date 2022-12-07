package PetStoreSmokeTest;

import Pojos.Category;
import Pojos.PetStorePetPojo;
import Pojos.Tag;
import base_url.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get02 extends PetStoreBaseUrl {
    /*
   * Given
       https://petstore.swagger.io/v2/pet/4567

      When
      * GET  req
      Then
      * Status code 404
      * Respose body like that
                {
                "code": 1,
                "type": "error",
                "message": "Pet not found"
            }
*/
    @Test
    public void get02() throws IOException {
        //set the url
        spec.pathParams("1","pet","2",4567);
        //set the expected data
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("code",404);
        expectedData.put("type", "error");
        expectedData.put("message", "Pet not found");
        System.out.println(expectedData);
        //send the req
        Response response=given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();
        //do assertion
        Map<String,Object> actualData=new ObjectMapper().readValue(response.asString(), HashMap.class);
        System.out.println(actualData);

        assertEquals(404,response.statusCode());
        assertEquals(expectedData.get("code"),actualData.get("code"));
        assertEquals(expectedData.get("message"),actualData.get("message"));
}}
