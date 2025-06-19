package tests.protractor;

import extensions.ProjectProperties;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.protractor.InterestsPage;
import pages.protractor.ProfilePage;
import tests.BaseTest;

import java.io.IOException;

@Epic("Тесты для Protractor")
@Feature("Тесты страницы интересов")
public class InterestsTests extends BaseTest {
    ProfilePage profilePage;
    InterestsPage interestsPage;
    static final String resourseNamePage = "profilePageLink";

    public InterestsTests() throws IOException {
        super(resourseNamePage);
    }

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
        String actualText = interestsPage.getRadioButtonData();
        String expectedText = ProjectProperties.getProperty("radioButtonXbox");
        Assert.assertEquals(actualText, expectedText);
    }

    @AfterMethod
    @Step("Очистка тестовых данных страницы")
    public void tearDown() {
        interestsPage.clearAllFields();
    }
}
