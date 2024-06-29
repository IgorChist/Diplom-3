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
import pageobject.*;


import static api.EndPoints.HOST;

public class ProfilePageTest {
    private WebDriver driver;
    UserSteps userSteps = new UserSteps();
    String accessToken;
    User user;
    MainPage mainPage;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    ProfilePage profilePage;

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
        profilePage = new ProfilePage(driver);

        mainPage.waitLoadingMainPage();
        mainPage.clickLoginButton();
        loginPage.waitForLoadLoginPage();
        loginPage.enterLoginForm(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        mainPage.waitLoadingMainPage();
        mainPage.clickUserCabinetButton();
        profilePage.waitLoadingProfilePage();
    }

    @Test
    @DisplayName("Переход в «Личный кабинет» с главной страницы")
    public void openUserCabinetFromMainPage() {

        boolean isLogoutButtonVisible = profilePage.isLogoutButtonVisible();
        Assert.assertTrue(isLogoutButtonVisible);
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void openConstructorByClickConstructorButton() {
        profilePage.clickConstructorButton();
        mainPage.waitLoadingMainPage();
        boolean isIngredientsContainerVisible = mainPage.isIngredientsContainerVisible();
        Assert.assertTrue(isIngredientsContainerVisible);
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на на логотип Stellar Burgers")
    public void openConstructorByClickLogoButton() {
        profilePage.clickLogoButton();
        mainPage.waitLoadingMainPage();
        boolean isIngredientsContainerVisible = mainPage.isIngredientsContainerVisible();
        Assert.assertTrue(isIngredientsContainerVisible);
    }
    @Test
    @DisplayName("Выход из аккаунта по кнопке «Выйти» в личном кабинете")
    public void userCanLogout() {
        profilePage.clickLogoutButton();
        loginPage.waitForLoadLoginPage();
        boolean isLoginButtonVisible = loginPage.isLoginButtonVisible();
        Assert.assertTrue(isLoginButtonVisible);
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