package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public class MedunnaBaseUrl {

    protected RequestSpecification spec;
    @BeforeEach//JunitJupiter
    public void setup(){
        spec= new RequestSpecBuilder().setBaseUri("https://medunna.com/api").build();
    }

}
