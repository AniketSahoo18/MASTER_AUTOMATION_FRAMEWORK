package webpages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import coreUtil.ValidationUtil.Validations;
import enums.WaitStrategy;
import baseUtil.DriverManager;
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

			WebElement prod = DriverManager.getDriver().findElements(PRODUCTS_TXT).stream()
					.filter(product -> getText(product, PRODUCT_TXT).equals(productName)).findFirst().orElse(null);
			
			wait(WaitStrategy.INVISIBLE, POPUP_LINK);
			
			scrollIntoElement(prod, PRODUCT_LINK);
			
			wait(3000);
			
			click(prod, PRODUCT_LINK, Duration.ofSeconds(10));

			wait(WaitStrategy.VISIBLE, POPUP_LINK);
			wait(WaitStrategy.INVISIBLE, ANIMATION_LINK);

			// Click Cart
			
			scrollIntoElement(CART_LINK);
			
			wait(3000);

			click(CART_LINK, WaitStrategy.CLICKABLE);
			
			wait(3000);

			if (check) {

				Validations.validationCheck("hard", "boolean", getWebElement(MYCART), "", "", steps, true);

			} else {

				Validations.stepInfo(steps);

			}

		}

		catch (Exception e) {

			Validations.validation(false, "Failed Step : " + steps, "</br>Fail Cause : " + e.getMessage());

		}

		return new CartPage();
	}
}
