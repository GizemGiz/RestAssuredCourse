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
    private Cookies cookies;


    @BeforeClass
    public void setup(){

        RestAssured.baseURI = "https://demo.mersys.io";

        reqSpec = given()
                .log().body()
                .contentType(ContentType.JSON);
    }

    @Test
    public void loginTest() {

        HashMap<String, String> credentials = new HashMap<>();

        given()
                .spec(reqSpec)
                .body(credentials)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract().detailedCookies();
    }

    @Test(dependsOnMethods = "loginTest")
    public void createNationalityTest(){

        HashMap<String, String> reqBody = new HashMap<>();
        //reqBody.get("nationality", "Turkey");
        given()
                .spec(reqSpec)
                .cookies(cookies)
                .when()
                .then()
                .statusCode(200);
    }
}
