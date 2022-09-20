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
    public void setup() {

        RestAssured.baseURI = "https://gorest.co.in";

        reqSpec = given()
                .log().uri()
                .header("Authorization", "Bearer ba84af0513873344544a5fa2a33b76e8d2d892d89b74322a3df05ef647fae66e")
                .contentType(ContentType.JSON);

        requestBody = new HashMap<>();
        requestBody.put("name", "BlahBlah123");
        requestBody.put("email", "blahblah@technoblah.com");
        requestBody.put("gender", "female");
        requestBody.put("status", "active");

    }

    @Test
    public void createUserTest() {

        userId = given()
                .spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/public/v2/users")
                .then()
                .log().body()
                .body("name", equalTo(requestBody.get("name")))
                .statusCode(201)
                .extract().path("id");
    }

    @Test(dependsOnMethods = "createUserTest")
    public void editUserTest() {

        HashMap<String, String> updateRequestBody = new HashMap<>();
        updateRequestBody.put("name", "Updated Blah Blah name");

        given()
                .spec(reqSpec)
                .body(updateRequestBody)
                .when()
                .put("/public/v2/users/" + userId)
                .then()
                .statusCode(200);

    }

    @Test(dependsOnMethods = "editUserTest")
    public void deleteUserTest() {

        given()
                .spec(reqSpec)
                .when()
                .delete("/public/v2/users/" + userId)
                .then()
                .log().body()
                .statusCode(204);


    }
}
