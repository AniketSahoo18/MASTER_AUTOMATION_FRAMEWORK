package baseFactory.drivers;

import baseFactory.IDriverInterface;
import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class ProxyDriver implements IDriverInterface {

    private final ThreadLocal<SelfHealingDriver> driver = new ThreadLocal<>();

    @Override
    public void setDriver(Object options) throws MalformedURLException {
        RemoteWebDriver delegate = new RemoteWebDriver(new URL("http://" + LOCALHOST + ":8085"), (Capabilities) options);

        driver.set(SelfHealingDriver.create(delegate));
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
        ChromeOptions options = new ChromeOptions();
        return options;
    }

    @Override
    public Object useFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        return options;
    }


    @Override
    public Object useEdge() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return options;
    }
}
