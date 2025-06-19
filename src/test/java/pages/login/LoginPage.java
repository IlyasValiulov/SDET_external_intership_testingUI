package pages.login;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class LoginPage extends BasePage {
    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "formly_1_input_username_0")
    private WebElement usernameDescription;

    @FindBy(css = ".btn")
    private WebElement submitButton;

    @FindBy(css = ".alert-danger")
    private WebElement exception;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод имени пользователя на страницу Login")
    public LoginPage inputUsername(String username) {
        this.username.sendKeys(username);
        return new LoginPage(driver);
    }

    @Step("Ввод пароля на страницу Login")
    public LoginPage inputPassword(String password) {
        this.password.sendKeys(password);
        return new LoginPage(driver);
    }

    @Step("Ввод описания пользователя на страницу Login")
    public LoginPage inputUsernameDescription(String usernameDescription) {
        this.usernameDescription.sendKeys(usernameDescription);
        return new LoginPage(driver);
    }

    @Step("Заполнение формы данными")
    public void fillForm(String username, String password, String description) {
        inputUsername(username);
        inputPassword(password);
        inputUsernameDescription(description);
    }

    @Step("Отправка формы с данными проходящие валидацию")
    public HomePage submitFormSucceed(String username, String password, String description) {
        fillForm(username, password, description);
        submitButton.click();
        return new HomePage(driver);
    }

    @Step("Отправка формы с данными не проходящие валидации")
    public LoginPage submitFormNotSucceed(String username, String password, String description) {
        fillForm(username, password, description);
        submitButton.click();
        return new LoginPage(driver);
    }

    @Step("Извлечение текста из ошибки")
    public String getExceptionText() {
        if (exception.isDisplayed()) {
            return exception.getText();
        }
        return "";
    }
}
