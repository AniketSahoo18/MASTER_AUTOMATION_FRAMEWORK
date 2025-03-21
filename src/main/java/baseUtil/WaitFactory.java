package baseUtil;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import constants.FrameworkConstants;
import enums.WaitStrategy;

public final class WaitFactory {

	private WaitFactory() {
	}

	// Implicit Wait........................

	public static void implicitWait() {

		DriverManager.getDriver().manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(FrameworkConstants.getImplicitWait()));
	}

	// Explicit Waits.........................

	public static WebElement explicitWait(WaitStrategy waitStrategy, By by) {

		WebElement waitElement = null;

		if (waitStrategy == WaitStrategy.CLICKABLE) {

			waitElement = new WebDriverWait(DriverManager.getDriver(),
					Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
					.until(ExpectedConditions.elementToBeClickable(by));
		}

		else if (waitStrategy == WaitStrategy.PRESENCE) {

			waitElement = new WebDriverWait(DriverManager.getDriver(),
					Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
					.until(ExpectedConditions.presenceOfElementLocated(by));
		}

		else if (waitStrategy == WaitStrategy.VISIBLE) {

			waitElement = new WebDriverWait(DriverManager.getDriver(),
					Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
					.until(ExpectedConditions.visibilityOfElementLocated(by));

		}

		else if (waitStrategy == WaitStrategy.INVISIBLE) {

			new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
					.until(ExpectedConditions.invisibilityOfElementLocated(by));

		}

		else if (waitStrategy == WaitStrategy.NONE) {

			waitElement = DriverManager.getDriver().findElement(by);
		}
		return waitElement;
	}

	// Try to use rarely
	public static void wait(int milliseconds) {

		try {

			Thread.sleep(milliseconds);
		}

		catch (InterruptedException e) {

			Thread.currentThread().interrupt();
		}
	}

}
