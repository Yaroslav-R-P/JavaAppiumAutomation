package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.android.AndroidMyListsPageObject;
import lib.ios.IOSMyListsPageObject;
import lib.ui.MyListsPageObject;

public class MyListsPageObjectFactory {
    public static MyListsPageObject get(AppiumDriver driver) {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidMyListsPageObject(driver);
        } else {
            return new IOSMyListsPageObject(driver);
        }
    }
}
