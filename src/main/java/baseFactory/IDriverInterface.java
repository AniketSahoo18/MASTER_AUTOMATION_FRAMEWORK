package baseFactory;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public interface IDriverInterface {

    static final String LOCALHOST = "localhost";
    static final String SELENOID = "10.6.223.91";

    void setDriver(Object delegate) throws MalformedURLException;

    SelfHealingDriver getDriver();

    void unload();

    Object useChrome();

    Object useFirefox();

    Object useEdge();

}
