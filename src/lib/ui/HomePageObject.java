package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class HomePageObject extends MainPageObject {
    public HomePageObject(AppiumDriver driver) {
        super(driver);
    }
    private static final String
            MAIN_TOOLBAR = "id:org.wikipedia:id/main_toolbar";

    public void isMainPageDisplayed() {
        this.waitForElementPresent(MAIN_TOOLBAR, "Cannot find MAIN_TOOLBAR, home page is not displayed", 10);
    }
}
