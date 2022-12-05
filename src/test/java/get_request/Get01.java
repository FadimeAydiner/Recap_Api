package get_request;

import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class Get01 {
    /*
     Given
         https://petstore.swagger.io/v2/pet/12
     When
         User sends a GET Request to the url
     Then
         HTTP Status Code should be 200
     And
         Content Type should be JSON
     And
         Status Line should be HTTP/1.1 200 OK
  */
    public static void main(String[] args) {
            //set the url
        String url="https://petstore.swagger.io/v2/pet/12";

            //set the expeceted data

            //send the req.
        Response response=given().when().get(url);
        response.prettyPrint();
        //do ass

        //status code
        if(response.statusCode()==200)
            System.out.println("Success: pass Status code is"+response.statusCode());
        else
            System.out.println("Test faied status code is "+response.statusCode());

        //content type
        if(response.contentType().equals("application/json"))
            System.out.println("Success: pass content type is "+response.contentType());
        else
            System.out.println("Test failed content type is "+response.contentType());

        //status line
        if(response.statusLine().equals("HTTP/1.1 200 OK"))
            System.out.println("Success: pass status line is "+response.statusLine());
        else
            System.out.println("Test failed status line is "+response.statusLine());

    }
}
