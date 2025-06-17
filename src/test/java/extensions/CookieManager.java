package extensions;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pages.sql_ex.MainPage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class CookieManager {
    private final String cookieFile;

    private MainPage mainPage;

    public CookieManager() throws IOException {
        cookieFile = ProjectProperties.getProperty("cookieFile");
    }

    public void writeCookies(WebDriver driver) {
        Set<Cookie> cookies = driver.manage().getCookies();
        try(FileWriter writer = new FileWriter(cookieFile)) {
            for (Cookie cookie : cookies) {
                writer.write(cookie.getName() + "=" + cookie.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readCookies(WebDriver driver) {
        try (BufferedReader reader = new BufferedReader(new FileReader(cookieFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String name = parts[0];
                    String value = parts[1];
                    driver.manage().addCookie(new Cookie(name, value));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void authUser(WebDriver driver, String login, String password) {
        if (existCookies()) {
            readCookies(driver);
            driver.navigate().refresh();
        }
        else {
            mainPage = new MainPage(driver);
            mainPage.setLogin(login);
            mainPage.setPassword(password);
            mainPage.submitForm();
            writeCookies(driver);
        }
    }

    public boolean existCookies() {
        if (cookieFile == null) return false;
        return new File(cookieFile).exists();
    }
}
