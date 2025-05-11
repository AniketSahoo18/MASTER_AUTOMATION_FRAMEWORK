package baseFactory;

import java.time.Duration;
import java.util.Objects;

import com.epam.healenium.SelfHealingDriver;
import enums.BrowserTypes;
import enums.DriverTypes;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

	static protected SelfHealingDriver driver;

	DriverFactory df;

	@BeforeMethod(alwaysRun = true)
	protected void setUp() {

		try {

			if (Objects.isNull(driver)) {

				df = new DriverFactory(DriverTypes.LOCAL);

				driver = df.getDriver(BrowserTypes.CHROME);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(coreUtil.ConfigFactory.getConfig().driverStartupWait()));
				driver.manage().window().maximize();
			}
		}
		catch (Exception e) {System.out.println("Fail Cause: " + e.getMessage());}
	}

	@AfterMethod(alwaysRun = true)

	protected void tearDown() {

		if (Objects.nonNull(driver)) {
			driver.quit();
			df.unloadDriver();
		}
	}

}
