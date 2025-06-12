package pages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class InterestsPage extends BasePage {
    @FindBy(name = "009")
    private WebElement radioButton;

    @FindBy(css = ".ng-binding")
    private WebElement json;

    @FindBy(linkText = "Next Section")
    private WebElement button;

    public InterestsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Выбор варианта xbox")
    private InterestsPage clickRadioButton() {
        radioButton.click();
        return new InterestsPage(driver);
    }

    @Step("Запись данных в поля Interests")
    public InterestsPage inputInterestsData() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        clearAllFields();
        clickRadioButton();
        return new InterestsPage(driver);
    }

    @Step("Нажати на кнопку Next Section")
    public PaymentPage clickButton() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        button.click();
        return new PaymentPage(driver);
    }

    @Step("Извлечение данных из json поля")
    public String getRadioButtonData() {
        String radioButton = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = node = mapper.readTree(json.getText());
            radioButton = node.get("type").asText();
        }
        catch (JsonProcessingException ex) {
            ex.getMessage();
        }
        return radioButton;
    }

    @Step("Очистка полей страницы")
    public void clearAllFields() {
        if (radioButton.isSelected()) {
            radioButton.click();
        }
    }
}
