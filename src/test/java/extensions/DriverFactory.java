package extensions;

import enums.ProjectBrowser;
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
            return createRemoteDriver(browserName);
        return createLocalDriver(browserName);
    }

    private WebDriver createLocalDriver(String browserName) {
        WebDriver driver = null;
        ProjectBrowser browser = ProjectBrowser.fromString(browserName);
        switch (browser) {
            case Firefox:
                driver = new FirefoxDriver();
                break;
            case Chrome:
                driver = new ChromeDriver();
                break;
            case Edge:
                driver = new EdgeDriver();
                break;
            case Internet_explorer:
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

    private WebDriver createRemoteDriver(String browserName) throws IOException {
        String hub_url = ProjectProperties.getProperty("hub_url");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ProjectBrowser browser = ProjectBrowser.fromString(browserName);
        capabilities.setBrowserName(browser.getBrowserName());
        return new RemoteWebDriver(new URL(hub_url), capabilities);
    }
}
