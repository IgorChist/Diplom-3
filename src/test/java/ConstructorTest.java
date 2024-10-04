import config.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;

import static api.EndPoints.HOST;

public class ConstructorTest {
    private WebDriver driver;
    MainPage mainPage;

    @Before
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        driver.get(HOST);

        mainPage = new MainPage(driver);
        mainPage.waitLoadingMainPage();
    }

    @Test
    @DisplayName("Переход к разделу Булки")
    public void shouldChoiceSectionBuns() {
        mainPage.clickSauceButton();
        mainPage.clickBunsButton();
        boolean isBunsChoiceSectionVisible = mainPage.isChoiceSectionVisible();
        Assert.assertTrue(isBunsChoiceSectionVisible);
    }
    @Test
    @DisplayName("Переход к разделу Соусы")
    public void shouldChoiceSectionSauce() {
        mainPage.clickSauceButton();
        boolean isSauceChoiceSectionVisible = mainPage.isChoiceSectionVisible();
        Assert.assertTrue(isSauceChoiceSectionVisible);
    }
    @Test
    @DisplayName("Переход к разделу Начинки")
    public void shouldChoiceSectionFilling() {
        mainPage.clickFillingButton();
        boolean isFillingChoiceSectionVisible = mainPage.isChoiceSectionVisible();
        Assert.assertTrue(isFillingChoiceSectionVisible);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
