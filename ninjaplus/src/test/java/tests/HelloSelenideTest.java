package tests;//import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.isChrome;

public class HelloSelenideTest {

    @Test
    public void OnAir() {
//        int valorA = 10;
//        int valorB = 15;
//        int total = valorA + valorB;
//
//        Assert.assertEquals(total, 25);
        isChrome();
        open("http://ninjaplus-web:5000/login");
        assertEquals(title(), "Ninja+");
    }
}
