package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.WelcomePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    //тесты на статьи
    @Test
    public void testCompereArticleTitle() {
        WelcomePageObject onboardingPageObject = new WelcomePageObject(driver);
        onboardingPageObject.skipOnboarding();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        String articleWithSubstring = "Java (programming language)";
        searchPageObject.clickByArticleWithSubstring(articleWithSubstring);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String articleTitle = articlePageObject.getArticleTitle();

        assertEquals("We see unexpected title!", "Java (programming language)", articleTitle);
    }
    //EX6
    @Test
    public void testTitlePresence() {
        String articleNameWithSubstring = "Java (programming language)";

        WelcomePageObject onboardingPageObject = new WelcomePageObject(driver);
        onboardingPageObject.skipOnboarding();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(articleNameWithSubstring);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.assertTheArticleHasATitle(articleNameWithSubstring);

    }
}
