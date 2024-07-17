package lib.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUiPageObject;

public class IOSNavigationUi extends NavigationUiPageObject {

    static {
        MY_LISTS_LINK = "id:Saved";
    }


    public IOSNavigationUi(AppiumDriver driver) {
        super(driver);
    }
}
