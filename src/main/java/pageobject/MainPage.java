package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private final By makeBurgerText = By.xpath(".//main/section[1]/h1");
    private final By loginButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By userCabinetButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By bunsButton = By.xpath(".//span[text() = 'Булки']");
    private final By sauceButton = By.xpath(".//span[text() = 'Соусы']");
    private final By fillingButton = By.xpath(".//span[text() = 'Начинки']");
    private final By choiceSectionOfIngredients = By.xpath(".//div[contains(@class,'tab_tab_type_current')]");
    private final By createOrderButton = By.xpath(".//button[text() = 'Оформить заказ']");
    private final By ingredientsContainer = By.className("constructor-element");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitLoadingMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(makeBurgerText));
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickUserCabinetButton() {
        driver.findElement(userCabinetButton).click();
    }

    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }

    public void clickFillingButton() {
        driver.findElement(fillingButton).click();
    }

    public boolean isChoiceSectionVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(choiceSectionOfIngredients));
        return driver.findElement(choiceSectionOfIngredients).isDisplayed();
    }

    public boolean isIngredientsContainerVisible() {
        return driver.findElement(ingredientsContainer).isDisplayed();
    }

    public boolean isCreateOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        return driver.findElement(createOrderButton).isDisplayed();
    }
}
