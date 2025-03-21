package webpages;

import org.openqa.selenium.By;
import coreUtil.ValidationUtil.Validations;
import enums.WaitStrategy;
import dataUtil.TestUtil;

public class ConfirmationPage extends TestUtil {

	private static final By CONFIRM_MSSG = By.cssSelector(".hero-primary");

	String confirmationMessage;

	public void getConfirmationMssg(boolean check, String steps) {

		try {

			confirmationMessage = getText(CONFIRM_MSSG, WaitStrategy.VISIBLE);

			if (check) {

				Validations.validationCheck("soft", "texttotext", getWebElement(CONFIRM_MSSG),
						"THANKYOU FOR THE ORDER.", confirmationMessage, steps, true);

			} else {

				Validations.stepInfo(steps);

			}

		}

		catch (Exception e) {

			Validations.validation(false, "Failed Step : " + steps, "</br>Fail Cause : " + e.getMessage());

		}

	}
}
