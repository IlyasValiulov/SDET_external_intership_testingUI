package extensions;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JsExecutor {
    @Step("Убрать фокус с поля ввода")
    public void removeFocusFromInput(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("document.activeElement.blur();");
    }

    @Step("Проверка наличия вертикального скролла")
    public boolean hasVerticalScroll(WebDriver driver) {
        return (Boolean)((JavascriptExecutor) driver).executeScript("return document.documentElement.scrollHeight > document.documentElement.clientHeight;");
    }

    @Step("Проверка наличия горизонтального скролла")
    public boolean hasHorizontalScroll(WebDriver driver) {
        return (Boolean)((JavascriptExecutor) driver).executeScript("return document.documentElement.scrollWidth > document.documentElement.clientWidth;");
    }
}
