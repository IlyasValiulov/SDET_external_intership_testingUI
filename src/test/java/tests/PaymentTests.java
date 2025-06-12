package tests;

import extensions.ProjectProperties;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InterestsPage;
import pages.PaymentPage;
import pages.ProfilePage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Epic("Тесты для Protractor")
@Feature("Тесты страницы оплаты")
public class PaymentTests extends BaseTest {
    ProfilePage profilePage;
    InterestsPage interestsPage;
    PaymentPage paymentPage;

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
        String testmessage = paymentPage.getAlertText();
        String needMessage = ProjectProperties.getProperty("submitMessage");
        Assert.assertEquals(testmessage, needMessage);
    }
}
