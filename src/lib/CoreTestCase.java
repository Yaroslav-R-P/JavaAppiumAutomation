package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {
    protected Platform Platform;
    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.Platform = new Platform();
        driver = this.Platform.getDriver();
//        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
//        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(long seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }
}
