package webpages;

import org.openqa.selenium.By;
import org.testng.Assert;

import coreUtil.ValidationUtil.Validations;
import enums.WaitStrategy;
import baseFactory.DriverFactory;
import dataUtil.TestUtil;

public class CartPage extends TestUtil {

	private static final By CARTPRODUCTS_TXT = By.cssSelector(".cartSection h3");
	private static final By CHECKOUT_BTN = By.cssSelector(".totalRow button");
	private static final By PAYMENT_METHOD = By.xpath("//div[contains(text(), 'Payment Method')]");

	public CheckoutPage placeOrder(String productName, boolean check, String steps) {

		try {

			// Checking product is added in Cart

			Boolean match = driver.findElements(CARTPRODUCTS_TXT).stream()
					.anyMatch(cartProduct -> cartProduct.getText().equals(productName));

			Assert.assertTrue(match);

			// Checkout

			click(CHECKOUT_BTN, WaitStrategy.CLICKABLE);

			if (check) {

				Validations.validationCheck("hard", "boolean", getWebElement(PAYMENT_METHOD), "", "", steps, true);

			} else {

				Validations.stepInfo(steps);

			}

		}

		catch (Exception e) {

			Validations.validation(false, "Failed Step : " + steps, "</br>Fail Cause : " + e.getMessage());

		}

		return new CheckoutPage();
	}
}
