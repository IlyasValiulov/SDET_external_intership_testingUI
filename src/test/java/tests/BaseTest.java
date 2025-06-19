package tests;

import extensions.DriverFactory;
import extensions.ProjectProperties;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    private String link;
    private DriverFactory driverFactory = new DriverFactory();

    public BaseTest(String resourceName) throws IOException {
        link = ProjectProperties.getProperty(resourceName);
    }

    @BeforeMethod
    @Step("Инициализация драйвера")
    public void init() throws IOException {
        String browserName = ProjectProperties.getProperty("browserName");
        boolean grid = Boolean.parseBoolean(ProjectProperties.getProperty("grid"));
        driver = driverFactory.createDriver(browserName, grid);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(link);
    }

    @AfterMethod
    @Step("Закрытие драйвера и завершение теста")
    @Attachment(type = "image/png")
    public byte[] tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }

        if (driver != null) {
            driver.quit();
        }
        return null;
    }
}
