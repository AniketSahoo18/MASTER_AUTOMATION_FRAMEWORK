package coreUtil.ValidationUtil;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import reports.ExtentLogger;

public class Validations {

	public static void validationCheck(String softorHardAssert, String validationType, WebElement element,
			String expectedResult, String actualResult, String steps, boolean trueFalse) {

		switch (validationType.toLowerCase()) {

		case "text":

			String actualText = element.getText().trim();

			if (softorHardAssert.equalsIgnoreCase("hard")) {

				validation(actualText.equals(expectedResult), steps,
						"<br/>Expected Value : " + expectedResult + "<br/>Actual Value : " + actualText);
			}

			else if (softorHardAssert.equalsIgnoreCase("soft")) {

				validationSoftAssert(actualText.equals(expectedResult), steps,
						"<br/>Expected Value : " + expectedResult + "<br/>Actual Value : " + actualText);
			}

			else {

				Assert.assertTrue(false, "NO Condition is Matching in Validation Check!!");
			}

			break;

		case "link":

			if (softorHardAssert.equalsIgnoreCase("hard")) {

				validation(element.isDisplayed(), steps, "");
			}

			else if (softorHardAssert.equalsIgnoreCase("soft")) {

				validationSoftAssert(element.isDisplayed(), steps, "");
			}

			else {

				Assert.assertTrue(false, "NO Condition is Matching in Validation Check!!");
			}

			break;

		case "boolean":

			if (softorHardAssert.equalsIgnoreCase("hard")) {

				validation(trueFalse, steps, "");
			}

			else if (softorHardAssert.equalsIgnoreCase("soft")) {

				validationSoftAssert(trueFalse, steps, "");
			}

			else {

				Assert.assertTrue(false, "NO Condition is Matching in Validation Check!!");
			}

			break;

		case "texttotext":

			if (softorHardAssert.equalsIgnoreCase("hard")) {

				validation(expectedResult.equalsIgnoreCase(actualResult), steps,
						"<br/>Expected Value : " + expectedResult + "<br/>Actual Value : " + actualResult);
			}

			else if (softorHardAssert.equalsIgnoreCase("soft")) {

				validationSoftAssert(expectedResult.equalsIgnoreCase(actualResult), steps,
						"<br/>Expected Value : " + expectedResult + "<br/>Actual Value : " + actualResult);
			}

			else {

				Assert.assertTrue(false, "NO Condition is Matching in Validation Check!!");
			}
			
			break;

		default:

			System.out.println("Invalid Validation Type" + validationType);

			break;
		}

	}

	public static void validationSoftAssert(boolean validationStatus, String steps, String message) {

		try {

			SoftAssert softAssert = new SoftAssert();

			if (validationStatus) {

				softAssert.assertEquals(validationStatus, true, steps + message);

				// ScreenshotNeeded - true, Otherwise false
				if (coreUtil.ConfigFactory.getConfig().executionMode().equalsIgnoreCase("GUI")) {

					ExtentLogger.pass(steps, message, true);

				} else {

					ExtentLogger.pass(steps, message);
				}
			}

			else {

				// ScreenshotNeeded - true, Otherwise false
				if (coreUtil.ConfigFactory.getConfig().executionMode().equalsIgnoreCase("GUI")) {

					ExtentLogger.fail(steps, message, true);

				} else {

					ExtentLogger.fail(steps, message);
				}

				softAssert.assertEquals(validationStatus, true, steps + message);
			}

		}

		catch (Exception e) {

			Assert.assertTrue(false);
		}
	}

	// For API...............

	public static void validationCheck(String softorHardAssert, String expectedResult, String actualResult,
			String steps) {

		if (softorHardAssert.equalsIgnoreCase("hard")) {

			validation(expectedResult.equalsIgnoreCase(actualResult), steps,
					"<br/>Expected Value : " + expectedResult + "<br/>Actual Value : " + actualResult);
		}

		else if (softorHardAssert.equalsIgnoreCase("soft")) {

			validationSoftAssert(expectedResult.equalsIgnoreCase(actualResult), steps,
					"<br/>Expected Value : " + expectedResult + "<br/>Actual Value : " + actualResult);
		}

		else {

			Assert.assertTrue(false, "NO Condition is Matching in Validation Check!!");
		}
	}

	public static void validation(boolean validationStatus, String steps, String message) {

		try {

			if (validationStatus) {

				Assert.assertEquals(validationStatus, true, steps);

				// ScreenshotNeeded - true, Otherwise false
				if (coreUtil.ConfigFactory.getConfig().executionMode().equalsIgnoreCase("GUI")) {

					ExtentLogger.pass(steps, message, true);
				}

				else {

					ExtentLogger.pass(steps, message);
				}

			}

			else {

				// ScreenshotNeeded - true, Otherwise false
				if (coreUtil.ConfigFactory.getConfig().executionMode().equalsIgnoreCase("GUI")) {

					ExtentLogger.fail(steps, message, true);

				} else {

					ExtentLogger.fail(steps, message);
				}

				Assert.assertEquals(validationStatus, true, steps);

			}

		}

		catch (Exception e) {

			Assert.assertTrue(false);
		}
	}

	public static void stepInfo(String steps) {

		try {

			// ScreenshotNeeded - true, Otherwise false
			if (coreUtil.ConfigFactory.getConfig().executionMode().equalsIgnoreCase("GUI")) {

				ExtentLogger.info(steps, true);

			} else {

				ExtentLogger.info(steps);
			}
		}

		catch (Exception e) {

			Assert.assertTrue(false);
		}
	}

}
