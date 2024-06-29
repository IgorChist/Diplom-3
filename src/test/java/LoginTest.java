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
import pageobject.RecoverPasswordPage;
import pageobject.RegistrationPage;

import static api.EndPoints.HOST;

public class LoginTest {
    private WebDriver driver;
    UserSteps userSteps = new UserSteps();
    String accessToken;
    User user;
    MainPage mainPage;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    RecoverPasswordPage recoverPasswordPage;

    @Before
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        driver.get(HOST);

        user = new User();
        user.setEmail(RandomStringUtils.randomAlphabetic(10) + "@yandex.ru");
        user.setPassword(RandomStringUtils.randomAlphabetic(10));
        user.setName(RandomStringUtils.randomAlphabetic(6));

        userSteps.createUser(user);
        mainPage = new MainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        recoverPasswordPage = new RecoverPasswordPage(driver);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void userCanLoginByLoginButton() {
        mainPage.waitLoadingMainPage();
        mainPage.clickLoginButton();
        loginPage.waitForLoadLoginPage();
        loginPage.enterLoginForm(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        mainPage.waitLoadingMainPage();
        boolean isCreateOrderButtonVisible = mainPage.isCreateOrderButton();
        Assert.assertTrue(isCreateOrderButtonVisible);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void userCanLoginByUserCabinetButton() {
        mainPage.waitLoadingMainPage();
        mainPage.clickUserCabinetButton();
        loginPage.waitForLoadLoginPage();
        loginPage.enterLoginForm(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        mainPage.waitLoadingMainPage();
        boolean isCreateOrderButtonVisible = mainPage.isCreateOrderButton();
        Assert.assertTrue(isCreateOrderButtonVisible);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void userCanLoginByLoginButtonOnRegistrationPage() {
        mainPage.waitLoadingMainPage();
        mainPage.clickUserCabinetButton();
        loginPage.waitForLoadLoginPage();
        loginPage.clickRegistrationButton();
        registrationPage.waitLoadingRegistrationPage();
        registrationPage.clickLoginButton();
        loginPage.waitForLoadLoginPage();
        loginPage.enterLoginForm(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        mainPage.waitLoadingMainPage();
        boolean isCreateOrderButtonVisible = mainPage.isCreateOrderButton();
        Assert.assertTrue(isCreateOrderButtonVisible);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void userCanLoginByLoginButtonOnRecoverPasswordPage() {
        mainPage.waitLoadingMainPage();
        mainPage.clickUserCabinetButton();
        loginPage.waitForLoadLoginPage();
        loginPage.clickRecoverButton();
        recoverPasswordPage.waitForLoadRecoverPasswordPage();
        recoverPasswordPage.clickLoginButton();
        loginPage.waitForLoadLoginPage();
        loginPage.enterLoginForm(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        mainPage.waitLoadingMainPage();
        boolean isCreateOrderButtonVisible = mainPage.isCreateOrderButton();
        Assert.assertTrue(isCreateOrderButtonVisible);
    }

    @After
    public void tearDown() {
        Response response = userSteps.loginUser(user);
        accessToken = response.then().extract().path("accessToken");
        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
        driver.quit();
    }
}
