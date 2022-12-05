package get_request;

import base_url.AutomationExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get05 extends AutomationExerciseBaseUrl {
    /*
        Given
	   	    https://automationexercise.com/api/productsList
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 30 on the console
			   Assert that there are 10 ids greater than 30
			 3)Print all brands whose usertype is Women on the console
			   Assert that the number of brands whose usertype is Women is 12
			 4)Total price of all product is 41864
     */
    @Test
    public void get05()
    {
        //set the url
        spec.pathParam("1","productsList");
        //set the expected

        //send the req.
        Response response=given().spec(spec).when().get("/{1}");
        response.jsonPath().prettyPrint();

        //Do assertion
        assertEquals(200,response.statusCode());

        JsonPath jsonPath=response.jsonPath();

        //Print all ids greater than 30 on the console
        //Assert that there are 10 ids greater than 30
        List<Integer> ids=jsonPath.getList("products.findAll{it.id>30}.id");
        System.out.println(ids.toString());

        assertEquals(10,ids.size());

        //Print all brands whose usertype is Women on the console
        //Assert that the number of brands whose usertype is Women is 12
        List<String> brands=jsonPath.getList("products.findAll{it.category.usertype.usertype=='Women'}.brand");
        System.out.println(brands.toString());

        assertEquals(12,brands.size());

        //Total price of all product is 41864
        List<String> prices=jsonPath.getList("products.price");
        System.out.println(prices.toString());
        List<Integer> priceInteger=new ArrayList<>();
        for(String w:prices)
            priceInteger.add(Integer.valueOf(w.replace("Rs. ","")));
        int total=0;
        for(Integer w:priceInteger)
            total+=w;
        System.out.println("total="+total);
        assertEquals(41864,total);

        //2nd Way:
        int totalPriceLambda = prices.stream().map(t->Integer.parseInt( t.replaceAll("[^0-9]",""))).reduce(0,Math::addExact);
        System.out.println("totalPriceLambda = " + totalPriceLambda);
        assertEquals(41864,totalPriceLambda);


    }
}
