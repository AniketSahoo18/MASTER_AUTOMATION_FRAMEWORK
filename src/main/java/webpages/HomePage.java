package webpages;

import java.time.Duration;

import coreUtil.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import coreUtil.ValidationUtil.Validations;
import enums.WaitStrategy;
import baseFactory.DriverFactory;
import dataUtil.TestUtil;

public class HomePage extends TestUtil {

	private static final By PRODUCTS_TXT = By.cssSelector(".mb-3");
	private static final By PRODUCT_TXT = By.cssSelector(".card-body b");
	private static final By CART_LINK = By.cssSelector("[routerlink*='cart']");
	private static final By PRODUCT_LINK = By.cssSelector(".card-body button:last-of-type");
	private static final By POPUP_LINK = By.cssSelector("#toast-container");
	private static final By ANIMATION_LINK = By.cssSelector(".ng-animating");
	private static final By MYCART = By.xpath("//h1[contains(text(), 'My Cart')]");

	public CartPage addProductToCart(String productName, boolean check, String steps) {

		try {

			WebElement prod = driver.findElements(PRODUCTS_TXT).stream()
					.filter(product -> getText(product, PRODUCT_TXT).equals(productName)).findFirst().orElse(null);
			
			WaitUtil.wait(WaitStrategy.INVISIBLE, POPUP_LINK);
			scrollIntoElement(prod, PRODUCT_LINK);
			WaitUtil.waitFor(3000);
			
			click(prod, PRODUCT_LINK, Duration.ofSeconds(10));

			WaitUtil.wait(WaitStrategy.VISIBLE, POPUP_LINK);
			WaitUtil.wait(WaitStrategy.INVISIBLE, ANIMATION_LINK);

			// Click Cart
			
			scrollIntoElement(CART_LINK);
			WaitUtil.waitFor(3000);

			click(CART_LINK, WaitStrategy.CLICKABLE);

			WaitUtil.waitFor(3000);

			if (check) {Validations.validationCheck("hard", "boolean", getWebElement(MYCART), "", "", steps, true);}

			else {Validations.stepInfo(steps);}

		}

		catch (Exception e) {

			Validations.validation(false, "Failed Step : " + steps, "</br>Fail Cause : " + e.getMessage());

		}

		return new CartPage();
	}
}
