package coreUtil;

import baseFactory.DriverFactory;
import baseFactory.TestBase;
import constants.FrameworkConstants;
import enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtil extends TestBase {

	private WaitUtil() {
	}

	// Implicit Wait........................

	public static void implicitWait() {

		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(FrameworkConstants.getImplicitWait()));
	}

	// Explicit Waits.........................

	public static WebElement explicitWait(WaitStrategy waitStrategy, By by) {

		WebElement waitElement = null;

		if (waitStrategy == WaitStrategy.CLICKABLE) {

			waitElement = new WebDriverWait(driver,
					Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
					.until(ExpectedConditions.elementToBeClickable(by));
		}

		else if (waitStrategy == WaitStrategy.PRESENCE) {

			waitElement = new WebDriverWait(driver,
					Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
					.until(ExpectedConditions.presenceOfElementLocated(by));
		}

		else if (waitStrategy == WaitStrategy.VISIBLE) {

			waitElement = new WebDriverWait(driver,
					Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
					.until(ExpectedConditions.visibilityOfElementLocated(by));

		}

		else if (waitStrategy == WaitStrategy.INVISIBLE) {

			new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
					.until(ExpectedConditions.invisibilityOfElementLocated(by));

		}

		else if (waitStrategy == WaitStrategy.NONE) {

			waitElement = driver.findElement(by);
		}
		return waitElement;
	}

	public static void wait(WaitStrategy waitStrategy, By by) {

		explicitWait(waitStrategy, by);
	}

	public static void waitFor(long defaultPollingIntervalMs) {

		try {
			Thread.sleep(defaultPollingIntervalMs);
		}
		catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("This thread was interrupted", e);
		}
	}

}
