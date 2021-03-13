package com.nopcommerce.others;

import com.nopcommerce.common.Common_01_RegisterUser;
import commons.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import pageObjects.nopcommerce.DesktopsPageObject;
import pageObjects.nopcommerce.HomePageObject;
import pageObjects.nopcommerce.LoginPageObject;
import pageObjects.nopcommerce.PageGeneratorManager;
import pageObjects.nopcommerce.ProductPageObject;
import pageObjects.nopcommerce.ShoppingCartPageObject;

public class Others_04_Order extends AbstractTest {

	private WebDriver driver;
	private String email, password;
	private String productName1, processor, ram, hdd, os, software;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		email = Common_01_RegisterUser.email;
		password = Common_01_RegisterUser.password;

		productName1 = "Build your own computer";
		processor = "2.2 GHz Intel Pentium Dual-Core E2200";
		ram = "4GB [+$20.00]";
		hdd = "320 GB";
		os = "Vista Premium [+$60.00]";
		software = "Microsoft Office [+$50.00]";

		log.info("Pre-conditions: Login to the System and go to My Account page");

		homePage.clickToNopCommerceHeaderLinkByText(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();

		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@Test()
	public void TC_01_AddProductToCartWithDetail() {
		homePage.clickToNopCommerceSubMenuByText(driver, "Computers ", "Desktops ");
		desktopsPage = PageGeneratorManager.getDesktopsPage(driver);

		desktopsPage.clickToNopCommerceLinkByClass(driver, "product-item", productName1);
		productPage = PageGeneratorManager.getProductPage(driver);
		productPage.selectNopCommerceDropdownListByName(driver, "product_attribute_1", processor);
		productPage.selectNopCommerceDropdownListByName(driver, "product_attribute_2", ram);
		
		productPage.clickToNopCommerceRadioButtonByText(driver, hdd);
		productPage.clickToNopCommerceRadioButtonByText(driver, os);
		productPage.checkOnNopCommerceCheckboxByText(driver, software);
		productPage.clickToNopCommerceSubButtonByValue(driver, "Add to cart");
		
		productPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "shopping cart");
		productPage.clickToNopCommerceLinkByClass(driver, "bar-notification", "shopping cart");
		
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		
		verifyEquals(shoppingCartPage.getNopCommerceProductNameByText(driver, productName1), productName1);
		log.info("Price ----------------------------");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName1, 4), "$1,330.00");
		log.info("Total price ----------------------------");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName1, 6), "$1,330.00");
		
		log.info("Product detail ----------------------------");
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, processor).contains(processor));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, ram).contains(ram));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, hdd).contains(hdd));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, os).contains(os));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, software).contains(software));
		
	}

	@Test()
	public void TC_02_EditProductInShoppingCart() {
	}

	@Test()
	public void TC_03_RemoveFromCart() {
	}

	@Test()
	public void TC_04_UpdateShoppingCart() {
	}

	@Test()
	public void TC_05_CheckOutOrderProductByCheque() {
	}

	@Test()
	public void TC_06_CheckOutOrderProductByCreditCard() {
	}

	@Test()
	public void TC_07_ReOrderProduct() {
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private DesktopsPageObject desktopsPage;
	private ProductPageObject productPage;
	private ShoppingCartPageObject shoppingCartPage;
}
