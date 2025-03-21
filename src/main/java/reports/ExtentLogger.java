package reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import dataUtil.TestUtil;

public class ExtentLogger {

//	private ExtentLogger() {
//	};

	public static void pass(String steps, String message) {

		ExtentManager.getExtentTest().log(Status.PASS, steps + message);
	}

	public static void fail(String steps, String message) {

		ExtentManager.getExtentTest().log(Status.FAIL, steps + message);
	}

	public static void skip(String steps, String message) {

		ExtentManager.getExtentTest().log(Status.SKIP, steps + message);
	}

	public static void info(String steps) {

		ExtentManager.getExtentTest().log(Status.INFO, steps);
	}

	public static void pass(String steps, String message, boolean isScreenshotNeeded) throws Exception {

		if (coreUtil.ConfigFactory.getConfig().passedstepsscreenshots() && isScreenshotNeeded) {

			ExtentManager.getExtentTest().log(Status.PASS, steps + message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(TestUtil.getBase64Image()).build());
		}

		else {

			pass(steps, message);
		}
	}

	public static void fail(String steps, String message, boolean isScreenshotNeeded) throws Exception {

		if (coreUtil.ConfigFactory.getConfig().failedstepsscreenshots() && isScreenshotNeeded) {

			ExtentManager.getExtentTest().log(Status.FAIL, steps + message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(TestUtil.getBase64Image()).build());
		}

		else {

			fail(steps, message);
		}
	}

	public static void skip(String steps, String message, boolean isScreenshotNeeded) throws Exception {

		if (coreUtil.ConfigFactory.getConfig().skippedstepsscreenshots() && isScreenshotNeeded) {

			ExtentManager.getExtentTest().log(Status.SKIP, steps + message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(TestUtil.getBase64Image()).build());
		}

		else {

			skip(steps, message);
		}
	}

	public static void info(String steps, boolean isScreenshotNeeded) throws Exception {

		if (coreUtil.ConfigFactory.getConfig().infostepsscreenshots() && isScreenshotNeeded) {

			ExtentManager.getExtentTest().log(Status.INFO, steps,
					MediaEntityBuilder.createScreenCaptureFromBase64String(TestUtil.getBase64Image()).build());
		}

		else {

			info(steps);
		}
	}

}
