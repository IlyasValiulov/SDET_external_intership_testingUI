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
    @FindBy(xpath = "//input[@name='name']")
    private WebElement name;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;

    @FindBy(xpath = "//pre")
    private WebElement json;

    @FindBy(xpath = "//a[contains(@class, 'btn')]")
    private WebElement button;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Запись данных в поля профиля")
    public ProfilePage inputProfileData(User user) {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        clearAllFields();
        this.name.sendKeys(user.name);
        this.email.sendKeys(user.email);
        return new ProfilePage(driver);
    }

    @Step("Извлечение данных в json поле")
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
