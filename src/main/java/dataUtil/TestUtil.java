package dataUtil;

import java.time.Duration;

import baseFactory.TestBase;
import coreUtil.WaitUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enums.WaitStrategy;
import baseFactory.DriverFactory;

public class TestUtil extends TestBase {

	protected static final Duration DEFAULT_TIMEOUT_TO_WAIT = Duration.ofSeconds(5);
	protected static final Duration DEFAULT_TIMEOUT_TO_CLICK = Duration.ofSeconds(5);

//	public static String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
//
//		TakesScreenshot ss = (TakesScreenshot) driver;
//		File source = ss.getScreenshotAs(OutputType.FILE);
//		File file = new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");
//		FileUtils.copyFile(source, file);
//		return System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
//	}

	protected WebElement getWebElement(By by) {

		WebElement element = driver.findElement(by);

		return element;
	}

	protected void sendKeys(By by, String value, WaitStrategy waitStrategy) {

		WebElement element = WaitUtil.explicitWait(waitStrategy, by);

		element.sendKeys(value);

	}

	protected void click(By by, WaitStrategy waitStrategy) {

		WebElement element = WaitUtil.explicitWait(waitStrategy, by);

		element.click();

	}

	public static void click(WebElement ele, By by) {

		WebElement element = ele.findElement(by);

		element.click();
	}

	protected String getText(By by, WaitStrategy waitStrategy) {

		WebElement element = WaitUtil.explicitWait(waitStrategy, by);

		return element.getText();

	}

	public static String getText(WebElement ele, By by) {

		WebElement element = ele.findElement(by);

		return element.getText();
	}

	//Action methods

	public static Actions getActions(WebElement element) {

		return new Actions(unpackWebDriverFromSearchContext(element));
	}

	public static void doubleClickOn(WebElement element) {

		try {
			getActions(element).doubleClick(element).build().perform();
		}
		catch (WebDriverException e) {
			scrollIntoViewByCoordinate(element);
			getActions(element).doubleClick(element).build().perform();
		}
	}

	public static void clickAndHoldOn(WebElement element) {

		try {
			getActions(element).clickAndHold(element).build().perform();
		}
		catch (WebDriverException e) {
			scrollIntoViewByCoordinate(element);
			getActions(element).clickAndHold(element).build().perform();
		}
	}

	public static void actionsClick(WebElement element) {
		getActions(element).click().build().perform();
	}

	public void clickMouseRightButton(WebElement element) {
		getActions(element).contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
	}

	public static void moveTo(WebElement element) {
		scrollIntoViewByCoordinate(element);
		getActions(element).moveToElement(element).build().perform();
	}

	public static void actionClick(WebElement element) {
		scrollIntoViewByCoordinate(element);
		getActions(element).moveToElement(element).build().perform();
		clickJS(element);
	}

	public static void dragAndDrop(WebElement element, WebElement target) {

		try {
			scrollIntoViewByCoordinate(element);
			getActions(element).dragAndDrop(element, target).build().perform();
		}
		catch (WebDriverException e) {
			scrollIntoViewByCoordinate(element);
			getActions(element).clickAndHold(element).build().perform();
			WaitUtil.waitFor(1000);
			getActions(element).moveToElement(target).build().perform();
			WaitUtil.waitFor(1000);
			getActions(element).moveByOffset(-1, -1).build().perform();
			WaitUtil.waitFor(1000);
			getActions(element).build().perform();
		}
	}

	public static int getDistanceInXDirection(WebElement firstElement, WebElement secondElement) {
		return secondElement.getRect().getX() - firstElement.getRect().getX();
	}

	public static int getDistanceInYDirection(WebElement firstElement, WebElement secondElement) {
		return secondElement.getRect().getY() - firstElement.getRect().getY();
	}

	public static void dragAndDropBelowTarget(WebElement element, WebElement target) {
		getActions(element).dragAndDropBy(element, getDistanceInXDirection(element, target),
				getDistanceInYDirection(element, target) + target.getSize().getHeight() / 3).build().perform();
	}

	public static void dragAndDropBy(WebElement element, int xOffset, int yOffset) {
		getActions(element).clickAndHold(element).moveByOffset(xOffset, yOffset).release().build().perform();
	}

	public static boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		}
		catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public static boolean isElementClickable(WebElement element) {
		try {
			webDriverWait(element, DEFAULT_TIMEOUT_TO_WAIT).until(ExpectedConditions.elementToBeClickable(element));
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	public static boolean isElementEnabled(WebElement element) {
		try {
			return element.isEnabled();
		}
		catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public static void clickOn(WebElement element) {
		try {
			element.click();
		}
		catch (WebDriverException e) {
			scrollIntoViewByCoordinate(element);
			element.click();
		}
	}

	// Javascript methods

	private static JavascriptExecutor getJavascriptExecutor() {

		return (JavascriptExecutor) driver;
	}

	private static JavascriptExecutor getJavascriptExecutor(WebElement element) {

		return (JavascriptExecutor) unpackWebDriverFromSearchContext(element);
	}

	public static void clickJS(WebElement element) {
		getJavascriptExecutor(element).executeScript("arguments[0].click();", element);
	}

	public static void doubleClickJS(WebElement element) {
		getJavascriptExecutor(element).executeScript("arguments[0].doubleClick();", element);
	}

	public static void waitAndClick(WebElement element, Duration timeInSeconds) {
		webDriverWait(element, timeInSeconds).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public static void waitAndClick(WebElement element) {
		waitAndClick(element, DEFAULT_TIMEOUT_TO_CLICK);
	}

	public static void sendKeysTo(WebElement element, String keysToSend) {
		element.sendKeys(keysToSend);
	}

	public static void clear(WebElement element) {
		element.clear();
	}

	public static void clearAndSendKeysTo(WebElement element, String keysToSend) {
		clear(element);
		sendKeysTo(element, keysToSend);
	}

	public static String getAttribute(WebElement element, String attributeName) {
		return element.getDomAttribute(attributeName);
	}

	public static String getTextFrom(WebElement element) {

		if(isElementDisplayed(element)) {
			return element.getText();
		}
		else {
			return null;
		}
	}


	public static WebElement scrollIntoViewByCoordinate(WebElement e) {

		getJavascriptExecutor(e).executeScript("arguments[0].scrollIntoView(true); scrollBy(0,-100);", e);

		return e;
	}

	public static WebElement scrollIntoViewReverse(WebElement e) {

		getJavascriptExecutor(e).executeScript("arguments[0].scrollIntoView(false)", e);

		return e;
	}

	public static WebElement scrollIntoElement(WebElement e) {

		getJavascriptExecutor(e).executeScript("arguments[0].scrollIntoView(true)", e);

		return e;
	}

	public static WebElement scrollIntoElement(By by) {

		WebElement e = driver.findElement(by);

		getJavascriptExecutor(e).executeScript("arguments[0].scrollIntoView(true)", e);

		return e;
	}

	public static WebElement scrollIntoElement(WebElement ele, By by) {

		WebElement e = ele.findElement(by);

		getJavascriptExecutor(e).executeScript("arguments[0].scrollIntoView(true)", e);

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


	public static void waitForPageLoad(long timeoutInSeconds) {

	}

}
