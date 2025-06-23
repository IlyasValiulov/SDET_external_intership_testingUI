package pages.http;

import extensions.ProjectProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.io.IOException;

public class HttpAuthenticationPage extends BasePage {
    @FindBy(id = "displayImage")
    private WebElement dispayImageButton;

    @FindBy(css = ".transparent")
    private WebElement authenticatedImage;

    private final String link;

    public HttpAuthenticationPage(WebDriver driver, String resourceName) throws IOException {
        super(driver);
        link = ProjectProperties.getProperty(resourceName);
    }

    @Step("Авторизация")
    public HttpAuthenticationPage authentication(String login, String password) {
        httpBasicAuth(login, password);
        return this;
    }

    @Step("Проверка, что авторизация успешно прошла")
    public boolean isAuthenticatedImageDisplayed() {
        return authenticatedImage.isDisplayed();
    }

    @Step("Авторизация механизмом Basic Auth")
    private void httpBasicAuth(String login, String password) {
        String urlWithAuth = getUrlWithBasicAuth(login, password);
        driver.get(urlWithAuth);
    }

    @Step("Получение строки с логином и паролем для Basic Auth")
    private String getUrlWithBasicAuth(String login, String password) {
        String target = "//";
        String replacement = target + login + ":" + password + "@";
        return link.replace(target, replacement);
    }
}
