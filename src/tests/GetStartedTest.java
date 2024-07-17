package tests;

import lib.CoreTestCase;
//import lib.iOSTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome() {
        if(Platform.getInstance().isAndroid()) {
            return;
        }
        WelcomePageObject welcomePageObject = WelcomePageObjectFactory.get(driver);
        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForNewWayToExploreText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForAddOrEditPreferredLangText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForLearnMoreAboutDataCollectedText();
        welcomePageObject.clickGetStartedButtonFrom_iOS();
    }
}
