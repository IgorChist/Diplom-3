import api.User;
import api.UserSteps;
import config.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;

import static api.EndPoints.HOST;


public class RegistrationTest {
    private WebDriver driver;
    UserSteps userSteps = new UserSteps();
    String accessToken;
    User user;
    MainPage mainPage;
    RegistrationPage registrationPage;
    LoginPage loginPage;

    @Before
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        driver.get(HOST);

        user = new User();
        user.setEmail(RandomStringUtils.randomAlphabetic(10) + "@yandex.ru");
        user.setPassword(RandomStringUtils.randomAlphabetic(10));
        user.setName(RandomStringUtils.randomAlphabetic(6));

        mainPage = new MainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);

        mainPage.waitLoadingMainPage();
        mainPage.clickUserCabinetButton();
        loginPage.waitForLoadLoginPage();
        loginPage.clickRegistrationButton();
        registrationPage.waitLoadingRegistrationPage();
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void shouldRegistrationNewUser(){
        registrationPage.enterRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.clickRegistrationButton();
        mainPage.clickUserCabinetButton();
        loginPage.enterLoginForm(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        boolean isCreateOrderButtonVisible = mainPage.isCreateOrderButton();
        Assert.assertTrue(isCreateOrderButtonVisible);
    };

    @Test
    @DisplayName("Появляется ошибка при использовании пароля короче 6 символов")
    public void shouldShowIncorrectPasswordText(){
        String correctPassword = user.getPassword();
        user.setPassword(RandomStringUtils.randomAlphabetic(5));
        registrationPage.enterRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.clickRegistrationButton();
        user.setPassword(correctPassword);
        boolean isTextVisible = registrationPage.isIncorrectPasswordTextVisible();
        Assert.assertTrue(isTextVisible);
    };

    @After
    public void tearDown() {
        Response response = userSteps.loginUser(user);
        accessToken = response.then().extract().path("accessToken");
        if (accessToken != null) {userSteps.deleteUser(accessToken);}
        driver.quit();
    }
}

