package tests;

import common.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;

public class LoginTestes extends BaseTest {

//    protected static LoginPage login;
//    protected static SideBar side;
//    Informações migradas para a common > BaseTest

    @DataProvider(name = "login-alerts")
    public Object[][] loginProvider() {
        return new Object[][]{
                {"diego@ninjaplus.com", "abc123", "Usuário e/ou senha inválidos"},
                {"404@gmail.com", "abc123", "Usuário e/ou senha inválidos"},
                {"", "abc123", "Opps. Cadê o email?"},
                {"diego@ninjaplus.com", "", "Opps. Cadê a senha?"}
        };
    }

//    @BeforeMethod
//    public void start() {
//        Configuration.browser = "chrome";
//        Configuration.baseUrl = "http://ninjaplus-web:5000";
//
//        login = new LoginPage();
//        side = new SideBar();
//    }
    //    Informações migradas para a common > BaseTest

    //DDT (Data Driven Testing)
    @Test
    public void shouldSeeLoggedUser() {
//        login.open();
//        login.with("diego@ninjaplus.com", "pwd123");
        login
                .open()
                .with("diego@ninjaplus.com", "pwd123");

        side.loggedUser().shouldHave(text("Diego"));

//        isChrome();
//        open("http://ninjaplus-web:5000");
//        $("#emailId").setValue("diego@ninjaplus.com");
//        $("input[name=email]").setValue("diego@ninjaplus.com");
//        $("#passId").setValue("pwd123");
//        $("#login").click();
//        $(byText("Entrar")).click();
//        $(".user .info span").shouldHave(text("Diego"));
    }

    @Test(dataProvider = "login-alerts")
    public void shouldSeeLoginAlerts(String email, String pass, String expectAlert) {
//        isChrome();
//        executeJavaScript("localStorage.clear();");
//        open("http://ninjaplus-web:5000");
//        $("input[name=email]").setValue(email);
//        $("#passId").setValue(pass);
//        $(byText("Entrar")).click();
//        $(".alert span").shouldHave(text(expectAlert));

        login
                .open()
                .with(email, pass)
                .alert().shouldHave(text(expectAlert));
    }

    @AfterMethod
    public void cleanup() {
        login.clearSession();
    }
}
