import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

//--------------------------------------------------------
//разнести всё тут и удалить этот класс
//--------------------------------------------------------

public class FirstTest extends CoreTestCase  {



    @Test
    public void testPlaceholderTextIsValid() {
        OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);
        onboardingPageObject.skipOnboarding();

        MainPageObject mainPageObject = new MainPageObject(driver);
        WebElement placeholderElement = mainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find placeholder",
                5
        );

        assertTrue(mainPageObject.assertElementHasText(placeholderElement, "Search Wikipedia",
                "placeholder text in search is not equal to 'Search Wikipedia'"));
    }
    @Test
    public void testCheckingWordsInSearch() throws InterruptedException {
        OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);
        onboardingPageObject.skipOnboarding();

        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        Thread.sleep(30000);
        List<WebElement> searchResult = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));

        int allSearchResults = searchResult.size();
        List<WebElement> list = searchResult.stream()
                .filter(element -> element.getText().toLowerCase().contains("java"))
                .collect(Collectors.toList());
        int searchResultsContainsJava = list.size();

        assertEquals(allSearchResults, searchResultsContainsJava);
    }

    @Test
    public void testSwipeOnboarding() {
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.waitForElementPresent(By.id("org.wikipedia:id/scrollViewContainer"), "Cannot find onboarding");
        mainPageObject.swipeLeftToFindElement(
                By.id("org.wikipedia:id/fragment_onboarding_done_button"),
                "Cannot find element 'onboarding_done_button'",
                20
        );
    }




}
