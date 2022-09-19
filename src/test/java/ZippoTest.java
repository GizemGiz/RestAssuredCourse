import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;


public class ZippoTest {


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
                .get("https://api.zippopotam.us/us/12170")
                .then()
                .statusCode(200);


    }

    @Test
    public void loggingRequestDetails(){

        given()
                .log().all()
                .when()
                .get("https://api.zippopotam.us/us/12170")
                .then()
                .statusCode(200);
    }

    @Test
    public void loginResponseDetails(){

        given()
                .when()
                .get("https://api.zippopotam.us/us/12170")
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    public void checkContentType(){

        given()
                .when()
                .get("https://api.zippopotam.us/us/12170")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200);
    }

    @Test
    public void validateCountryTest(){

        given()
                .when()
                .get("https://api.zippopotam.us/us/12170")
                .then()
                .body("country", equalTo("United States"))
                .statusCode(200);

    }

    @Test
    public void validateCountry(){
        given()
                .when()
                .get("https://api.zippopotam.us/us/12170")
                .then()
                .body("'country abbreviation'", equalTo("US"))
                .statusCode(200);
    }

















}
