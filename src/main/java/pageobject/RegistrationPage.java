package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private final By registrationText = By.xpath(".//*[text()='Регистрация']");
    private final By nameField = By.xpath("//div/form/fieldset[1]//input[@name='name']");
    private final By emailField = By.xpath("//div/form/fieldset[2]//input[@name='name']");
    private final By passwordField = By.xpath("//div/form/fieldset[3]//input[@name='Пароль']");
    private final By registrationButton = By.xpath(".//*[text()='Зарегистрироваться']");
    private final By incorrectPasswordText = By.xpath(".//p[text() = 'Некорректный пароль']");
    private final By loginButton = By.xpath("//main/div/div/p/*[@href='/login']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadingRegistrationPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> driver.findElement(registrationText).isDisplayed());
    }

    public void clickName() {
        driver.findElement(nameField).click();
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
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

    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void enterRegistrationForm(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public boolean isIncorrectPasswordTextVisible() {
        return driver.findElement(incorrectPasswordText).isDisplayed();
    }

}
