package PetStoreSmokeTest;

import Pojos.Category;
import Pojos.PetStorePetPojo;
import Pojos.Tag;
import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Put01 extends PetStoreBaseUrl {
    /*
    * Given
        https://petstore.swagger.io/v2/pet
     And
                         * {
                "id": 4567,
                "category": {
                    "id": 1,
                    "name": "mammal"
                },
                "name": "cotton",
                "photoUrls": [
                    "string"
                ],
                "tags": [
                    {
                        "id": 2,
                        "name": "my lovely kitten"
                    }
                ],
                "status": "available"
            }
       When
                * Post req
       Then
            * Status code 200
                * Respose body like that
                   * {
                    "id": 4567,
                    "category": {
                        "id": 1,
                        "name": "mammal"
                    },
                    "name": "cotton",
                    "photoUrls": [
                        "string"
                    ],
                    "tags": [
                        {
                            "id": 2,
                            "name": "my lovely kitten"
                        }
                    ],
                    "status": "available"
                }
*/
    @Test
    public void put01() throws IOException {
        //set the url
        spec.pathParam("1","pet");
        //set the expected data
        Category category=new Category(1,"mammal");
        Tag tag=new Tag(2,"my lovely kitten");
        ArrayList<String> photoUrls=new ArrayList<>();
        photoUrls.add("photo1.jpg");
        photoUrls.add("photo2.jpg");

        ArrayList<Tag> tags=new ArrayList<>();
        tags.add(tag);
        PetStorePetPojo expectedData=new PetStorePetPojo(4567,category,"cotton",photoUrls,tags,"available");
        System.out.println(expectedData);

        //send the req
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{1}");
        response.prettyPrint();
        //do assertion
        PetStorePetPojo actualData=new ObjectMapper().readValue(response.asString(), PetStorePetPojo.class);
        System.out.println(actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getCategory().getName(),actualData.getCategory().getName());
        assertEquals(expectedData.getPhotoUrls(),actualData.getPhotoUrls());
        //assertEquals(expectedData.getTags(),actualData.getTags());
        assertEquals(expectedData.getStatus(),actualData.getStatus());

    }










}
