package utils;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MedunnaAuthentication {
    public static void main(String[] args) {
        System.out.println(generateToken());
    }
    public static  String generateToken(){
        Map<String,Object> json=new HashMap<>();
        json.put( "password","John.123");
        json.put( "rememberMe",true);
        json.put( "username","john_doe");


        Response response=given().contentType(ContentType.JSON).body(json).when().post("https://medunna.com/api/authenticate");
        //response.prettyPrint();
        /*{
            "password":"John.123",
                "rememberMe":true,
                "username":"john_doe"
        }*/
        JsonPath jsonPath=response.jsonPath();
        return jsonPath.getString("id_token");
    }
}
