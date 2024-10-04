package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoverPasswordPage {
    private WebDriver driver;
    private final By recoverPasswordText = By.xpath(".//main/div/h2");
    private final By loginButton = By.xpath(".//main/div/div/p/*[@href='/login']");

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    public void waitForLoadRecoverPasswordPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> driver.findElement(recoverPasswordText).isDisplayed());
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
