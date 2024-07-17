package lib.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;

public class IOSWelcomePageObject extends WelcomePageObject {
    static {
        TITLE_LOCATOR_ON_ONBOARDING = "id:org.wikipedia:id/primaryTextView";
        FIRST_SCREEN_TITLE = "The free encyclopedia";
        SECOND_SCREEN_TITLE = "New ways to explore";
        THIRD_SCREEN_TITLE = "Search in over 300 languages";
        FOURTH_SCREEN_TITLE = "Learn more about our privacy policy and terms of use";


        STEP_LEARN_MIRE_LINK = "xpath://XCUIElementTypeStaticText[@name='The free encyclopedia']";
        STEP_NEW_WAYS_TO_EXPLORE = "xpath://XCUIElementTypeStaticText[@name='New ways to explore']";
        STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK = "id:Search in over 300 languages";
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about our privacy policy and terms of use']";
        NEXT_LINK = "xpath://XCUIElementTypeButton[@name='Next']";
        GET_STARTED_LINK = "xpath://XCUIElementTypeButton[@name='Get started']";
        ONBOARDONG_DONE_BUTTON = "xpath://XCUIElementTypeButton[@name='Get started']";

        SKIP = "xpath://XCUIElementTypeButton[@name='Skip']";
        SCROLL_VIEW_CONTAINER_ANDROID = "id:org.wikipedia:id/scrollViewContainer";
    }

    public IOSWelcomePageObject(AppiumDriver driver) {
        super(driver);
    }
}
