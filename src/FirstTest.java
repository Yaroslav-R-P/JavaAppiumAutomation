import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

public class FirstTest {
    private AppiumDriver driver;
    private static final String TITLE_LOCATOR_ON_ONBOARDING = "org.wikipedia:id/primaryTextView";
    private static final String FIRST_SCREEN_TITLE = "The Free Encyclopedia\n" + "…in over 300 languages";
    private static final String SECOND_SCREEN_TITLE = "New ways to explore";
    private static final String THIRD_SCREEN_TITLE = "Reading lists with sync";
    private static final String FOURTH_SCREEN_TITLE = "Data & Privacy";

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
        capabilities.setCapability("appium:platformVersion", "8.0");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app", "/Users/yar/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'onboarding_skip_button'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                20
        );
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'onboarding_skip_button'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find element go back",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still presented on the page",
                5
        );

    }

    @Test
    public void testArticleTitle() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'onboarding_skip_button'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                20
        );

        WebElement titleElement = waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find article title",
                15
        );
        String article_title = titleElement.getText();

        Assert.assertEquals("We see unexpected title!", "Java (programming language)", article_title);
    }

    @Test
    public void testPlaceholderTextIsValid() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'onboarding_skip_button'",
                5
        );

        WebElement placeholderElement = waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find placeholder",
                5
        );

        Assert.assertTrue(assertElementHasText(placeholderElement, "Search Wikipedia",
                "placeholder text in search is not equal to 'Search Wikipedia'"));
    }

    @Test
    public void testCancelSearchResult() throws InterruptedException {

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'onboarding_skip_button'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        Thread.sleep(15000);
        List<WebElement> searchResult = driver.findElements(By.className("android.view.ViewGroup"));

        Assert.assertTrue("search results less than 2", searchResult.size() > 2);

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find and clean search field",
                5
        );

        waitForElementPresent(By.id("org.wikipedia:id/search_empty_message"), "Blank search page not displayed", 5);
    }

    @Test
    public void testCheckingWordsInSearch() throws InterruptedException {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'onboarding_skip_button'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
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

        Assert.assertEquals(allSearchResults, searchResultsContainsJava);
    }

    @Test
    public void testSwipeOnboarding() {
        waitForElementPresent(By.id("org.wikipedia:id/scrollViewContainer"), "Cannot find onboarding");
        swipeLeftToFindElement(
                By.id("org.wikipedia:id/fragment_onboarding_done_button"),
                "Cannot find element 'onboarding_done_button'",
                20

        );
    }

    @Test
    public void saveFirstArticleToMyList() throws InterruptedException {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'onboarding_skip_button'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text=\"Java (programming language)\"]"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                20
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find save page element",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find save page element",
                5);

        //Thread.sleep(10000);
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Cannot put text into articles folder input",
                10
        );

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Cannot find 'Ok' button",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find 'Ok' button",
                5);

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find saved element 'Java (programming language)'",
                10
        );

        swipeElementToLeft(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                600,
                "Cannot to swipe"
        );

        waitForElementNotPresent(By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "The element is displayed",
                5
        );
    }

    @Test
    public void testAmountOfNotEmtySearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'onboarding_skip_button'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        String search_line = "Linkin Park Diskography";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find search input",
                5
        );
        String search_result_locator = "android.view.ViewGroup";
        waitForElementPresent(
                By.className(search_result_locator),
                "Cannot find by the request" + search_line,
                20
        );
        int amount_of_search_results = getAmountOfElements(By.className(search_result_locator));
        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'onboarding_skip_button'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                20
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find article title",
                15
        );
        String title_before_rotation = waitForElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find title of article",
                20
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find title of article",
                20
        );

        Assert.assertTrue(
                "title before and after rotation not equals",
                title_after_rotation.equals(title_before_rotation));

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find title of article",
                20
        );

        Assert.assertTrue(
                "title before and after rotation not equals",
                title_before_rotation.equals(title_after_second_rotation));
    }

    @Test
    public void testSearchArticleInBackground() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'onboarding_skip_button'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                20
        );
        driver.runAppInBackground(3);

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find 'Object-oriented programming language' after returning in background",
                20
        );
    }

    @Test
    public void testCheckTheTitlesOfTheOnboarding() {
        waitForElementPresent(By.id("org.wikipedia:id/scrollViewContainer"),
                "Cannot find onboarding",
                5);

        swipeLeftIfElementIsValid(
                By.id(TITLE_LOCATOR_ON_ONBOARDING),
                FIRST_SCREEN_TITLE,
                "Cannot find expected title"
        );

        swipeLeftIfElementIsValid(
                By.id(TITLE_LOCATOR_ON_ONBOARDING),
                SECOND_SCREEN_TITLE,
                "Cannot find expected title"
        );

        swipeLeftIfElementIsValid(
                By.id(TITLE_LOCATOR_ON_ONBOARDING),
                THIRD_SCREEN_TITLE,
                "Cannot find expected title"
        );

        swipeLeftIfElementIsValid(
                By.id(TITLE_LOCATOR_ON_ONBOARDING),
                FOURTH_SCREEN_TITLE,
                "Cannot find expected title"
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_done_button"),
                "Cannot find element 'onboarding_done_button'",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/main_toolbar_wordmark"),
                "Home page is not displayed",
                10
        );
    }

    @Test
    public void testTitlePresence() throws InterruptedException {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find 'onboarding_skip_button'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                20
        );
        Thread.sleep(5000);

        assertElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find title"
        );


    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "/n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private boolean assertElementHasText(WebElement element, String expectedText, String error_message) {
        if (element.getText().equals(expectedText)) {
            return true;
        } else {
            System.out.println(error_message);
            return false;
        }
    }


    protected void swipe(Point start, Point end, Duration duration) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        this.driver.perform(Arrays.asList(swipe));
    }

    protected void swipeElementToLeft(By by, long duration, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);

        Point location = element.getLocation();
        Dimension size = element.getSize();

        int left_x = location.getX();
        int right_x = left_x + size.getWidth();
        int upper_y = location.getY();
        int lower_y = upper_y + size.getHeight();
        int middle_y = upper_y + (size.getHeight() / 2);

        int start_x = right_x - 20;
        int end_x = left_x + 20;
        int start_y = middle_y;
        int end_y = middle_y;

        this.swipe(
                new Point(start_x, start_y),
                new Point(end_x, end_y),
                Duration.ofMillis(duration)
        );
    }


    protected void swipeLeftToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping left. \n" + error_message, 0);
                return;
            }
            swipeLeftQuick();
            already_swiped++;
        }
    }

    protected void swipeLeftIfElementIsValid(By by, String expectedResult, String error_message) {
        WebElement element = waitForElementPresent(by, "Cannot find expected title", 5);
        String actualTitleText = element.getText();

        if (expectedResult.equals(actualTitleText)) {
            swipeLeftQuick();
        } else {
            Assert.assertTrue("The screen title is incorrect, swipe is cancelled",expectedResult.equals(actualTitleText));
        }
    }

    protected void swipeLeft(int timeOfSwipe) {
        int startX = (int) (driver.manage().window().getSize().getWidth() * 0.8);
        int endX = (int) (driver.manage().window().getSize().getWidth() * 0.2);
        int startY = driver.manage().window().getSize().getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 0);
        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY)); //перемещение указателя в исходное положение
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        /* Далее имитация события касания на экране, перемещение указателя из исходного положения в конечное положение
         за 600 миллисекунд и выполнение события касания */
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(timeOfSwipe), PointerInput.Origin.viewport(), endX, startY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(scroll));
    }

    protected void swipeLeftQuick() {
        swipeLeft(200);
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private String waitForElementAndGetAttribute(By by, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        return element.getText();
    }
    private void assertElementPresent(By by, String error_message)
    {
        int sum_of_elements = getAmountOfElements(by);
        if (sum_of_elements == 0) {
            String error = "Expected element :" + by.toString() + " not found. ";
            throw new AssertionError(error +" " + error_message);
        }
    }
}
