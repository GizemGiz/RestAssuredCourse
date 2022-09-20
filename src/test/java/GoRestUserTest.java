import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestUserTest {

    private RequestSpecification reqSpec;
    private HashMap<String, String> requestBody;
    private Object userId;

    @BeforeClass
    public void setup(){

        RestAssured.baseURI = "https://gorest.co.in";

        reqSpec =given()
                .log().uri()
                .header("Authorization", "Bearer ba84af0513873344544a5fa2a33b76e8d2d892d89b74322a3df05ef647fae66")
                .contentType(ContentType.JSON);

        requestBody = new HashMap<>();
        requestBody.put("name", "Tester Test User");
        requestBody.put("email", "testeruser@gmail.com");
        requestBody.put("gender", "female");
        requestBody.put("status", "active");
    }

    @Test
    public void createUserTest(){

         userId = given()
                .spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/public/v2/users")
                .then()
                .log().body()
                .statusCode(201)
                .extract().path("id");
    }

    @Test
}
