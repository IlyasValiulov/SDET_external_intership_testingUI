package extensions;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Set;

public class CookieManager {
    private final String cookieFile;

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

    public boolean existCookies() {
        if (cookieFile == null) return false;
        File file = new File(cookieFile);
        return file.exists();
    }
}
