import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appium:deviceName","AndroidTestDevice");
        capabilities.setCapability("appium:platformVersion","8.0");
        capabilities.setCapability("appium:automationName","UiAutomator2");
        capabilities.setCapability("appium:appPackage","org.wikipedia");
        capabilities.setCapability("appium:appActivity",".main.MainActivity");
        capabilities.setCapability("appium:app","/Users/yar/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

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

        Assert.assertEquals("We see unexpected title!","Java (programming language)",article_title);
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
    public void CancelSearch() throws InterruptedException {

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

    private WebElement waitForElementPresent(By by, String error_message, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver,timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message,  5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by, error_message,  timeoutSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by, error_message,  5);
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
        WebElement element = waitForElementPresent(by,error_message,timeoutInSeconds);
        element.clear();
        return element;
    }

    private boolean  assertElementHasText(WebElement element, String expectedText, String error_message) {
        if (element.getText().equals(expectedText)) {
            return true;
        } else {
            System.out.println(error_message);
            return false;
        }
    }
}
