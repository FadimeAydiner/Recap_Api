package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public class PetStoreBaseUrl {

    protected RequestSpecification spec;
    @BeforeEach//JunitJupiter
    public void setup(){
        spec= new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").build();
    }

}
