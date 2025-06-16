package tests.sql_ex;

import extensions.CookieManager;
import extensions.JsExecutor;
import extensions.ProjectProperties;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.sql_ex.MainPage;
import tests.BaseTest;

import java.io.IOException;

public class MainPageTests extends BaseTest {
    private MainPage mainPage;
    private CookieManager cookieManager;
    private JsExecutor js;
    private static final String resourseNamePage = "sqlexPageLink";

    public MainPageTests() throws IOException {
        super(resourseNamePage);
        cookieManager = new CookieManager();
    }

    @BeforeMethod
    @Step("Инициализация драйвера на странице")
    public void setUp() {
        mainPage = new MainPage(driver);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Тест авторизации при использовании куков")
    public void profileWithCorrectNameAndEmailTest() throws IOException {
        if (cookieManager.existCookies()) {
            cookieManager.readCookies(driver);
            driver.navigate().refresh();
        }
        else {
            String login = ProjectProperties.getProperty("sqlLogin");
            String password = ProjectProperties.getProperty("sqlPassword");
            mainPage.setLogin(login);
            mainPage.setPassword(password);
            mainPage.submitForm();
            cookieManager.writeCookies(driver);
        }
        Assert.assertFalse(mainPage.elementsExist());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Тест проверки работоспособности функций JsEcecutor")
    public void jsExecutorTest() {
        js = new JsExecutor();
        SoftAssert softAssert = new SoftAssert();

        mainPage.setLogin("Делаем инпут login активным");
        js.removeFocusFromInput(driver);
        softAssert.assertEquals(mainPage.isLoginActive(), false);

        boolean hasVerticalScroll = js.hasVerticalScroll(driver);
        softAssert.assertEquals(hasVerticalScroll, true);
        boolean hasHorizontalScroll = js.hasHorizontalScroll(driver);
        softAssert.assertEquals(hasHorizontalScroll, true);

        softAssert.assertAll();
    }
}
