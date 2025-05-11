package websites;

import baseFactory.DriverFactory;
import baseFactory.TestBase;
import coreUtil.WaitUtil;
import enums.PageUrls;
import webpages.HomePage;
import webpages.LoginPage;

public class Authorization extends TestBase {
    public static LoginPage openLetsShopWebsite() {
        driver.get(PageUrls.LETS_SHOP_URL.toString());
        WaitUtil.implicitWait();
        return new LoginPage();
    }
    public static HomePage loginToLetsShopWebsite() {

        openLetsShopWebsite().loginApplication(coreUtil.ConfigFactory.getConfig().userName(),
                coreUtil.ConfigFactory.getConfig().password(), true, "Login to Application");
        return new HomePage();
    }
}
