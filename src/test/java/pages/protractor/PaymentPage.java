package pages.protractor;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;

public class PaymentPage extends BasePage {
    @FindBy(css = ".btn")
    private WebElement buttonSubmit;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажатие на кнопку submit")
    public void clickButtonSubmit() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(buttonSubmit)).click();
    }

    @Step("Извлечение сообщения из alert")
    public String getAlertText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText();
    }
}
