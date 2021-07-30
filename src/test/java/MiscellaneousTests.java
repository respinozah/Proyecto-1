import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class MiscellaneousTests extends Base{

    @Test
    public void HomeTest(){
        given()
        .when()
            .get("/")
        .then()
            .statusCode(200)
            .body(Matchers.containsString("Welcome to Golang"));
    }

    @Test
    public void PingTest(){
        given()
        .when()
            .get("/ping")
        .then()
            .statusCode(200)
            .body("response", Matchers.equalTo("pong"));
    }

    @Test
    public void notFoundTest(){
        given()
        .when()
            .get("/paginaNoExiste")
        .then()
            .statusCode(404)
            .body(Matchers.containsString("Opss!! 404 again?"));
    }
}
