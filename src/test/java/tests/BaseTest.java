package tests;

import extensions.ProjectProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    private String link;

    public BaseTest(String resourceName) throws IOException {
        link = ProjectProperties.getProperty(resourceName);
    }

    @BeforeMethod
    @Step("Инициализация драйвера")
    public void init() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(link);
    }

    @AfterMethod
    @Step("Закрытие драйвера и завершение теста")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
