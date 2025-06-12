package tests;

import extensions.ProjectProperties;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InterestsPage;
import pages.ProfilePage;

import java.io.IOException;

@Epic("Тесты для Protractor")
@Feature("Тесты страницы интересов")
public class InterestsTests extends BaseTest {
    ProfilePage profilePage;
    InterestsPage interestsPage;

    @BeforeMethod
    @Step("Инициализация драйвера на странице")
    public void setUp() {
        profilePage = new ProfilePage(driver);
        interestsPage = profilePage.clickButton();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Тест с выбранным radiobutton")
    public void interestWithRadiobuttonTest() throws IOException {
        interestsPage.inputInterestsData();
        String radioButtonOutput = interestsPage.getRadioButtonData();
        String expectedText = ProjectProperties.getProperty("radioButtonXbox");
        Assert.assertEquals(radioButtonOutput, expectedText);
    }

    @AfterMethod
    @Step("Очистка тестовых данных страницы")
    public void tearDown() {
        interestsPage.clearAllFields();
    }
}
