package tests;

import lib.CoreTestCase;
import lib.ui.HomePageObject;
import lib.ui.MainPageObject;
import lib.ui.OnboardingPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class OnboardingTests extends CoreTestCase {

    //EX5
    @Test
    public void testCheckTheTitlesOfTheOnboarding() {
        OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);

        onboardingPageObject.checkTitleAndSwipe(1);
        onboardingPageObject.checkTitleAndSwipe(2);
        onboardingPageObject.checkTitleAndSwipe(3);
        onboardingPageObject.checkTitleAndSwipe(4);
        onboardingPageObject.clickGetStarted();

        HomePageObject homePageObject = new HomePageObject(driver);
        homePageObject.isMainPageDisplayed();
    }
}
