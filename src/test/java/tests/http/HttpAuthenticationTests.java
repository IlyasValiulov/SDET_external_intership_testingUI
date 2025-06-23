package tests.http;

import extensions.ProjectProperties;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.http.HttpAuthenticationPage;
import tests.BaseTest;

import java.io.IOException;

@Epic("Тесты для Http")
@Feature("Тесты аутентификации")
public class HttpAuthenticationTests extends BaseTest {
    private HttpAuthenticationPage httpAuthenticationPage;
    private static final String resourceName = "httpAuthenticationPage";
    private static final String resourceAuthenticationLinl = "httpAuthenticationLink";

    public HttpAuthenticationTests() throws IOException {
        super(resourceName);
    }

    @BeforeMethod
    @Step("Инициализация драйвера на странице")
    public void setUp() throws IOException {
        httpAuthenticationPage = new HttpAuthenticationPage(driver, resourceAuthenticationLinl);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Тест аутентификации")
    public void authenticationTest() throws IOException {
        String login = ProjectProperties.getProperty("httpLogin");
        String password = ProjectProperties.getProperty("httpPassword");
        boolean actual = httpAuthenticationPage
                .authentication(login, password)
                .isAuthenticatedImageDisplayed();
        Assert.assertTrue(actual);
    }
}
