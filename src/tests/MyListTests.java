package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.*;
import org.junit.Test;

public class MyListTests extends CoreTestCase {
    //тесты списков статей
    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        WelcomePageObject onboardingPageObject = WelcomePageObjectFactory.get(driver);
        onboardingPageObject.skipOnboarding();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        String articleWithSubstring = "Java (programming language)";
        searchPageObject.clickByArticleWithSubstring(articleWithSubstring);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);;
        articlePageObject.waitForTitleElement(articleWithSubstring);
        String article_title = articlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyListAndGoToIt(name_of_folder);
            MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
            myListsPageObject.swipeByArticleToDelete(article_title);
        } else {
            articlePageObject.addArticlesToMySave();
            articlePageObject.goToTheHomeWikiPage();

            NavigationUiPageObject navigationUiPageObject = NavigationUIFactory.get(driver);
            navigationUiPageObject.openMyLists();
            MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
            myListsPageObject.closeSynchronizationWindow();
            myListsPageObject.swipeAndTapDeleteArticle();

        }
    }
}
