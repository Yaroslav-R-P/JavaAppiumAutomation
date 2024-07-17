package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.android.AndroidArticlePageObject;
import lib.ios.IOSArticlePageObject;
import lib.ui.ArticlePageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(AppiumDriver driver) {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        } else {
            return new IOSArticlePageObject(driver);
        }
    }
}
