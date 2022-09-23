import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class NationalitiesFunctionTests {
    private RequestSpecification reqSpec;
    private String nationality_id;
    private Cookies cookies;


    @BeforeClass
    public void setup(){

        RestAssured.baseURI = "https://demo.mersys.io";

        reqSpec = given()
                .log().body()
                .contentType(ContentType.JSON);
    }

    @Test(priority = 1)
    public void loginTest() {

        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("username", "richfield.edu");
        credentials.put("password", "Richfield2020!");
        credentials.put("rememberMe", "true");

        cookies = given()
                .spec(reqSpec)
                .body(credentials)
                .when()
                .post("/auth/login")
                .then()
                .body("username", equalTo(credentials.get("username")))
                .statusCode(200)
                .extract().detailedCookies();
    }

    @Test(priority = 2)
    public void createNationalityTest(){

        HashMap<String, String> reqBody = new HashMap<>();
        reqBody.put("name", "Gizem Nation");


        nationality_id = given()
                .spec(reqSpec)
                .cookies(cookies)
                .body(reqBody)
                .when()
                .post("/school-service/api/nationality")
                .then()
                .log().body()
                .statusCode(201)
                .extract().jsonPath().getString("id");


    }
    @Test(priority = 3)
    public void createNationalityNegativeTest(){

        HashMap<String, String> reqBody = new HashMap<>();
        reqBody.put("name", "Gizem Nation");

        given()
                .spec(reqSpec)
                .cookies(cookies)
                .body(reqBody)
                .when()
                .post("/school-service/api/nationality")
                .then()
                .log().body()
                .statusCode(400);
    }

}
