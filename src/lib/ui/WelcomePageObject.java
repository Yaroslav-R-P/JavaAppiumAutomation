package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WelcomePageObject extends MainPageObject {

    private static final String
    TITLE_LOCATOR_ON_ONBOARDING = "id:org.wikipedia:id/primaryTextView",
    FIRST_SCREEN_TITLE = "The Free Encyclopedia\n" + "…in over 300 languages",
    SECOND_SCREEN_TITLE = "New ways to explore",
    THIRD_SCREEN_TITLE = "Reading lists with sync",
    FOURTH_SCREEN_TITLE = "Data & Privacy",
    ONBOARDONG_DONE_BUTTON = "id:org.wikipedia:id/fragment_onboarding_done_button",

    STEP_LEARN_MIRE_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about Wikipedia']",
    STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK = "xpath://XCUIElementTypeButton[@name='Add or edit preferred languages']",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about data collected']",
    NEXT_LINK = "xpath://XCUIElementTypeButton[@name='Next']",
    GET_STARTED_LINK = "xpath://XCUIElementTypeButton[@name='Get started']",
    SKIP_BUTTON_ON_ANDROID = "id:org.wikipedia:id/fragment_onboarding_skip_button",
    SCROLL_VIEW_CONTAINER_ANDROID = "id:org.wikipedia:id/scrollViewContainer";


    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }


    public void skipOnboarding() {
        this.waitForElementPresent(SKIP_BUTTON_ON_ANDROID, "Cannot find skip_button", 10);
        this.waitForElementAndClick(SKIP_BUTTON_ON_ANDROID,
                "Cannot find 'onboarding_skip_button'",
                5
        );
    }
    public void checkTitleAndSwipe(int screenNumber) {
        this.waitForElementPresent(SCROLL_VIEW_CONTAINER_ANDROID,
                "Cannot find onboarding",
                5);
        WebElement element = waitForElementPresent(TITLE_LOCATOR_ON_ONBOARDING, "Cannot find title", 5);
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
        this.waitForElementAndClick(ONBOARDONG_DONE_BUTTON,
                "Cannot find element 'onboarding_done_button'",
                5
        );
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MIRE_LINK, "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find and click 'Next' link", 10);
    }

    public void clickGetStartedButtonFrom_iOS() {
        this.waitForElementAndClick(GET_STARTED_LINK, "Cannot find and click 'Get started' link", 10);
    }

    public void waitForNewWayToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE, "Cannot find 'NewWay To Explore Text'", 10);
    }

    public void waitForAddOrEditPreferredLangText() {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK, "Cannot find 'Add or edit preferred languages' link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find 'Learn more about data collected' link", 10);
    }


}
