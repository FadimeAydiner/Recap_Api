package post_request;

import Pojos.MedunnaRoomPojo;
import base_url.MedunnaBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import utils.MedunnaAuthentication;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.MedunnaAuthentication.generateToken;

public class Post03 extends MedunnaBaseUrl {
      /*
         Given
           1) https://medunna.com/api/rooms
           2)   {
                    "createdBy": "john_doe",
                    "createdDate": "2022-12-04T13:40:13.537985Z",
                    "roomNumber": 8793965,
                    "roomType": "TWIN",
                    "status": true,
                    "price": 111,
                    "description": "My Room"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                        "createdBy": "john_doe",
                                        "createdDate": "2022-12-04T13:40:13.537985Z",
                                        "id": 369858,
                                        "roomNumber": 8793965,
                                        "roomType": "TWIN",
                                        "status": true,
                                        "price": 111,
                                        "description": "My Room"
                                    }
     */
    @Test
    public void post03() throws IOException {
        //set the url
        spec.pathParam("1","rooms");

        //set the expected
        MedunnaRoomPojo expectedData=new MedunnaRoomPojo("john_doe","2022-12-06T19:53:39.097975Z",33000065,"TWIN",true,111,"My Room");
        System.out.println(expectedData);

        //send the resp
        Response response=given().spec(spec).headers("Authorization","Bearer "+generateToken()).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        response.prettyPrint();
        //do ass
        MedunnaRoomPojo actualData=new ObjectMapper().readValue(response.asString(),MedunnaRoomPojo.class);
        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getCreatedBy(),actualData.getCreatedBy());
       // assertEquals(expectedData.getCreatedDate(),actualData.getCreatedDate());
        assertEquals(expectedData.getRoomNumber(),actualData.getRoomNumber());
        assertEquals(expectedData.getRoomType(),actualData.getRoomType());
        assertEquals(expectedData.isStatus(),actualData.isStatus());
        assertEquals(expectedData.getPrice(),actualData.getPrice());
        assertEquals(expectedData.getDescription(),actualData.getDescription());
    }
}
