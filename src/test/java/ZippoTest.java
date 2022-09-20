import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ZippoTest {

    @BeforeClass
    public void setup(){

        // WE ADD THIS, THAT WAY WE DON'T HAVE TO WRITE ALL URL FOR NEXT CODES
        RestAssured.baseURI = "https://api.zippopotam.us";
    }


    @Test
    public void test(){

        given()
                .when()
                .then();


    }

    @Test
    public void checkingStatusCode(){

        given()
                .when()
                .get("/us/12170")
                .then()
                .statusCode(200);


    }

    @Test
    public void loggingRequestDetails(){

        given()
                .log().all()
                .when()
                .get("/us/12170")
                .then()
                .statusCode(200);
    }

    @Test
    public void loginResponseDetails(){

        given()
                .when()
                .get("/us/12170")
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    public void checkContentType(){

        given()
                .when()
                .get("/us/12170")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200);
    }

    @Test
    public void validateCountryTest(){

        given()
                .when()
                .get("/us/12170")
                .then()
                .body("country", equalTo("United States"))
                .statusCode(200);

    }

    @Test
    public void validateCountry(){
        given()
                .when()
                .get("/us/12170")
                .then()
                .body("'country abbreviation'", equalTo("US"))
                .statusCode(200);
    }

    @Test
    public void validateStateTest(){
        given()
                .when()
                .get("/us/12170")
                .then()
                .body("places[0].state", equalTo("New York"))
                .statusCode(200);
    }

    @Test
    public void pathParametersTest(){
        String country = "US";
        String zipcode = "12170";

        given()
                .pathParam("country", country)
                .pathParam("zipcode", zipcode)
                .when()
                .get("/{country}/{zipcode}")
                .then()
                .statusCode(200);
    }

    @Test
    public void queryParameters(){
        String  gender = "female";
        String status= "active";

        given()
                .param("gender", gender)
                .param("status", status)
                .when()
                .get("https://gorest.co.in/public/v2/users")
                .then()
                .statusCode(200)
                .log().body(); //response body
    }

    @Test
    public void extractValueTest(){
        Object countryInfo = given()
                .when()
                .get("/user/v2/12170")
                .then()
                .extract().path("country");
        System.out.println(countryInfo);

    }














}
