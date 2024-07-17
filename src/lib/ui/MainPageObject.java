package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }
    public WebElement waitForElementPresent(String locator, String error_message, long timeoutSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

//    public WebElement waitForElementPresentV1(By by, String error_message) {
//        return waitForElementPresent(by, error_message, 5);
//    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "/n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public boolean assertElementHasText(WebElement element, String expectedText, String error_message) {
        if (element.getText().equals(expectedText)) {
            return true;
        } else {
            System.out.println(error_message);
            return false;
        }
    }


    public void swipe(Point start, Point end, Duration duration) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        this.driver.perform(Arrays.asList(swipe));
    }

    public void swipeElementToLeft(String locator, long duration, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message, 10);

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
    //метод swipeUpTitleElementAppear приведен для примера, не используется в текущем проекте
    public void swipeUpTitleElementAppear(String locator, String error_message, int max_swipes) {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while(!this.isElementLocatedOnTheScreen(locator)) // то есть пока у нас элемент не находится на экране
            if(already_swiped > max_swipes) {
                Assert.assertTrue(error_message,this.isElementLocatedOnTheScreen(locator));
            }
        //<Метод swipeUPQuick>
        already_swiped++;



        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swiping left. \n" + error_message, 0);
                return;
            }
            swipeLeftQuick();
            already_swiped++;
        }
    }
    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(locator,
                "Cannot find element by locator" + locator,
                5).getLocation().getY();
        /*
        Код выше - таким образом мы находим элемент и получаем его расположение по оси Y (по вертикали)
         */
        int screen_size_by_y = driver.manage().window().getSize().getHeight(); // получаем высоту всего экрана
        return element_location_by_y < screen_size_by_y;
        /*
        Таким образом, пока element_location_by_y будет больше, чем размер экрана по высоте, мы будем возвращать false,
        Но, как только мы доскроллим до element_location_by_y вернётся true и мы будем знать, что элемент находится и
        отображается на экране.
         */
    }

    public void swipeLeftToFindElement(String locator, String error_message, int max_swipes) {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swiping left. \n" + error_message, 0);
                return;
            }
            swipeLeftQuick();
            already_swiped++;
        }
    }

    public void swipeLeftIfElementIsValid(String locator, String expectedResult, String error_message) {
        WebElement element = waitForElementPresent(locator, "Cannot find expected title", 5);
        String actualTitleText = element.getText();

        if (expectedResult.equals(actualTitleText)) {
            swipeLeftQuick();
        } else {
            Assert.assertTrue("The screen title is incorrect, swipe is cancelled",expectedResult.equals(actualTitleText));
        }
    }

    public void swipeLeft(int timeOfSwipe) {
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

    public void swipeLeftQuick() {
        swipeLeft(200);
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public String waitForElementAndGetAttribute(String locator, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSeconds);
        return element.getText();
    }
    public void assertElementPresent(String locator, String error_message) {
        By by = this.getLocatorByString(locator);
        int sum_of_elements = getAmountOfElements(locator);
        if (sum_of_elements == 0) {
            String error = "Expected element :" + by.toString() + " not found. ";
            throw new AssertionError(error +" " + error_message);
        }
    }

    public void assertElementNotPresent(String locator, String error_message) {
        By by = this.getLocatorByString(locator);
        int sum_of_elements = getAmountOfElements(locator);
        if (sum_of_elements > 0){
            String default_message = "An element '" + by + "' supposed to be not present";
            throw  new AssertionError(default_message +" " + error_message);
        }
    }

    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if(by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator " + locator_with_type);
        }
    }
}
