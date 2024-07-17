package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class HomePageObject extends MainPageObject {
    public HomePageObject(AppiumDriver driver) {
        super(driver);
    }
    protected static String
            MAIN_TOOLBAR;

    public void isMainPageDisplayed() {
        this.waitForElementPresent(MAIN_TOOLBAR, "Cannot find MAIN_TOOLBAR, home page is not displayed", 10);
    }
}
