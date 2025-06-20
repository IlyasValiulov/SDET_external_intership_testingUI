package tests.tabs;

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
import pages.tabs.TabPage;
import tests.BaseTest;

import java.io.IOException;

@Epic("Тесты для Tabs")
@Feature("Тесты страницы вкладок и окон")
public class TabTests extends BaseTest {
    private TabPage tabPage;
    private static final String resourceName = "tabLink";

    public TabTests() throws IOException {
        super(resourceName);
    }

    @BeforeMethod
    @Step("Инициализация драйвера на странице")
    public void setUp() {
        tabPage = new TabPage(driver);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Тест с окнами")
    public void tabTest() throws IOException {
        tabPage.newTabFromMainWindow();
        tabPage.newTabFromWindow();

        int actualCount = tabPage.getCountOfTabs();
        int expectedCount = Integer.parseInt(ProjectProperties.getProperty("countOfTabs"));
        Assert.assertEquals(actualCount, expectedCount);
    }
}
