package lib.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE_TEXT}']";
        SAVE_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "xpath://XCUIElementTypeButton[@name='Search']";
        ANDROID_TITLE_ELEMENT_FOR_TESTS = "xpath://XCUIElementTypeStaticText[@name='Java (programming language)']";
        GO_HOME_LINK = "id:Wikipedia, return to Explore";


//        OK_BUTTON_IN_THE_SAVE_WINDOW = "id:android:id/button1";
//        ADD_TO_LIST_BUTTON_IN_SNACKBAR = "id:org.wikipedia:id/snackbar_action";
//        VIEW_LIST_BUTTON_IN_SNACKBAR = "id:org.wikipedia:id/snackbar_action";
//        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
