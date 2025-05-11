package coreUtil;

import baseFactory.DriverFactory;
import baseFactory.TestBase;

public class WindowUtil extends TestBase {

    private WindowUtil(){}

    public static String getCurrentWindow() {

        return driver.getWindowHandle();
    }

    public static void switchToWindow(String windowName) {

        for(String windowHandle : driver.getWindowHandles()) {
            if(!getCurrentWindow().contentEquals(windowHandle)) {
                driver.switchTo().window(windowName);
                break;
            }
        }
    }

}
