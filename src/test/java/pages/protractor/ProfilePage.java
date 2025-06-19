package pages.protractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

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

    private ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(ProfilePage.class);

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
        inputName(user.getName());
        inputEmail(user.getEmail());
        return new ProfilePage(driver);
    }

    @Step("Нажати на кнопку Next Section")
    public InterestsPage clickButton() {
        button.click();
        return new InterestsPage(driver);
    }

    @Step("Извлечение данных из json поля")
    public User getProfileData() {
        try {
            return mapper.readValue(json.getText(), User.class);
        }
        catch (JsonProcessingException ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Step("Очистка полей страницы")
    public void clearAllFields() {
        name.clear();
        email.clear();
    }
}
