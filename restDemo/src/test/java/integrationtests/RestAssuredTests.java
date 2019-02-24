package integrationtests;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;

import org.junit.Test;

public class RestAssuredTests {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "restDemo";
        RestAssured.port = 8084;
    }

    @Test
    public void testUserFetchesSuccess() {
        when()
                .get("/api/customer/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("Sarah Palin"))
                .body("age", equalTo(56));
    }

}
