package pages.sql_ex;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class MainPage extends BasePage {
    @FindBy(name = "login")
    private WebElement login;

    @FindBy(name = "psw")
    private WebElement password;

    @FindBy(name = "subm1")
    private WebElement submit;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Заполнение логина в MainPage")
    public MainPage setLogin(String login) {
        this.login.sendKeys(login);
        return new MainPage(driver);
    }

    @Step("Заполнение пароля в MainPage")
    public MainPage setPassword(String password) {
        this.password.sendKeys(password);
        return new MainPage(driver);
    }

    @Step("Отправка формы в MainPage")
    public MainPage submitForm() {
        this.submit.click();
        return new MainPage(driver);
    }

    @Step("Проверка что элементы на странице существуют")
    public boolean elementsExist() {
        try {
            return login.isEnabled() && password.isDisplayed() && submit.isDisplayed();
        }
        catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Step("Проверка что поле login активно")
    public boolean isLoginActive() {
        return login.isSelected();
    }
}
