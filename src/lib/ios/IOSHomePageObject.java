package lib.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.HomePageObject;

public class IOSHomePageObject extends HomePageObject {

    static {
        MAIN_TOOLBAR = "xpath://XCUIElementTypeToolbar[@name='Toolbar']";
    }

    public IOSHomePageObject(AppiumDriver driver) {
        super(driver);
    }
}
