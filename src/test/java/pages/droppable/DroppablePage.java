package pages.droppable;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.function.Supplier;

public class DroppablePage extends BasePage {
    @FindBy(css= ".demo-frame")
    private WebElement iframe;

    @FindBy(id = "draggable")
    private WebElement draggableElement;

    @FindBy(id = "droppable")
    private WebElement droppableElement;

    public DroppablePage(WebDriver driver) {
        super(driver);
    }

    @Step("Перетаскивание элемента draggableElement в принимающий droppableElement")
    public void dragAndDropDraggableElement() {
        performInFrame(() -> {
            new Actions(driver).dragAndDrop(draggableElement, droppableElement).perform();
        });
    }

    @Step("Извлечение значения принимающего элемента")
    public String getDroppableElementText() {
        return performInFrame(() -> droppableElement.getText());
    }

    private <T> T performInFrame(Supplier<T> action) {
        try {
            driver.switchTo().frame(iframe);
            return action.get();
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    private void performInFrame(Runnable action) {
        try {
            driver.switchTo().frame(iframe);
            action.run();
        } finally {
            driver.switchTo().defaultContent();
        }
    }
}
