import Helpers.RequestHelper;
import io.restassured.RestAssured;
import org.testng.annotations.*;

public class Base {

    protected int articleId;

    @Parameters("host")
    @BeforeSuite(alwaysRun = true)
    public void setup(String host){
        RestAssured.baseURI = host;
    }

    //
    @BeforeMethod(groups = "useArticle")
    public void createArticle(){
        int articleId = RequestHelper.createRandomArticleAndGetId();
    }

    @AfterMethod(groups = "useArticle")
    public void deleteArticle(){
        RequestHelper.cleanUpArticle(articleId);
    }
}
