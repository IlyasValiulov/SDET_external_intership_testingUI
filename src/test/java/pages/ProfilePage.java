package pages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import extensions.User;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class ProfilePage extends BasePage {
    @FindBy(name = "name")
    private WebElement name;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(css = ".ng-binding")
    private WebElement json;

    @FindBy(linkText = "Next Section")
    private WebElement button;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Запись имени в поле name")
    public ProfilePage inputName(String name) {
        this.name.sendKeys(name);
        return new ProfilePage(driver);
    }

    @Step("Запись почты в поле email")
    public ProfilePage inputEmail(String email) {
        this.email.sendKeys(email);
        return new ProfilePage(driver);
    }

    @Step("Запись данных в поля профиля")
    public ProfilePage inputProfileData(User user) {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        clearAllFields();
        inputName(user.name);
        inputEmail(user.email);
        return new ProfilePage(driver);
    }

    @Step("Нажати на кнопку Next Section")
    public InterestsPage clickButton() {
        button.click();
        return new InterestsPage(driver);
    }

    @Step("Извлечение данных из json поля")
    public User getProfileData() {
        User user = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            user = mapper.readValue(json.getText(), User.class);
        }
        catch (JsonProcessingException ex) {
            ex.getMessage();
        }
        return user;
    }

    @Step("Очистка полей страницы")
    public void clearAllFields() {
        name.clear();
        email.clear();
    }
}
