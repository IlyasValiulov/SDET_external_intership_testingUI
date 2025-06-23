package tests.droppable;

import extensions.ProjectProperties;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.droppable.DroppablePage;
import tests.BaseTest;

import java.io.IOException;

@Epic("Тесты для Protractor")
@Feature("Тесты страницы входа")
public class DroppableTest extends BaseTest {
    private DroppablePage droppablePage;
    private static final String resourceName = "droppablePage";

    public DroppableTest() throws IOException {
        super(resourceName);
    }

    @BeforeMethod
    @Step("Инициализация страницы")
    public void setUp() {
        droppablePage = new DroppablePage(driver);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Тесты для формы входа")
    public void dragAndDropTest() throws IOException {
        SoftAssert softAssert = new SoftAssert();
        String actualDroppableText = droppablePage.getDroppableElementText();
        String expectedDroppableText = ProjectProperties.getProperty("expectedDroppableTextBeforeDragAndDrop");
        softAssert.assertEquals(actualDroppableText, expectedDroppableText);

        actualDroppableText = droppablePage
                .dragAndDropDraggableElement()
                .getDroppableElementText();
        expectedDroppableText = ProjectProperties.getProperty("expectedDroppableTextAfterDragAndDrop");
        softAssert.assertEquals(actualDroppableText, expectedDroppableText);
        softAssert.assertAll();
    }
}
