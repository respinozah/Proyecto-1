import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import specifications.ResponseSpecifications;

public class MiscellaneousTests extends Base{

    @Test (description = "Test that validates api availability.")
    public void HomeTest(){
        given()
        .when()
            .get("/")
        .then()
            .statusCode(200)
            .body(Matchers.containsString("Welcome to Golang"));
    }

    @Test (description = "Test that pings the api.")
    public void PingTest(){
        given()
        .when()
            .get("/ping")
        .then()
            .statusCode(200)
            .body("response", Matchers.equalTo("pong"));
    }

    @Test (description = "Test that validates api behavior for an invalid page or endpoint.")
    public void notFoundTest(){
        given()
        .when()
            .get("/paginaNoExiste")
        .then()
            .statusCode(404)
            .body(Matchers.containsString("Opss!! 404 again?"));
    }
}
