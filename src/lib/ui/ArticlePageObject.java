package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
    TITLE = "//android.widget.TextView[@text='Java (programming language)']",
    //TITLE = "(//android.widget.TextView[@class='android.widget.TextView'])[1]",


    SAVE_BUTTON = "org.wikipedia:id/page_save",
    OK_BUTTON_IN_THE_SAVE_WINDOW = "android:id/button1",

    ADD_TO_LIST_BUTTON_IN_SNACKBAR = "org.wikipedia:id/snackbar_action",
    VIEW_LIST_BUTTON_IN_SNACKBAR = "org.wikipedia:id/snackbar_action",
    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page", 20);
    }


    public String getArticleTitle() {
        WebElement element = waitForElementPresent(By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),"cannot find article title", 20);
        return element.getText();
    }

    public void addArticleToMyListAndGoToIt(String name_of_folder) {
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),
                "Cannot find save page element",
                5);

        this.waitForElementAndClick(
                By.id(ADD_TO_LIST_BUTTON_IN_SNACKBAR),
                "Cannot find 'Add to list' button on snackbar",
                5);

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                10
        );

        this.waitForElementAndClick(
                By.id(OK_BUTTON_IN_THE_SAVE_WINDOW),
                "Cannot find 'Ok' button",
                5);

        this.waitForElementAndClick(
                By.id(VIEW_LIST_BUTTON_IN_SNACKBAR),
                "Cannot find 'Ok' button on snackbar_action",
                5);



    }
}

