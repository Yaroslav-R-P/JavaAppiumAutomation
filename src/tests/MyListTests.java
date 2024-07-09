package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

public class MyListTests extends CoreTestCase {
    //тесты списков статей
    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        WelcomePageObject onboardingPageObject = new WelcomePageObject(driver);
        onboardingPageObject.skipOnboarding();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        String articleWithSubstring = "Java (programming language)";
        searchPageObject.clickByArticleWithSubstring(articleWithSubstring);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement(articleWithSubstring);
        String article_title = articlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        articlePageObject.addArticleToMyListAndGoToIt(name_of_folder);

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.swipeByArticleToDelete(article_title);

    }
}
