package baseFactory.drivers;

import baseFactory.IDriverInterface;
import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class RemoteDriver implements IDriverInterface {

    private final ThreadLocal<SelfHealingDriver> driver = new ThreadLocal<>();

    @Override
    public void setDriver(Object capabilities) throws MalformedURLException {
        RemoteWebDriver delegate = new RemoteWebDriver(
                URI.create("http://" + SELENOID + ":4444/wd/hub").toURL(),
                (Capabilities) capabilities
        );

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
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("enableVNC", true);

        return capabilities;
    }

    @Override
    public Object useFirefox() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "firefox");
        capabilities.setCapability("enableVNC", true);

        return capabilities;
    }

    @Override
    public Object useEdge() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "edge");
        capabilities.setCapability("enableVNC", true);

        return capabilities;
    }

}
