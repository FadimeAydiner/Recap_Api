package patch_request;

import base_url.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Patch01 extends RegresBaseUrl {
     /*
     Given
         1) https://reqres.in/api/users/2
         2) {
              "name": "neo"
             }
     When
          I send PATCH Request to the Url
     Then
           Status code is 200
           And response body is like   {
                                              "name": "neo",
                                              "updatedAt": "2022-10-02T12:53:21.675Z"
                                       }
  */

    @Test
    public void patch01() throws IOException {
        //set the url
        spec.pathParams("1","users","2",2);

        //set the exp
        Map<String,String> expectedData=new HashMap<>();
        expectedData.put("name","neo");

        //send the res
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{1}/{2}");
        response.prettyPrint();

        //do ass
        //
        // Map<String,String> actualData=response.as(HashMap.class);
        Map<String,String> actualData=new ObjectMapper().readValue(response.asString(), HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));

    }
}
