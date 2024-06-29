package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final By loginText = By.xpath(".//main/div/h2");
    private final By emailField = By.xpath(".//*[@type='text']");
    private final By passwordField = By.xpath(".//*[@type='password']");
    private final By loginButton = By.xpath("//div/form/button");
    private final By registerButton = By.xpath("//div/p[1]/*[@href='/register']");
    private final By recoverButton = By.xpath("//div/p[2]/*[@href='/forgot-password']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> driver.findElement(loginText).isDisplayed());
    }

    public void clickEmail() {
        driver.findElement(emailField).click();
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickPassword() {
        driver.findElement(passwordField).click();
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterLoginForm(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoginButtonVisible() {
        return driver.findElement(loginButton).isDisplayed();
    }

    public void clickRegistrationButton() {
        driver.findElement(registerButton).click();
    }

    public void clickRecoverButton() {
        driver.findElement(recoverButton).click();
    }
}
