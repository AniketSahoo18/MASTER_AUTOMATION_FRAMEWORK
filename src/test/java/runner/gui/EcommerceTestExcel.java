package runner.gui;

import java.util.Map;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import baseFactory.TestBase;
import dataUtil.DataProvidersUI;
import websites.Authorization;

@Listeners(listeners.TestListener.class)

public final class EcommerceTestExcel extends TestBase {

	private EcommerceTestExcel() {
	}

	@Test(dataProvider = "testData_Fashion", dataProviderClass = DataProvidersUI.class, enabled = true, priority = 1)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test to check Fashion Item Order from Ecommerce Website")
	public void ecommerceFashion_Test(Map<String, String> testData) {

		try {

			Authorization.loginToLetsShopWebsite()
						 .addProductToCart(testData.get("Product"), true, "Add Product to Cart")
						 .placeOrder(testData.get("Product"), true, "Checkout and Place Order")
						 .submitOrder(true, "Place Order")
						 .getConfirmationMssg(true, "Product Confirmation");

		}

		catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test(dataProvider = "testData_Electronics", dataProviderClass = DataProvidersUI.class, enabled = true, priority = 2)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test to check Electronics Item Order from Ecommerce Website")
	public void ecommerceElectronics_Test(Map<String, String> testData) {

		try {

			Authorization.loginToLetsShopWebsite()
						 .addProductToCart(testData.get("Product"), true, "Add Product to Cart")
						 .placeOrder(testData.get("Product"), true, "Checkout and Place Order")
						 .submitOrder(true, "Place Order")
						 .getConfirmationMssg(true, "Product Confirmation");

		}

		catch (Exception e) {

			e.printStackTrace();
		}

	}

}
