package tests.protractor;

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
import pages.protractor.InterestsPage;
import pages.protractor.PaymentPage;
import pages.protractor.ProfilePage;
import tests.BaseTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Epic("Тесты для Protractor")
@Feature("Тесты страницы оплаты")
public class PaymentTests extends BaseTest {
    ProfilePage profilePage;
    InterestsPage interestsPage;
    PaymentPage paymentPage;
    static final String resourseNamePage = "profilePageLink";

    public PaymentTests() throws IOException {
        super(resourseNamePage);
    }

    @BeforeMethod
    @Step("Инициализация драйвера на странице")
    public void setUp() {
        profilePage = new ProfilePage(driver);
        interestsPage = profilePage.clickButton();
        paymentPage = interestsPage.clickButton();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Успешный тест оплаты")
    public void paymentSuccessfulTest() throws IOException {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        paymentPage.clickButtonSubmit();
        String actualMessage = paymentPage.getAlertText();
        String expectedMessage = ProjectProperties.getProperty("submitMessage");
        Assert.assertEquals(actualMessage, expectedMessage);
    }
}
