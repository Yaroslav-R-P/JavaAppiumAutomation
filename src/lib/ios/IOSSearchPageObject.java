package lib.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name='Cancel']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING}']"; //тут можно через contains написать
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText";
        SEARCH_EMPTY_RESULT = "id:No results found"; //когда поиск не дал результатов
        SEARCH_EMPTY_PAGE_LOGO = "id:Settings"; // лого вики, когда поиск сброшен в 0 и страница пуста
    }
}
