package lib.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUiPageObject;

public class AndroidNavigationUiPageObject extends NavigationUiPageObject {

    static {
        MY_LISTS_LINK = "id:org.wikipedia:id/nav_tab_reading_lists";
    }

    public AndroidNavigationUiPageObject(AppiumDriver driver) {
        super(driver);
    }
}
