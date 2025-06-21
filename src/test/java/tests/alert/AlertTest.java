package tests.alert;

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
import pages.alert.AlertPage;
import tests.BaseTest;

import java.io.IOException;

@Epic("Тесты для Alert")
@Feature("Тесты работы с уведомлениями на странице Alert")
public class AlertTest extends BaseTest {
    private AlertPage alertPage;
    private static final String resourceName = "alertLink";

    public AlertTest() throws IOException {
        super(resourceName);
    }

    @BeforeMethod
    @Step("Инициализация драйвера на странице")
    public void setUp() {
        alertPage = new AlertPage(driver);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Тесты ввода имени")
    public void alertTest() throws IOException {
        String name = ProjectProperties.getProperty("alertName");
        alertPage.inputName(name);
        boolean usageName = alertPage.checkUsageName(name);
        Assert.assertTrue(usageName);
    }
}
