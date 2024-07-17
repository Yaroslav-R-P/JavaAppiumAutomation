package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class WelcomePageObject extends MainPageObject {

    protected static String
    TITLE_LOCATOR_ON_ONBOARDING,
    FIRST_SCREEN_TITLE,
    SECOND_SCREEN_TITLE,
    THIRD_SCREEN_TITLE,
    FOURTH_SCREEN_TITLE,
    ONBOARDONG_DONE_BUTTON,

    STEP_LEARN_MIRE_LINK,
    STEP_NEW_WAYS_TO_EXPLORE,
    STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK,
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
    NEXT_LINK,
    GET_STARTED_LINK,
    SKIP,
    SCROLL_VIEW_CONTAINER_ANDROID;


    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }


    public void skipOnboarding() {
        this.waitForElementPresent(SKIP, "Cannot find skip_button", 10);
        this.waitForElementAndClick(SKIP,
                "Cannot find 'onboarding_skip_button'",
                5
        );
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP, "Cannot find skip button", 10);
    }
    public void checkTitleAndSwipe(int screenNumber) {

        if(Platform.getInstance().isAndroid()){
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
        } else {
            String expectedResult = null;
            String actual_result = null;
            String error = "The screen title is incorrect, swipe is cancelled";

            if (screenNumber == 1) {
                expectedResult = FIRST_SCREEN_TITLE;
                actual_result = waitForElementAndGetAttribute(STEP_LEARN_MIRE_LINK, "Cannot find title text on first screen", 10);
            }
            if (screenNumber == 2) {
                expectedResult = SECOND_SCREEN_TITLE;
                actual_result = waitForElementAndGetAttribute(STEP_NEW_WAYS_TO_EXPLORE, "Cannot find title text on second screen", 10);
                if(expectedResult.equals(actual_result)) {swipeLeft(200);}}
            if (screenNumber == 3) {
                expectedResult = THIRD_SCREEN_TITLE;
                actual_result = waitForElementAndGetAttribute(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK, "Cannot find title text on third screen = "+ actual_result, 10);
                if(expectedResult.equals(actual_result)) {swipeLeft(200);}
            }
            if (screenNumber == 4) {
                expectedResult = FOURTH_SCREEN_TITLE;
                actual_result = waitForElementAndGetAttribute(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find title text on four screen", 10);
            }

            if (expectedResult.equals(actual_result)) {
                swipeLeft(200);
               // swipeLeftQuick();
            } else {
                throw  new AssertionError(error);
            }
        }

    }

//    public void checkTitleAndSwipe(int screenNumber) {
//        String expectedResult = null;
//        String actual_result = null;
//        String error = "The screen title is incorrect, swipe is cancelled";
//
//        if (screenNumber == 1) {
//            expectedResult = FIRST_SCREEN_TITLE;
//            actual_result = waitForElementAndGetAttribute(STEP_LEARN_MIRE_LINK, "Cannot find title text on first screen", 10);
//        }
//        if (screenNumber == 2) {
//            expectedResult = SECOND_SCREEN_TITLE;
//            actual_result = waitForElementAndGetAttribute(STEP_NEW_WAYS_TO_EXPLORE, "Cannot find title text on first screen", 10);
//        }
//        if (screenNumber == 3) {
//            expectedResult = THIRD_SCREEN_TITLE;
//            actual_result = waitForElementAndGetAttribute(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK, "Cannot find title text on first screen", 10);
//
//        }
//        if (screenNumber == 4) {
//            expectedResult = FOURTH_SCREEN_TITLE;
//            actual_result = waitForElementAndGetAttribute(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find title text on first screen", 10);
//
//        }
//
//
//        if (expectedResult.equals(actual_result)) {
//            swipeLeftQuick();
//        } else {
//            throw  new AssertionError(error);
//        }
//    }

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
