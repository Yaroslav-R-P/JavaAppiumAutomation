package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.android.AndroidNavigationUiPageObject;
import lib.ios.IOSNavigationUi;
import lib.ui.NavigationUiPageObject;

public class NavigationUIFactory {

    public static NavigationUiPageObject get(AppiumDriver driver) {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUiPageObject(driver);
        } else {
            return new IOSNavigationUi(driver);
        }
    }
}
