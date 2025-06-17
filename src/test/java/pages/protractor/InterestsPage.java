package pages.protractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

import java.util.concurrent.TimeUnit;

public class InterestsPage extends BasePage {
    @FindBy(name = "009")
    private WebElement radioButton;

    @FindBy(css = ".ng-binding")
    private WebElement json;

    @FindBy(linkText = "Next Section")
    private WebElement button;

    private ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(InterestsPage.class);

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

    @Step("Нажатие на кнопку Next Section")
    public PaymentPage clickButton() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        button.click();
        return new PaymentPage(driver);
    }

    @Step("Извлечение данных из json поля")
    public String getRadioButtonData() {
        try {
            JsonNode node = mapper.readTree(json.getText());
            return node.get("type").asText();
        }
        catch (JsonProcessingException ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Step("Очистка полей страницы")
    public void clearAllFields() {
        if (radioButton.isSelected()) {
            radioButton.click();
        }
    }
}
