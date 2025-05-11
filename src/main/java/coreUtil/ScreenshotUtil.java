package coreUtil;

import baseFactory.DriverFactory;
import baseFactory.TestBase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotUtil extends TestBase {

    private ScreenshotUtil(){}

    public static String getBase64Image() {

        return ((TakesScreenshot) driver.getDelegate()).getScreenshotAs(OutputType.BASE64);
    }
}
