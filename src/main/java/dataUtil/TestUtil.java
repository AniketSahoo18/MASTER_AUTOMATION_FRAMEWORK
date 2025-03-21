package dataUtil;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enums.WaitStrategy;
import baseUtil.DriverManager;
import baseUtil.WaitFactory;

public class TestUtil {

	public static void getUrl(String url, WebDriver driver) {

		try {

			driver.get(url);

		}

		catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

//	public static String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
//
//		TakesScreenshot ss = (TakesScreenshot) driver;
//		File source = ss.getScreenshotAs(OutputType.FILE);
//		File file = new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");
//		FileUtils.copyFile(source, file);
//		return System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
//	}

	protected WebElement getWebElement(By by) {

		WebElement element = DriverManager.getDriver().findElement(by);

		return element;
	}

	protected void sendKeys(By by, String value, WaitStrategy waitStrategy) {

		WebElement element = WaitFactory.explicitWait(waitStrategy, by);

		element.sendKeys(value);

	}

	protected void click(By by, WaitStrategy waitStrategy) {

		WebElement element = WaitFactory.explicitWait(waitStrategy, by);

		element.click();

	}

	public static void click(WebElement ele, By by) {

		WebElement element = ele.findElement(by);

		element.click();
	}

	protected String getText(By by, WaitStrategy waitStrategy) {

		WebElement element = WaitFactory.explicitWait(waitStrategy, by);

		return element.getText();

	}

	public static String getText(WebElement ele, By by) {

		WebElement element = ele.findElement(by);

		return element.getText();
	}

	public static String getBase64Image() {

		return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
	}

	// Javascript methods

	private static JavascriptExecutor getJavascriptExecutor() {

		return (JavascriptExecutor) DriverManager.getDriver();
	}

	public static WebElement scrollIntoViewByCoordinate(WebElement e) {

		getJavascriptExecutor().executeScript("arguments[0].scrollIntoView(true); scrollBy(0,-100);", e);

		return e;
	}

	public static WebElement scrollIntoViewReverse(WebElement e) {

		getJavascriptExecutor().executeScript("arguments[0].scrollIntoView(false)", e);

		return e;
	}

	public static WebElement scrollIntoElement(WebElement e) {

		getJavascriptExecutor().executeScript("arguments[0].scrollIntoView(true)", e);

		return e;
	}

	public static WebElement scrollIntoElement(By by) {

		WebElement e = DriverManager.getDriver().findElement(by);

		getJavascriptExecutor().executeScript("arguments[0].scrollIntoView(true)", e);

		return e;
	}

	public static WebElement scrollIntoElement(WebElement ele, By by) {

		WebElement e = ele.findElement(by);

		getJavascriptExecutor().executeScript("arguments[0].scrollIntoView(true)", e);

		return e;
	}

	public static void scrollIntoPageHeight() {

		getJavascriptExecutor().executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public static void click(WebElement ele, By by, Duration timeInSeconds) {

		WebElement element = ele.findElement(by);

		webDriverWait(element, timeInSeconds).until(ExpectedConditions.elementToBeClickable(element));

		element.click();
	}

	public static void wait(WaitStrategy waitStrategy, By by) {

		WaitFactory.explicitWait(waitStrategy, by);
	}

	public static void wait(int milliseconds) {

		WaitFactory.wait(milliseconds);
	}

	public static WebDriverWait webDriverWait(WebElement ele, Duration timeInSeconds) {

		WebDriverWait waiter = new WebDriverWait(unpackWebDriverFromSearchContext(ele), timeInSeconds);
		waiter.ignoring(StaleElementReferenceException.class);
		return waiter;
	}

	public static WebDriver unpackWebDriverFromSearchContext(SearchContext searchContext) {

		if (searchContext instanceof WebDriver) {

			return (WebDriver) searchContext;
		}

		if (searchContext instanceof WrapsDriver) {

			return unpackWebDriverFromSearchContext(((WrapsDriver) searchContext).getWrappedDriver());
		}

		if (searchContext instanceof WrapsElement) {

			return unpackWebDriverFromSearchContext(((WrapsElement) searchContext).getWrappedElement());
		}

		return null;
	}

}
