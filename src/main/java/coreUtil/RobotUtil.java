package coreUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RobotUtil {

    private RobotUtil() {
    }

    public static void addNewTab() {

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_T);
        }
        catch (AWTException e) {

            throw new RuntimeException("AWTException occured while executing", e);
        }
    }

    public static void addNewBrowserWindow() {

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_N);
        }
        catch (AWTException e) {

            throw new RuntimeException("AWTException occured while executing", e);
        }
    }

    public static void addNewInCogBrowserWindow() {

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_N);
        }
        catch (AWTException e) {

            throw new RuntimeException("AWTException occured while executing", e);
        }
    }

}
