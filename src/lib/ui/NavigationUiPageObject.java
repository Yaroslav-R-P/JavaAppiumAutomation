package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class NavigationUiPageObject extends MainPageObject {

    protected static String
        MY_LISTS_LINK;

     public void openMyLists() {
         waitForElementAndClick(MY_LISTS_LINK, "Cannot find MY_LISTS_LINK element", 5);
     }

    public NavigationUiPageObject(AppiumDriver driver) {
        super(driver);
    }
}
