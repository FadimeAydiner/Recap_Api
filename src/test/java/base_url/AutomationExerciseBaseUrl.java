package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public class AutomationExerciseBaseUrl {

    protected RequestSpecification spec;
    @BeforeEach//JunitJupiter
    public void setup(){
        spec= new RequestSpecBuilder().setBaseUri("https://automationexercise.com/api").build();
    }

}
