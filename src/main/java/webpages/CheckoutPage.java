package webpages;

import org.openqa.selenium.By;

import coreUtil.ValidationUtil.Validations;
import enums.WaitStrategy;
import dataUtil.TestUtil;

public class CheckoutPage extends TestUtil {

	private static final By COUNTRY_TXT = By.cssSelector("[placeholder='Select Country']");
	private static final By SEARCH_COUNTRY = By.cssSelector(".ta-item:nth-of-type(2)");
	private static final By PLACE_ORDER_BTN = By.cssSelector(".action__submit");

	public ConfirmationPage submitOrder(boolean check, String steps) {

		try {

			sendKeys(COUNTRY_TXT, "India", WaitStrategy.PRESENCE);
			click(SEARCH_COUNTRY, WaitStrategy.CLICKABLE);
			click(PLACE_ORDER_BTN, WaitStrategy.CLICKABLE);

			Validations.stepInfo(steps);

		}

		catch (Exception e) {

			Validations.validation(false, "Failed Step : " + steps, "</br>Fail Cause : " + e.getMessage());

		}

		return new ConfirmationPage();
	}

}
