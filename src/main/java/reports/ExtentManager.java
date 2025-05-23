package reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	private ExtentManager() {

	}

	private static ThreadLocal<ExtentTest> exTest = new ThreadLocal<>();

	static ExtentTest getExtentTest() {

		return exTest.get();
	}

	public static void setExtentTest(ExtentTest test) {

		exTest.set(test);
	}

	static void unload() {

		exTest.remove();
	}
}
