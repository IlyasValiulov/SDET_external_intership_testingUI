package pages.alert;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.function.Supplier;

public class AlertPage extends BasePage {
    @FindBy(linkText = "INPUT ALERT")
    private WebElement inputAlert;

    @FindBy(css = ".demo-frame")
    private WebElement iframe;

    @FindBy(linkText = "Click the button to demonstrate the Input box.")
    private WebElement inputBoxButton;

    @FindBy(id = "demo")
    private WebElement label;

    public AlertPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод имени")
    public void inputName(String name) {
        clickInputAlert();
        performInFrame(() -> {
            inputBoxButton.click();
            inputNameInAlert(name);
        });
    }

    @Step("Проверка, что введенное имя используется в label")
    public boolean checkUsageName(String name) {
        String text = performInFrame(() -> label.getText());
        return text.contains(name);
    }

    @Step("Нажатие на кнопку Input Alert")
    private void clickInputAlert() {
        inputAlert.click();
    }

    @Step("Ввод имени в alert")
    private void inputNameInAlert(String name) {
        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys(name);
        promptAlert.accept();
    }

    @Step("Переключение в iframe и выполнение действия с возвратом")
    private <T> T performInFrame(Supplier<T> action) {
        try {
            driver.switchTo().frame(iframe);
            return action.get();
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    @Step("Переключение в iframe и выполнение действия")
    private void performInFrame(Runnable action) {
        try {
            driver.switchTo().frame(iframe);
            action.run();
        } finally {
            driver.switchTo().defaultContent();
        }
    }
}
