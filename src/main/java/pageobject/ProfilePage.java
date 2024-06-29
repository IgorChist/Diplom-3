package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private WebDriver driver;
    private final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");
    private final By profileButton = By.xpath(".//a[text() = 'Профиль']");
    private final By logoutButton = By.xpath(".//button[text() = 'Выход']");
    private final By logoButton = By.xpath(".//header/nav/div");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadingProfilePage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(profileButton));
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    public boolean isLogoutButtonVisible() {
        return driver.findElement(logoutButton).isDisplayed();
    }

    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }
}
