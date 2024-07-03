package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OnboardingPageObject extends MainPageObject {

    private static final String
    TITLE_LOCATOR_ON_ONBOARDING = "org.wikipedia:id/primaryTextView",
    FIRST_SCREEN_TITLE = "The Free Encyclopedia\n" + "…in over 300 languages",
    SECOND_SCREEN_TITLE = "New ways to explore",
    THIRD_SCREEN_TITLE = "Reading lists with sync",
    FOURTH_SCREEN_TITLE = "Data & Privacy",
    ONBOARDONG_DONE_BUTTON = "org.wikipedia:id/fragment_onboarding_done_button";

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
    public void checkTitleAndSwipe(int screenNumber) {
        this.waitForElementPresent(By.id("org.wikipedia:id/scrollViewContainer"),
                "Cannot find onboarding",
                5);
        WebElement element = waitForElementPresent(By.id(TITLE_LOCATOR_ON_ONBOARDING), "Cannot find title", 5);
        String expectedResult = null;
        String error = "The screen title is incorrect, swipe is cancelled";
        if (screenNumber == 1) {
            expectedResult = FIRST_SCREEN_TITLE;
        }
        if (screenNumber == 2) {
            expectedResult = SECOND_SCREEN_TITLE;
        }
        if (screenNumber == 3) {
            expectedResult = THIRD_SCREEN_TITLE;
        }
        if (screenNumber == 4) {
            expectedResult = FOURTH_SCREEN_TITLE;
        }

        String actualTitleText = element.getText();
        if (expectedResult.equals(actualTitleText)) {
            swipeLeftQuick();
        } else {
            throw  new AssertionError(error);
        }
    }

    public void clickGetStarted() {
        this.waitForElementAndClick(By.id(ONBOARDONG_DONE_BUTTON),
                "Cannot find element 'onboarding_done_button'",
                5
        );
    }

}
