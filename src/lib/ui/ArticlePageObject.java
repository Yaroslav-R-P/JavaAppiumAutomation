package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
    TITLE_TPL = "xpath:(//android.widget.TextView[@text='{TITLE_TEXT}'])[1]",
    //TITLE = "(//android.widget.TextView[@class='android.widget.TextView'])[1]",


    SAVE_BUTTON = "id:org.wikipedia:id/page_save",
    OK_BUTTON_IN_THE_SAVE_WINDOW = "id:android:id/button1",

    ADD_TO_LIST_BUTTON_IN_SNACKBAR = "id:org.wikipedia:id/snackbar_action",
    VIEW_LIST_BUTTON_IN_SNACKBAR = "id:org.wikipedia:id/snackbar_action",
    MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
    ANDROID_TITLE_ELEMENT_FOR_TESTS = "xpath://android.widget.TextView[@text=\"Java (programming language)\"]";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getTitleXpathByArticleName(String articleNameWithSubstring) {
        return TITLE_TPL.replace("{TITLE_TEXT}", articleNameWithSubstring);
    }

    public WebElement waitForTitleElement(String articleWithSubstring) {
        String titleXpath = getTitleXpathByArticleName(articleWithSubstring);
        return this.waitForElementPresent(titleXpath, "Cannot find article title on page", 20);
    }

    public String getArticleTitle() {
        WebElement element = waitForElementPresent(ANDROID_TITLE_ELEMENT_FOR_TESTS,"cannot find article title", 20);
        return element.getText();
    }

    public void addArticleToMyListAndGoToIt(String name_of_folder) {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find save page element",
                5);

        this.waitForElementAndClick(
               ADD_TO_LIST_BUTTON_IN_SNACKBAR,
                "Cannot find 'Add to list' button on snackbar",
                5);

        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                10
        );

        this.waitForElementAndClick(
                OK_BUTTON_IN_THE_SAVE_WINDOW,
                "Cannot find 'Ok' button",
                5);

        this.waitForElementAndClick(
                VIEW_LIST_BUTTON_IN_SNACKBAR,
                "Cannot find 'Ok' button on snackbar_action",
                5);



    }

    public void assertTheArticleHasATitle(String articleNameWithSubstring) {
    String title_xpath = getTitleXpathByArticleName(articleNameWithSubstring);
        this.waitForElementPresent(title_xpath, "The article has no title", 10);
    }





}

