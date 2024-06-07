import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

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
        System.out.println("First test run");
    }
}
