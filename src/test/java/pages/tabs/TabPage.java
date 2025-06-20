package pages.tabs;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class TabPage extends BasePage {
    @FindBy(css = ".demo-frame")
    private WebElement iframe;

    @FindBy(linkText = "New Browser Tab")
    private WebElement tabLink;

    public TabPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажатие на ссылку и переход на новую вкладку от главного окна")
    public void newTabFromMainWindow() {
        driver.switchTo().frame(iframe);
        tabLink.click();
        switchNextWindow();
        driver.switchTo().defaultContent();
    }

    @Step("Нажатие на ссылку и переход на новую вкладку")
    public void newTabFromWindow() {
        tabLink.click();
    }

    @Step("Получение количества дескрипторов вкладок")
    public int getCountOfTabs() {
        return driver.getWindowHandles().toArray().length;
    }

    @Step("Переключение вкладки")
    private void switchNextWindow() {
        int indexTab = getCountOfTabs() - 1;
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[indexTab]);
    }
}
