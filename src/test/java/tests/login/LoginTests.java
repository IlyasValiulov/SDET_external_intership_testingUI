package tests.login;

import extensions.LoginDataProvider;
import models.LoginData;
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
import pages.login.HomePage;
import pages.login.LoginPage;
import tests.BaseTest;

import java.io.IOException;

@Epic("Тесты для Protractor")
@Feature("Тесты страницы входа")
public class LoginTests extends BaseTest {
    private LoginPage loginPage;
    static final String resourseNamePage = "loginPageLink";

    public LoginTests() throws IOException {
        super(resourseNamePage);
    }

    @BeforeMethod
    @Step("Инициализация страницы входа")
    public void setUp() {
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "testLoginData", dataProviderClass = LoginDataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Тесты для формы входа")
    public void submitFormTest(LoginData loginData) throws IOException {
        if (loginData.isSucceed()) {
            HomePage homePage = loginPage.submitFormSucceed(loginData.getUsername(), loginData.getPassword(), loginData.getUsernameDescription());
            String exceptedH1 = ProjectProperties.getProperty("HomePageH1");
            Assert.assertEquals(exceptedH1, homePage.getH1Value());
        }
        else {
            loginPage = loginPage.submitFormNotSucceed(loginData.getUsername(), loginData.getPassword(), loginData.getUsernameDescription());
            String exceptedMessage = ProjectProperties.getProperty("LoginMessage");
            Assert.assertEquals(exceptedMessage, loginPage.getExceptionText());
        }
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Story("Падающий тест для проверки работы скриншотов")
    public void submitFailedTest() throws IOException {
        String username = "username";
        String password = "password";
        String usernameDescription = "usrDesc";
        HomePage homePage = loginPage.submitFormSucceed(username, password, usernameDescription);

        String exceptedH1 = ProjectProperties.getProperty("HomePageH1");
        Assert.assertEquals(exceptedH1, homePage.getH1Value());
    }
}
