package webpages;

import org.openqa.selenium.By;
import coreUtil.DecodeUtil;
import coreUtil.ValidationUtil.Validations;
import enums.WaitStrategy;
import dataUtil.TestUtil;

public class LoginPage extends TestUtil {

	private static final By EMAIL_TEXT_BOX = By.id("userEmail");
	private static final By PASSWORD_TEXT_BOX = By.id("userPassword");
	private static final By LOGIN_BUTTON = By.id("login");
	private static final By HOME = By.xpath("//i[@class='fa fa-home']");

	public HomePage loginApplication(String email, String password, boolean check, String steps) {

		try {

			sendKeys(EMAIL_TEXT_BOX, email, WaitStrategy.PRESENCE);
			sendKeys(PASSWORD_TEXT_BOX, DecodeUtil.getDecodedString(password), WaitStrategy.PRESENCE);
			click(LOGIN_BUTTON, WaitStrategy.CLICKABLE);

			if (check) {

				Validations.validationCheck("hard", "boolean", getWebElement(HOME), "", "", steps, true);

			} else {

				Validations.stepInfo(steps);

			}

		}

		catch (Exception e) {

			Validations.validation(false, "Failed Step : " + steps, "</br>Fail Cause : " + e.getMessage());

		}
		return new HomePage();
	}

}
