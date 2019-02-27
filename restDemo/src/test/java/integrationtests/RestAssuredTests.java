package integrationtests;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import data.CustomerFacade;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.Test;

public class RestAssuredTests {

    @Before
    public void setup() {
        CustomerFacade.resetData(); // to allways start from a known state.
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "restDemo/api";
        RestAssured.port = 8084;
    }

    @Test
    public void testGetCustomers() {
        int size = get("/customer").jsonPath().getList("$").size(); // "$" points to the json body root
        assertEquals(size, 3);
    }

//    @Test //much better and readable
//    public void testGetCustomers3() {
//        when()
//                .get("/customer")
//                .then()
//                .statusCode(200)
//                .body(containsString("Vladimir"))
//                .body("$", equalTo(96));
//    }

    @Test
    public void testUserFetchesSuccess() {
        when()
                .get("/customer/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("Sarah Palin"))
                .body("age", equalTo(56));
    }

    @Test
    public void testUserFetchesFailure() {
        when()
                .get("/customer/4")
                .then()
                .statusCode(404)
                .body("message", equalTo("no customer with id: 4"));
    }

    @Test
	public void testPost() { // data for post: {"name": "Svend Auken","age": 82}
		Map<String,String> customer = new HashMap<>();
		customer.put("name", "Svend Auken");
		customer.put("age", "82");

		given()
		.contentType("application/json")
		.body(customer)
		.when().post("/customer").then()
		.statusCode(200).body("id", equalTo("4"));
	}

}
