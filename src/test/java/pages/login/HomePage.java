package pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class HomePage extends BasePage  {
    @FindBy(xpath = "//h1")
    private WebElement h1;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getH1Value() {
        return h1.getText();
    }
}
