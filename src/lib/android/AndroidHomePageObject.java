package lib.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.HomePageObject;

public class AndroidHomePageObject extends HomePageObject {

    static {
        MAIN_TOOLBAR = "id:org.wikipedia:id/main_toolbar";
    }

    public AndroidHomePageObject(AppiumDriver driver) {
        super(driver);
    }
}
