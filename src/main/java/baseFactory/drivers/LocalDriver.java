package baseFactory.drivers;

import baseFactory.IDriverInterface;
import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class LocalDriver implements IDriverInterface {

    private final ThreadLocal<SelfHealingDriver> driver = new ThreadLocal<>();

    @Override
    public void setDriver(Object delegate) {

        driver.set(SelfHealingDriver.create((WebDriver) delegate));
    }

    @Override
    public SelfHealingDriver getDriver() {

        return driver.get();
    }

    @Override
    public void unload() {

        driver.remove();
    }

    @Override
    public Object useChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");

        return new ChromeDriver(options);
    }

    @Override
    public Object useFirefox() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();

        return new FirefoxDriver(options);
    }

    @Override
    public Object useEdge() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();

        return new EdgeDriver(options);
    }
}
