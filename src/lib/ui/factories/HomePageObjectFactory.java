package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.android.AndroidHomePageObject;
import lib.ios.IOSHomePageObject;
import lib.ui.HomePageObject;

public class HomePageObjectFactory {

    public static HomePageObject get(AppiumDriver driver) {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidHomePageObject(driver);
        } else {
            return new IOSHomePageObject(driver);
        }
    }
}
