package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class OnboardingPageObject extends MainPageObject {

    public OnboardingPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void skipOnboarding() {
        this.waitForElementPresent(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Cannot find skip_button", 10);
        this.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'onboarding_skip_button'",
                5
        );
    }
}
