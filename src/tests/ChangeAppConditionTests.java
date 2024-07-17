package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.WelcomePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    //тесты на изменение состояний апки
    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);
        onboardingPageObject.skipOnboarding();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);;
        String title_before_rotation = articlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String title_after_rotation = articlePageObject.getArticleTitle();

        assertTrue(
                "title before and after rotation not equals",
                title_after_rotation.equals(title_before_rotation));

        this.rotateScreenPortrait();
        String title_after_second_rotation = articlePageObject.getArticleTitle();

        assertTrue(
                "title before and after rotation not equals",
                title_before_rotation.equals(title_after_second_rotation));
    }

    @Test
    public void testSearchArticleInBackground() {
        WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);
        onboardingPageObject.skipOnboarding();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");
        this.backgroundApp(3);
        searchPageObject.waitForSearchResult("Java (programming language)");

    }

}
