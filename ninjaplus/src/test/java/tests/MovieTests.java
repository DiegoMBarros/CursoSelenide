package tests;

import common.BaseTest;
import libs.Database;
import models.MovieModel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class MovieTests extends BaseTest {

//    private static LoginPage login;
//    private static SideBar side;
//    private static MoviePage movie;
//    Informações migradas para a common > BaseTest
    private Database db;

    @BeforeMethod
    public void setup() {
//        Configuration.browser = "chrome";
//        Configuration.baseUrl = "http://ninjaplus-web:5000";
//
//        login = new LoginPage();
//        side = new SideBar();
//        movie = new MoviePage();
//    Informações migradas para a common > BaseTest

        login
                .open()
                .with("diego@ninjaplus.com", "pwd123");

        side.loggedUser().shouldHave(text("Diego"));
    }

    @BeforeSuite
    public void delorean() {
        db = new Database();
        db.resetMovies();
    }

    @Test
    public void shouldRegisterANewMovie() {
//        String title = "Jumanji - Próxima fase";
//        String status = "Pré-venda";
//        String year = "2020";
//        String realeaseDate = "16/01/2020";
//        List<String> cast = Arrays.asList("The Rock", "Jack Black", "Kevin Hart", "Karen Gillan", "Danny DeVito");
//        String plot = "Tentado a revisitar o mundo de Jumanji, Spencer decide "
//            +"consertar o bug no jogo do game que sejam transportados ao local";

        MovieModel movieData = new MovieModel(
                "Jumanji - Próxima fase",
                "Pré-venda",
                2020,
                "16/01/2020",
                Arrays.asList("The Rock", "Jack Black", "Kevin Hart", "Karen Gillan", "Danny DeVito"),
                "Tentado a revisitar o mundo de Jumanji, Spencer decide "
                +"consertar o bug no jogo do game que sejam transportados ao local",
                "jumanji2.jpg"
        );

        //Database db = new Database();
        //db.deleteMovie(movieData.title);

//        movie.add().create(title, status, year, realeaseDate, cast, plot);
//        movie.add().create(movieData);
//        movie.items().findBy(text(movieData.title)).shouldBe(visible);
        movie
                .add()
                .create(movieData)
                .items().findBy(text(movieData.title)).shouldBe(visible);

    }

    @Test
    public void shouldSearchOneMovie() {
        movie.search("Batman").items().shouldHaveSize(2);
    }

    @AfterMethod
    public void cleanup() {
        login.clearSession();
    }
}
