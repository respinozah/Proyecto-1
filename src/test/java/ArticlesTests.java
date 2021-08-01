import Helpers.RequestHelper;
import io.restassured.response.Response;
import model.Article;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import specifications.RequestSpecifications;
import specifications.ResponseSpecifications;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArticlesTests extends Base{

    @Test(description = "Prueba para registrar un articulo.")
    public void createArticleTest(){
        Article testArticle = new Article("randomTitle", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc felis quam, volutpat a pharetra luctus, facilisis sit amet ipsum. Aliquam porttitor iaculis urna et ultrices. Mauris aliquam augue velit, id condimentum quam varius blandit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aliquam et tellus nisi. Donec aliquet odio sit amet nisl accumsan, in laoreet ligula euismod. Morbi tincidunt mauris ac turpis semper sagittis. Sed nec quam elit. Suspendisse finibus auctor neque facilisis feugiat.");

        given()
            .spec(RequestSpecifications.useJWTAuthentication())
            .body(testArticle)
        .when()
            .post("/v1/article")
        .then()
            .spec(ResponseSpecifications.validatePositiveResponse())
            .body("message", Matchers.equalTo("Article created"));
    }

    @Test(description = "Prueba para obtener todos los articulos.", groups = "useArticle")
    public void getAllArticlesTest(){
        given()
            .spec(RequestSpecifications.useJWTAuthentication())
        .when()
            .get("/v1/articles")
        .then()
            .statusCode(200)
            .body("results[0].data[0].id", Matchers.equalTo(articleId));
    }

    @Test(description = "Prueba para obtener un articulo.", groups = "useArticle")
    public void getAnArticleTest(){
            given()
                .spec(RequestSpecifications.useJWTAuthentication())
            .when()
                .get("/v1/article/" + articleId)
            .then()
                .body("data.id", Matchers.equalTo(articleId));
    }
}
