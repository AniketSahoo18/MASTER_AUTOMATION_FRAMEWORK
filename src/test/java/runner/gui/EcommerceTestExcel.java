package runner.gui;

import java.util.Map;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import baseUtil.TestBase;
import dataUtil.DataProvidersUI;
import webpages.CartPage;
import webpages.CheckoutPage;
import webpages.ConfirmationPage;
import webpages.HomePage;
import webpages.LoginPage;

@Listeners(listeners.TestListener.class)

public final class EcommerceTestExcel extends TestBase {

	HomePage homePage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	ConfirmationPage confPage;

	private EcommerceTestExcel() {
	}

	@Test(dataProvider = "testData_Fashion", dataProviderClass = DataProvidersUI.class, enabled = true, priority = 1)
	public void ecommerceFashion_Test(Map<String, String> mapData) {

		try {
			LoginPage loginPage = new LoginPage();
			homePage = loginPage.loginApplication(mapData.get("User Name"), mapData.get("Password"), true,
					"Login to Application");
			cartPage = homePage.addProductToCart(mapData.get("Product"), true, "Add Product to Cart");
			checkoutPage = cartPage.placeOrder(mapData.get("Product"), true, "Checkout and Place Order");
			confPage = checkoutPage.submitOrder(true, "Place Order");
			confPage.getConfirmationMssg(true, "Product Confirmation");

		}

		catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test(dataProvider = "testData_Electronics", dataProviderClass = DataProvidersUI.class, enabled = true, priority = 2)
	public void ecommerceElectronics_Test(Map<String, String> mapData) {

		try {

			LoginPage loginPage = new LoginPage();
			homePage = loginPage.loginApplication(mapData.get("User Name"), mapData.get("Password"), true,
					"Login to Application");
			cartPage = homePage.addProductToCart(mapData.get("Product"), true, "Add Product to Cart");
			checkoutPage = cartPage.placeOrder(mapData.get("Product"), true, "Place the Order");
			confPage = checkoutPage.submitOrder(true, "Submit the Order");
			confPage.getConfirmationMssg(true, "Product Confirmation");

		}

		catch (Exception e) {

			e.printStackTrace();
		}

	}

}
