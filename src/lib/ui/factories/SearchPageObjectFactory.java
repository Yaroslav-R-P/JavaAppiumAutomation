package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.android.AndroidSearchPageObject;
import lib.ios.IOSSearchPageObject;
import lib.ui.SearchPageObject;

import javax.crypto.SealedObject;

public class SearchPageObjectFactory {

    public static SearchPageObject get(AppiumDriver driver) {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        } else {
            return new IOSSearchPageObject(driver);
        }
    }
}
