package lib.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;

public class AndroidWelcomePageObject extends WelcomePageObject {

    static {
            TITLE_LOCATOR_ON_ONBOARDING = "id:org.wikipedia:id/primaryTextView";
            FIRST_SCREEN_TITLE = "The Free Encyclopedia\n" + "…in over 300 languages";
            SECOND_SCREEN_TITLE = "New ways to explore";
            THIRD_SCREEN_TITLE = "Reading lists with sync";
            FOURTH_SCREEN_TITLE = "Data & Privacy";
            ONBOARDONG_DONE_BUTTON = "id:org.wikipedia:id/fragment_onboarding_done_button";
            STEP_LEARN_MIRE_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about Wikipedia']";
            STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore";
            STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK = "xpath://XCUIElementTypeButton[@name='Add or edit preferred languages']";
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about data collected']";
            NEXT_LINK = "xpath://XCUIElementTypeButton[@name='Next']";
            GET_STARTED_LINK = "xpath://XCUIElementTypeButton[@name='Get started']";
            SKIP = "id:org.wikipedia:id/fragment_onboarding_skip_button";
            SCROLL_VIEW_CONTAINER_ANDROID = "id:org.wikipedia:id/scrollViewContainer";
    }
    public AndroidWelcomePageObject(AppiumDriver driver) {
        super(driver);
    }
}
