import Helpers.RequestHelper;
import io.restassured.RestAssured;
import org.testng.annotations.*;

public class Base {

    protected int articleId;
    protected int postId;
    protected int commentId;

    @Parameters("host")

    @BeforeSuite(alwaysRun = true)
    public void setup(String host){
        RestAssured.baseURI = host;
    }


    //Articles
    @BeforeMethod(groups = "useArticle")
    public void createArticle(){ articleId = RequestHelper.createRandomArticleAndGetId(); }

    @AfterMethod(groups = "useArticle")
    public void deleteArticle(){ RequestHelper.cleanUpArticle(articleId); }

//    @AfterMethod(groups = "createArticle")
//    public void deleteCreatedArticle(){ RequestHelper.cleanUpArticle(articleId); }


    //Posts
    @BeforeMethod(groups = "usePost")
    public void createPost(){
        postId = RequestHelper.createRandomPostAndGetId();
    }

    @AfterMethod(groups = "usePost")
    public void deletePost(){
        RequestHelper.cleanUpPost(postId);
    }

//    @AfterMethod(groups = "createPost")
//    public void deleteCreatedPost(){ RequestHelper.cleanUpPost(postId); }


    //Comments
    //@BeforeMethod(groups = "useComment")
    public void createComment(){
        createPost();
        commentId = RequestHelper.createRandomCommentAndGetId(postId);
    }

    //@AfterMethod(groups = "useComment")
    public void deleteComment(){
        RequestHelper.cleanUpComment(postId, commentId);
        deletePost();
    }

//    @AfterMethod(groups = "createComment")
//    public void deleteCreatedComment(){ RequestHelper.cleanUpComment(postId, commentId); }
}
