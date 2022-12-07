package put_request;

import Pojos.PetStoreUserPojo;
import Pojos.PetStoreUserResponsePojo;
import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Put01 extends PetStoreBaseUrl {
    /*
       Given
         1) https://petstore.swagger.io/v2/user/JohnDoe
         2)  {
                "id": 11234,
                "username": "JohnnyFoe",
                "firstName": "John",
                "lastName": "Foe",
                "email": "j@d.com",
                "password": "1234",
                "phone": "1234",
                "userStatus": 3
              }
      When
          I send POST Request to the Url
      Then
          Status code is 200
      And
          response body is like {
                              {
                                  "code": 200,
                                  "type": "unknown",
                                  "message": "11234"
                              }
   */
    @Test
    public void put01() throws IOException {

        //set the url
        spec.pathParams("1","user","2","JohnDoe");
        //set the expected data
        PetStoreUserPojo expectedData=new PetStoreUserPojo(11234,"JohnFoe123","John","Foe","j@d.com","abc","1234",2);
        System.out.println(expectedData);

        //send teh req
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{1}/{2}");
        response.prettyPrint();

        //do ass
        assertEquals(200,response.statusCode());
       // PetStoreUserResponsePojo actualData=response.as(PetStoreUserResponsePojo.class);
        PetStoreUserResponsePojo actualData=new ObjectMapper().readValue(response.asString(),PetStoreUserResponsePojo.class);

        assertEquals(200,actualData.getCode());
        assertEquals("unknown",actualData.getType());
        assertEquals("11234",actualData.getMessage());
    }
}
