package lib.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    static {
                SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
                SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
                SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://android.widget.TextView[@text='{SUBSTRING}']";
                SEARCH_RESULT_ELEMENT = "xpath://androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup";
                SEARCH_EMPTY_RESULT = "xpath://android.widget.TextView[@text='No results']";
                SEARCH_EMPTY_PAGE_LOGO = "id:org.wikipedia:id/search_empty_image";
    }



}
