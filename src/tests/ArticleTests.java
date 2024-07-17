package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.WelcomePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    //тесты на статьи
    @Test
    public void testCompereArticleTitle() {
        WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);
        onboardingPageObject.skipOnboarding();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        String articleWithSubstring = "Java (programming language)";
        searchPageObject.clickByArticleWithSubstring(articleWithSubstring);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String articleTitle = articlePageObject.getArticleTitle();

        assertEquals("We see unexpected title!", "Java (programming language)", articleTitle);
    }
    //EX6
    @Test
    public void testTitlePresence() {
        String articleNameWithSubstring = "Java (programming language)";

        WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);
        onboardingPageObject.skipOnboarding();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(articleNameWithSubstring);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.assertTheArticleHasATitle(articleNameWithSubstring);

    }
}
