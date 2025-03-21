package baseUtil;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	public static WebDriver createBrowserInstance(String browser, int wait) {

		try {
			if (browser.equalsIgnoreCase("chrome")) {

				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				options.addArguments("disable-popup-blocking");
				DriverManager.setDriver(new ChromeDriver(options));
			}

			else if (browser.equalsIgnoreCase("edge")) {

				WebDriverManager.edgedriver().setup();
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--incognito");
				options.addArguments("disable-popup-blocking");
				DriverManager.setDriver(new EdgeDriver(options));
			}

			else if (browser.equalsIgnoreCase("firefox")) {

				// NEED TO IMPLEMENT
			}

			else {

				throw new IllegalArgumentException("Unsupported Browser !!! : " + browser);

			}
			
			DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
			DriverManager.getDriver().manage().window().maximize();
		}

		catch (Exception e) {

			e.printStackTrace();
		}

		return DriverManager.getDriver();
	}
}
