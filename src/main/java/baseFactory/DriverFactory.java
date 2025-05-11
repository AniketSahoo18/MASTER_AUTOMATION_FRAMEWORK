package baseFactory;

import baseFactory.drivers.LocalDriver;
import baseFactory.drivers.ProxyDriver;
import baseFactory.drivers.RemoteDriver;
import com.epam.healenium.SelfHealingDriver;
import enums.BrowserTypes;
import enums.DriverTypes;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public final class DriverFactory {

	private IDriverInterface context;

	public DriverFactory(DriverTypes local) {
		switch (local) {
			case LOCAL -> this.context = new LocalDriver();
			case PROXY -> this.context = new ProxyDriver();
			case REMOTE -> this.context = new RemoteDriver();
		}

	}

	public SelfHealingDriver getDriver(BrowserTypes browser) throws MalformedURLException {
		switch (browser) {
			case CHROME -> context.setDriver(context.useChrome());
			case FIREFOX -> context.setDriver(context.useFirefox());
			case EDGE -> context.setDriver(context.useEdge());
		}
		return context.getDriver();
	}

	public void unloadDriver() {
		context.unload();
	}
}
