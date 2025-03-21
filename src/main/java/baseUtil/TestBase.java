package baseUtil;

import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import dataUtil.TestUtil;

public class TestBase {

	public WebDriver driver;

	@BeforeMethod(alwaysRun = true)

	protected void setUp() {

		try {

			if (Objects.isNull(DriverManager.getDriver())) {

				driver = BrowserFactory.createBrowserInstance(coreUtil.ConfigFactory.getConfig().browser(),
						coreUtil.ConfigFactory.getConfig().driverStartupWait());

			}

			TestUtil.getUrl(coreUtil.ConfigFactory.getConfig().url(), driver);
			WaitFactory.implicitWait();

		}

		catch (Exception e) {

			System.out.println("Fail Cause: " + e.getMessage());

		}
	}

	@AfterMethod(alwaysRun = true)

	protected void tearDown() {

		if (Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}

}
