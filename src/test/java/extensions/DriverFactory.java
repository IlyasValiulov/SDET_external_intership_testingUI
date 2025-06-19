package extensions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;

public class DriverFactory {
    public WebDriver createDriver(String browserName, boolean grid) throws IOException {
        if (grid)
            return createDriverWithGrid(browserName);
        return createDriverWithoutGrid(browserName);
    }

    private WebDriver createDriverWithoutGrid(String browserName) {
        WebDriver driver = null;
        switch (browserName) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "internet_explorer":
                InternetExplorerOptions options = new InternetExplorerOptions();
                options.ignoreZoomSettings();
                options.requireWindowFocus();
                options.setCapability("ignoreProtectedModeSettings", true);
                options.setCapability("enablePersistentHover", false);
                options.setCapability("ie.ensureCleanSession", true);
                driver = new InternetExplorerDriver(options);
                break;
        }
        return driver;
    }

    private WebDriver createDriverWithGrid(String browserName) throws IOException {
        String hub_url = ProjectProperties.getProperty("hub_url");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (browserName) {
            case "chrome":
                capabilities.setBrowserName("chrome");
                break;
            case "firefox":
                capabilities.setBrowserName("firefox");
                break;
            case "edge":
                capabilities.setBrowserName("MicrosoftEdge");
                break;
            case "ie":
                capabilities.setBrowserName("internet explorer");
                break;
        }
        return new RemoteWebDriver(new URL(hub_url), capabilities);
    }
}
