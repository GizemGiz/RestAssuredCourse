import POJO.GoRestUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestUserTestWithPojo {

    private RequestSpecification reqSpec;
    private GoRestUser user;


    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://gorest.co.in";

        reqSpec = given()
                .log().body()
                .header("Authorization", "Bearer ba84af0513873344544a5fa2a33b76e8d2d892d89b74322a3df05ef647fae66e")
                .contentType(ContentType.JSON);

        user = new GoRestUser();
        user.setName("Burger Kingg");
        user.setEmail("burger1234@kingss.com");
        user.setGender("female");
        user.setStatus("active");
    }
    @Test
    public void createUserTest(){

        user.setId(given()
                .spec(reqSpec)
                .body(user)
                .when()
                .post("/public/v2/users")
                .then()
                .log().body()
                .statusCode(201)
                .body("name", equalTo(user.getName()))
                .extract().jsonPath().getString("id"));
    }

    @Test(dependsOnMethods = "createUserTest")
    public void createUserNegativeTest(){

        given()
                .spec(reqSpec)
                .body(user)
                .when()
                .post("/public/v2/users")
                .then()
                .log().body()
                .statusCode(422);
    }

    @Test(dependsOnMethods = "createUserNegativeTest")
    public void getUserTest(){

        given()
                .spec(reqSpec)
                .when()
                .get("/public/v2/users/" + user.getId())
                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo(user.getName()))
                .body("email", equalTo(user.getEmail()))
                .body("gender", equalTo(user.getGender()))
                .body("status", equalTo(user.getStatus()));

    }

    @Test(dependsOnMethods = "getUserTest")
    public void updateUserTest(){

        HashMap<String, String> body = new HashMap<>();
        body.put("status", "inactive");

        given()
                .spec(reqSpec)
                .body(body)
                .when()
                .put("/public/v2/users/" + user.getId())
                .then()
                .log().body()
                .statusCode(200)
                .body("status", equalTo(body.get("status")));
    }
    @Test(dependsOnMethods = "updateUserTest")
    public void  deleteUserTest(){
        given()
                .spec(reqSpec)
                .when()
                .delete("/public/v2/users/" + user.getId())
                .then()
                .log().body()
                .statusCode(204);
    }
    @Test(dependsOnMethods = "deleteUserTest")
    public void deleteUserNegativeTest(){
        given()
                .spec(reqSpec)
                .when()
                .delete("/public/v2/users/" + user.getId())
                .then()
                .log().body()
                .statusCode(404);
    }
}
