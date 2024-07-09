package tests;

import lib.CoreTestCase;
import lib.ui.HomePageObject;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class OnboardingTests extends CoreTestCase {

    //EX5
    @Test
    public void testCheckTheTitlesOfTheOnboarding() {
        WelcomePageObject onboardingPageObject = new WelcomePageObject(driver);

        onboardingPageObject.checkTitleAndSwipe(1);
        onboardingPageObject.checkTitleAndSwipe(2);
        onboardingPageObject.checkTitleAndSwipe(3);
        onboardingPageObject.checkTitleAndSwipe(4);
        onboardingPageObject.clickGetStarted();

        HomePageObject homePageObject = new HomePageObject(driver);
        homePageObject.isMainPageDisplayed();
    }
}
