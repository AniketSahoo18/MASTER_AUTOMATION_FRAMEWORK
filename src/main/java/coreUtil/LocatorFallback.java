package coreUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class LocatorFallback {

    public static WebElement findElementSmart(WebDriver driver, By[] locators, By relativeRefLocator,
                                              By fallbackTagName, String relativePosition) {

        for(By locator : locators) {
            try {
                System.out.println("Trying locator : " + locator.toString());
                WebElement element = driver.findElement(locator);
                System.out.println("Element Found using : " + locator.toString());
            }
            catch (NoSuchElementException e) {
                System.out.println("Element Not Found with Locator : " + locator.toString());
            }
        }

        try {
            System.out.println("Trying relative locator strategy... using " + relativePosition + " of "
                    + relativeRefLocator.toString());
            WebElement referenceElement = driver.findElement(relativeRefLocator);

            WebElement relativeElement = switch (relativePosition.toLowerCase()) {
                case "below" -> driver.findElement(with(fallbackTagName).below(referenceElement));
                case "above" -> driver.findElement(with(fallbackTagName).above(referenceElement));
                case "toleftof" -> driver.findElement(with(fallbackTagName).toLeftOf(referenceElement));
                case "torightof" -> driver.findElement(with(fallbackTagName).toRightOf(referenceElement));
                case "near" -> driver.findElement(with(fallbackTagName).near(referenceElement));
                default -> throw new IllegalArgumentException("Invalid relative position : " + relativePosition);
            };

            System.out.println("Element is Found using relative locator : " + relativePosition);
            return relativeElement;
        }
        catch (NoSuchElementException e) {
            System.out.println("Element is Not Found with relative locator : " + relativePosition);
            throw new NoSuchElementException("Element Not Found with any locators...");
        }
    }
}
