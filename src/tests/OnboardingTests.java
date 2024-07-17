package tests;

import lib.CoreTestCase;
import lib.ui.HomePageObject;
import lib.ui.MainPageObject;
import lib.ui.WelcomePageObject;
import lib.ui.factories.HomePageObjectFactory;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

public class OnboardingTests extends CoreTestCase {

    //EX5
    @Test
    public void testCheckTheTitlesOfTheOnboarding() throws InterruptedException {
        WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);

        onboardingPageObject.checkTitleAndSwipe(1);
        onboardingPageObject.checkTitleAndSwipe(2);
        onboardingPageObject.checkTitleAndSwipe(3);
        onboardingPageObject.checkTitleAndSwipe(4);
        onboardingPageObject.clickGetStarted();
        HomePageObject homePageObject = HomePageObjectFactory.get(driver);
        homePageObject.isMainPageDisplayed();
    }
}
