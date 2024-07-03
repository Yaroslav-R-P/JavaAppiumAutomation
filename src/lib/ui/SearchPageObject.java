package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class SearchPageObject extends MainPageObject {

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }


    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    /* TEMPLATES METHODS */


    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "org.wikipedia:id/search_src_text",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//android.widget.TextView[@text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENT = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup",
        SEARCH_EMPTY_RESULT = "//android.widget.TextView[@text='No results']",
        SEARCH_EMPTY_PAGE_LOGO = "org.wikipedia:id/search_empty_image";


    public void initSearchInput() {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input before clicking");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT),search_line, "Cannot find and type search input ", 5);
    }

    public void waitForSearchResult(String substring) {

        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring" + substring, 20);
    }

    public void clickByArticleWithSubstring(String substring) {

        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring" + substring, 20);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click cancel button", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find by the request",
                20
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT), "Cannot find empty result element", 20);
    }

    public void assertThereIsNotResultOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Cannot find any results");
    }

    public void assertSearchReturnedSeveralResults() {
        int amount_of_results = getAmountOfFoundArticles();
        Assert.assertTrue("The search returned less than two results", amount_of_results > 1);

    }

    public void assertSearchResultsBlockIsHidden() {
        this.assertElementPresent(By.id(SEARCH_EMPTY_PAGE_LOGO), "SEARCH_EMPTY_PAGE_LOGO is not found");
    }
}


