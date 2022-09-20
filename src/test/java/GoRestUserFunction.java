import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestUserFunction {

    RequestSpecification reqSec;

    @BeforeClass

    public void setup(){

        RestAssured.baseURI = "https://gorest.co.in/";

        reqSec = given()
                .log().body()
                .header("Authorization", "Bearer ba84af0513873344544a5fa2a33b76e8d2d892d89b74322a3df05ef647fae66e")
                .contentType(ContentType.JSON);
    }

    @Test

    public void createUserTest(){

        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "Test User Name");
        requestBody.put("email", "testser@goresttest.com");
        requestBody.put("gender", "male");
        requestBody.put("status", "active");

        given()
                .spec(reqSec)
                .body(requestBody)
                .when()
                .post("/public/v2/users")
                .then()
                .log().body()
                .statusCode(201)
                .body("name");
    }



}
