package com.nopcommerce.others;

import com.nopcommerce.common.Common_01_RegisterUser;
import commons.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.nopcommerce.*;

public class Others_04_Order extends AbstractTest {

	private WebDriver driver;
	private String email, password;
	private String productName1, productName2, productName3, processor, ram, hdd, os, software, editProcessor, editRam, editHDD, editOS, editSoftware;
	private String productCode3, billFirstName, billLastName, billEmail, billCompany, billCountry, billState, billCity, billAdd1, billAdd2, billZipcode, billPhone, billFax;
	private String shipFirstName, shipLastName, shipEmail, shipCompany, shipCountry, shipState, shipCity, shipAdd1, shipAdd2, shipZipcode, shipPhone, shipFax;

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

		editProcessor = "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
		editRam = "2 GB";
		editHDD = "400 GB [+$100.00]";
		editOS = "Vista Home [+$50.00]";
		editSoftware = "Acrobat Reader [+$10.00]";

		productName2 = "Lenovo IdeaCentre 600 All-in-One PC";
		productName3 = "Apple MacBook Pro 13-inch";
		productCode3 = "AP_MBP_13";

		billFirstName = "Automation";
		billLastName = "Selenium";
		billEmail = "selenium" + randomNumber() + "@gmail.com";
		billCompany = "Automation FC";
		billCountry = "Viet Nam";
		billState = "Other";
		billCity = "Ho Chi Minh";
		billAdd1 = "01 Tran Van Du";
		billAdd2 = "131 Nguyen Duc Thuan";
		billZipcode = "70000";
		billPhone = "09534483323";
		billFax = "02843324872847";

		shipFirstName = "Anh";
		shipLastName = "Nguyen";
		shipEmail = "anh" + randomNumber() + "@gmail.com";
		shipCompany = "CLA";
		shipCountry = "United States";
		shipState = "Alaska";
		shipCity = "NewYork";
		shipAdd1 = "01 NewYork";
		shipAdd2 = "";
		shipZipcode = "50000";
		shipPhone = "07834349233";
		shipFax = "34234234544";

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
		log.info("TC_02_AddProductToCartFromWishlistPage - Step 01: Click to 'Wishlist' Link");
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

		shoppingCartPage.clickToNopCommerceHomePage(driver);
	}

	@Test(dependsOnMethods = "TC_01_AddProductToCartWithDetail")
	public void TC_02_EditProductInShoppingCart() {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToNopCommerceHeaderOtherLinkByText(driver, "Shopping cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		shoppingCartPage.clickToEditLink(driver);
		productPage = PageGeneratorManager.getProductPage(driver);
		productPage.selectNopCommerceDropdownListByName(driver, "product_attribute_1", editProcessor);
		productPage.selectNopCommerceDropdownListByName(driver, "product_attribute_2", editRam);
		productPage.clickToNopCommerceRadioButtonByText(driver, editHDD);
		productPage.clickToNopCommerceRadioButtonByText(driver, editOS);

		productPage.unCheckOnNopCommerceCheckboxByText(driver, software);
		productPage.checkOnNopCommerceCheckboxByText(driver, editSoftware);

		productPage.inputNopcommerceProductQuantity(driver, "2");

		productPage.clickToNopCommerceSubButtonByValue(driver, "Update");

		productPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "shopping cart");
		productPage.clickToNopCommerceLinkByClass(driver, "bar-notification", "shopping cart");

		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		verifyEquals(shoppingCartPage.getNopCommerceProductNameByText(driver, productName1), productName1);
		log.info("Price ----------------------------");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName1, 4), "$1,375.00");
		log.info("Quantity  ----------------------------");
		verifyEquals(shoppingCartPage.getNopCommerceProductQtyByColumn(driver, productName1, 5), "2");
		log.info("Total price ----------------------------");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName1, 6), "$2,750.00");


		log.info("Product detail ----------------------------");

		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, editProcessor).contains(editProcessor));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, editRam).contains(editRam));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, editHDD).contains(editHDD));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, editOS).contains(editOS));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, editSoftware).contains(editSoftware));

		shoppingCartPage.clickToNopCommerceHomePage(driver);

	}

	@Test(dependsOnMethods = "TC_02_EditProductInShoppingCart")
	public void TC_03_RemoveFromCart() {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToNopCommerceHeaderOtherLinkByText(driver, "Shopping cart");

		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		log.info("Click Remove icon ------------------------------------------------------------");
		shoppingCartPage.clickToNopCommerceProductButtonIconByColumn(driver, productName1, 7);

		log.info("Verify delete item successfully ---------------------------------------------------------------------");
		verifyEquals(shoppingCartPage.getShoppingCardResultMsgByClass(driver, "no-data"), "Your Shopping Cart is empty!");
		verifyTrue(shoppingCartPage.isNopCommerceProductUndisplayed(driver, productName1));

		shoppingCartPage.clickToNopCommerceHomePage(driver);
	}

	@Test(dependsOnMethods = "TC_03_RemoveFromCart")
	public void TC_04_UpdateShoppingCart() {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToNopCommerceSubMenuByText(driver, "Computers ", "Desktops ");
		productPage = PageGeneratorManager.getProductPage(driver);
		productPage.clickToNopCommerceSubButtonByProductName(driver, productName2, "Add to cart");

		productPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "shopping cart");
		productPage.clickToNopCommerceLinkByClass(driver, "bar-notification", "shopping cart");

		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		verifyEquals(shoppingCartPage.getNopCommerceProductNameByText(driver, productName2), productName2);
		log.info("Price ----------------------------");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName1, 4), "$500.00");
		log.info("Quantity  ----------------------------");
		verifyEquals(shoppingCartPage.getNopCommerceProductQtyByColumn(driver, productName1, 5), "1");
		log.info("Total price ----------------------------");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName1, 6), "$500.00");

		shoppingCartPage.inputNopcommerceProductQuantity(driver, "5");
		shoppingCartPage.clickToNopCommerceSubButtonByValue(driver, "Update shopping cart");

		log.info("Total price ----------------------------");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName1, 6), "$2,500.00");

		log.info("Clear the product in Shopping card -------------------------");
		shoppingCartPage.clickToNopCommerceProductButtonIconByColumn(driver, productName2, 7);

		log.info("Verify delete product successfully -----------------------------------");
		verifyEquals(shoppingCartPage.getShoppingCardResultMsgByClass(driver, "no-data"), "Your Shopping Cart is empty!");
		verifyTrue(shoppingCartPage.isNopCommerceProductUndisplayed(driver, productName2));
		shoppingCartPage.clickToNopCommerceHomePage(driver);
	}

	@Test(dependsOnMethods = "TC_04_UpdateShoppingCart")
	public void TC_05_CheckOutOrderProductByCheque() {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToNopCommerceSubMenuByText(driver, "Computers ", "Notebooks ");
		noteBookPage = PageGeneratorManager.getNoteBooksPage(driver);

		noteBookPage.clickToNopCommerceLinkByClass(driver, "product-item", productName3);

		productPage = PageGeneratorManager.getProductPage(driver);
		productPage.clickToAddToCartButton(driver);

		productPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "shopping cart");
		productPage.clickToNopCommerceLinkByClass(driver, "bar-notification", "shopping cart");

		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("Verify Product Name is displayed ----------------------------------------------");
		verifyEquals(shoppingCartPage.getNopCommerceProductNameByText(driver, productName3), productName3);

		log.info("Verify product code --------------------------------------------------------------");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName3, 1), productCode3);
		log.info("Verify Price ---------------------------------------------------------------------");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName3, 4), "$1,800.00");
		log.info("Verify Product Qty --------------------------------------------------------------");
		verifyEquals(shoppingCartPage.getNopCommerceProductQtyByColumn(driver, productName3, 5), "2");
		log.info("Verify Total Price --------------------------------------------------------------");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName3, 6), "$3,600.00");

		shoppingCartPage.selectNopCommerceDropdownListByName(driver, "checkout_attribute_1", "No");
		shoppingCartPage.checkOnNopCommerceCheckboxByID(driver, "termsofservice");
		shoppingCartPage.clickToNopCommerceSubButtonByValue(driver, " Checkout ");

		checkOutPage = PageGeneratorManager.getCheckOutPage(driver);

		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Billing address"));

		checkOutPage.unCheckOnNopCommerceCheckboxByText(driver, "Ship to the same address");
		checkOutPage.setNopCommerceValueForTextBoxByID(driver,"BillingNewAddress_FirstName", billFirstName);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver,"BillingNewAddress_LastName", billLastName);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver,"BillingNewAddress_Email", billEmail);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver,"BillingNewAddress_Company", billCompany);

		checkOutPage.selectNopCommerceDropdownListByName(driver,"BillingNewAddress.CountryId",billCountry);
		checkOutPage.selectNopCommerceDropdownListByName(driver,"BillingNewAddress.StateProvinceId",billState);

		checkOutPage.setNopCommerceValueForTextBoxByID(driver,"BillingNewAddress_City", billCity);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver,"BillingNewAddress_Address1", billAdd1);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver,"BillingNewAddress_Address2", billAdd2);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver,"BillingNewAddress_ZipPostalCode", billZipcode);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver,"BillingNewAddress_PhoneNumber", billPhone);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver,"BillingNewAddress_FaxNumber", billFax);

		checkOutPage.clickToButtonByID(driver, "billing-buttons-container", "Continue");

		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Shipping address"));
		verifyTrue(checkOutPage.isShippingTextDisplayed(driver));
		checkOutPage.selectNopCommerceDropdownListByName(driver, "shipping_address_id", "New Address");

		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_FirstName", shipFirstName);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_LastName", shipLastName);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_Email", shipEmail);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_Company", shipCompany);

		checkOutPage.selectNopCommerceDropdownListByName(driver, "ShippingNewAddress.CountryId",shipCountry);
		checkOutPage.sleepInSecond(driver, 1);
		checkOutPage.selectNopCommerceDropdownListByName(driver, "ShippingNewAddress.StateProvinceId",shipState);

		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_City", shipCity);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_Address1", shipAdd1);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_Address2", shipAdd2);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_ZipPostalCode", shipZipcode);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_PhoneNumber", shipPhone);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_FaxNumber", shipFax);

		checkOutPage.clickToButtonByID(driver, "shipping-buttons-container", "Continue");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Shipping method"));

		checkOutPage.clickToNopCommerceRadioButtonByText(driver, "Ground ($0.00)");
		checkOutPage.clickToButtonByID(driver, "shipping-method-buttons-container", "Continue");

		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Payment method"));
		checkOutPage.clickToNopCommerceRadioButtonByText(driver,"Check / Money Order");
		checkOutPage.clickToButtonByID(driver,"payment-method-buttons-container", "Continue");

		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Payment information"));

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
	private NoteBooksPageObject noteBookPage;
	private CheckOutPageObject checkOutPage;
}
