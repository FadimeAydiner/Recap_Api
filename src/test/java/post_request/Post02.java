package post_request;

import Pojos.PetStoreUserPojo;
import Pojos.PetStoreUserResponsePojo;
import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Post02 extends PetStoreBaseUrl {
      /*
         Given
           1) https://petstore.swagger.io/v2/user/
           2)  {
                  "id": 11234,
                  "username": "JohnDoe123",
                  "firstName": "John",
                  "lastName": "Doe",
                  "email": "j@d.com",
                  "password": "abc",
                  "phone": "1234",
                  "userStatus": 2
                }
        When
            I send POST Request to the Url
        Then
            Status code is 200
        And
            response body is like
                                {
                                    "code": 200,
                                    "type": "unknown",
                                    "message": "11234"
                                }
     */
    @Test
    public void post02(){

        //set the url
        spec.pathParam("1","user");
        //set the expected data
        PetStoreUserPojo expectedData=new PetStoreUserPojo(11234,"JohnDoe123","John","Doe","j@d.com","abc","1234",2);
        System.out.println(expectedData);

        //send teh req
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        response.prettyPrint();

        //do ass
        assertEquals(200,response.statusCode());
        PetStoreUserResponsePojo actualData=response.as(PetStoreUserResponsePojo.class);

        assertEquals(200,actualData.getCode());
        assertEquals("unknown",actualData.getType());
        assertEquals("11234",actualData.getMessage());
    }
}
