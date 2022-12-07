package delete_request;

import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Delete01 extends PetStoreBaseUrl {
    /*
        URL: https://petstore.swagger.io/v2/pet/12
       HTTP Request Method: DELETE Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
                response type{
    "code": 200,
    "type": "unknown",
    "message": "12"
     */

    @Test
    public void delete01() throws IOException {
        //set the url
        spec.pathParams("1","pet","2",12);

        //set the expe

        //send the req
        Response response=given().spec(spec).contentType(ContentType.JSON).when().delete("/{1}/{2}");
        response.prettyPrint();

        //do ass
        Map<String,Object> actualData=new ObjectMapper().readValue(response.asString(), HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(200,actualData.get("code"));
        assertEquals("unknown",actualData.get("type"));
        assertEquals("12",actualData.get("message"));
    }


}
