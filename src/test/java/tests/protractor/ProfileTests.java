package tests.protractor;

import extensions.ProjectProperties;
import extensions.User;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.protractor.ProfilePage;
import tests.BaseTest;

import java.io.IOException;

@Epic("Тесты для Protractor")
@Feature("Тесты страницы профиля")
public class ProfileTests extends BaseTest {
    ProfilePage profilePage;
    static final String resourseNamePage = "profilePageLink";

    public ProfileTests() throws IOException {
        super(resourseNamePage);
    }

    @BeforeMethod
    @Step("Инициализация драйвера на странице")
    public void setUp() {
        profilePage = new ProfilePage(driver);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Тест с корретными данными name и email")
    public void profileWithCorrectNameAndEmailTest() throws IOException {
        String name = ProjectProperties.getProperty("name");
        String email =  ProjectProperties.getProperty("email");
        User userInput = new User(name, email);
        profilePage.inputProfileData(userInput);
        User userOutput = profilePage.getProfileData();
        Assert.assertEquals(userInput, userOutput);
    }

    @AfterMethod
    @Step("Очистка тестовых данных страницы")
    public void tearDown() {
        profilePage.clearAllFields();
    }
}
