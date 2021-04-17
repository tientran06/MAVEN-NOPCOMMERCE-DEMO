package com.nopcommerce.others;

import com.nopcommerce.common.Common_01_RegisterUser;
import commons.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.nopcommerce.*;

public class Others_04_Order extends AbstractTest {

	private WebDriver driver;
	private String email, password, cardType, cardName, cardNumber, expireMonth, expireYear, cardCode, existingBillAdd, existingShipAdd;
	private String productName1, productName2, productName3, productName4, processor, ram, hdd, os, software, editProcessor, editRam, editHDD, editOS, editSoftware, orderNo;
	private String productCode3, productCode4, billFirstName, billLastName, billEmail, billCompany, billCountry, billState, billCity, billAdd1, billAdd2, billZipcode, billPhone, billFax;
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
		productName4 = "HTC One M8 Android L 5.0 Lollipop";
		productCode4 = "M8_HTC_5L";

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
		billPhone = "093" + randomNumber7Digits();
		billFax = "028433" + randomNumber7Digits();
		existingBillAdd = billFirstName + " " + billLastName + ", " + billAdd1 + ", " + billCity + " " + billZipcode + ", " + billCountry;

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
		shipPhone = "078" + randomNumber7Digits();
		shipFax = "028543" + randomNumber7Digits();
		existingShipAdd = shipFirstName + " " + shipLastName + ", " + shipAdd1 + ", " + shipCity + ", " + shipState + " " + shipZipcode + ", " + shipCountry;

		cardType = "Visa";
		cardName = "Tran Tuan Ngoc";
		cardNumber = "445093000321453";
		expireMonth = "12";
		expireYear = "2022";
		cardCode = "060";

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
		log.info("TC_01_AddProductToCartWithDetail - Step 01: Click to 'Desktops' Menu");
		homePage.clickToNopCommerceSubMenuByText(driver, "Computers ", "Desktops ");
		desktopsPage = PageGeneratorManager.getDesktopsPage(driver);

		log.info("TC_01_AddProductToCartWithDetail - Step 02: Click to Product link");
		desktopsPage.clickToNopCommerceLinkByClass(driver, "product-item", productName1);
		productPage = PageGeneratorManager.getProductPage(driver);

		log.info("TC_01_AddProductToCartWithDetail - Step 03: Select required fields");
		productPage.selectNopCommerceDropdownListByName(driver, "product_attribute_1", processor);
		productPage.selectNopCommerceDropdownListByName(driver, "product_attribute_2", ram);
		productPage.clickToNopCommerceRadioButtonByText(driver, hdd);
		productPage.clickToNopCommerceRadioButtonByText(driver, os);
		productPage.checkOnNopCommerceCheckboxByText(driver, software);

		log.info("TC_01_AddProductToCartWithDetail - Step 04: Click 'Add to cart' button");
		productPage.clickToNopCommerceSubButtonByValue(driver, "Add to cart");

		log.info("TC_01_AddProductToCartWithDetail - Step 05: Verify the Product is added to cart successfully");
		productPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "shopping cart");

		log.info("TC_01_AddProductToCartWithDetail - Step 06: Click to 'Shopping cart' link");
		productPage.clickToNopCommerceLinkByClass(driver, "bar-notification", "shopping cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("TC_01_AddProductToCartWithDetail - Step 07: Verify Product Name is displayed in Shopping cart");
		verifyEquals(shoppingCartPage.getNopCommerceProductNameByText(driver, productName1), productName1);
		log.info("TC_01_AddProductToCartWithDetail - Step 08: Verify the Product's price in Shopping cart");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName1, 4), "$1,330.00");
		log.info("TC_01_AddProductToCartWithDetail - Step 09: Verify the Product's Total price in Shopping cart");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName1, 6), "$1,330.00");

		log.info("TC_01_AddProductToCartWithDetail - Step 10: Verify Product information is the same with the selection in Shopping cart");
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, processor).contains(processor));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, ram).contains(ram));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, hdd).contains(hdd));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, os).contains(os));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, software).contains(software));

		log.info("TC_01_AddProductToCartWithDetail - Step 11: Back to the Home page ");
		shoppingCartPage.clickToNopCommerceHomePage(driver);
	}

	@Test(dependsOnMethods = "TC_01_AddProductToCartWithDetail")
	public void TC_02_EditProductInShoppingCart() {
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("TC_02_EditProductInShoppingCart - Step 01: Click to 'Shopping cart' Header link");
		homePage.clickToNopCommerceHeaderOtherLinkByText(driver, "Shopping cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("TC_02_EditProductInShoppingCart - Step 02: Click to 'Edit' link");
		shoppingCartPage.clickToEditLink(driver);
		productPage = PageGeneratorManager.getProductPage(driver);

		log.info("TC_02_EditProductInShoppingCart - Step 03: Edit Product's information");
		productPage.selectNopCommerceDropdownListByName(driver, "product_attribute_1", editProcessor);
		productPage.selectNopCommerceDropdownListByName(driver, "product_attribute_2", editRam);
		productPage.clickToNopCommerceRadioButtonByText(driver, editHDD);
		productPage.clickToNopCommerceRadioButtonByText(driver, editOS);
		productPage.unCheckOnNopCommerceCheckboxByText(driver, software);
		productPage.checkOnNopCommerceCheckboxByText(driver, editSoftware);
		productPage.inputNopcommerceProductQuantity(driver, "2");

		log.info("TC_02_EditProductInShoppingCart - Step 03: Click to 'Update' button");
		productPage.clickToNopCommerceSubButtonByValue(driver, "Update");

		log.info("TC_02_EditProductInShoppingCart - Step 04: Verify the Product is added into 'Shopping cart' successfully");
		productPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "shopping cart");

		log.info("TC_02_EditProductInShoppingCart - Step 05: Click to 'Shopping cart' button");
		productPage.clickToNopCommerceLinkByClass(driver, "bar-notification", "shopping cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("TC_02_EditProductInShoppingCart - Step 06: Verify Product Name is displayed successfully");
		verifyEquals(shoppingCartPage.getNopCommerceProductNameByText(driver, productName1), productName1);

		log.info("TC_02_EditProductInShoppingCart - Step 07: Verify the Price after editting the Product");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName1, 4), "$1,375.00");

		log.info("TC_02_EditProductInShoppingCart - Step 08: Verify the Quatity after editting the Product");
		verifyEquals(shoppingCartPage.getNopCommerceProductQtyByColumn(driver, productName1, 5), "2");

		log.info("TC_02_EditProductInShoppingCart - Step 09: Verify the Total price after editting the Product");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName1, 6), "$2,750.00");

		log.info("TC_02_EditProductInShoppingCart - Step 10: Verify the Product's detail are correct");

		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, editProcessor).contains(editProcessor));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, editRam).contains(editRam));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, editHDD).contains(editHDD));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, editOS).contains(editOS));
		verifyTrue(shoppingCartPage.getNopCommerceProductDescriptionByColumn(driver, productName1, 3, editSoftware).contains(editSoftware));

		log.info("TC_02_EditProductInShoppingCart - Step 11: Back to the Home page");
		shoppingCartPage.clickToNopCommerceHomePage(driver);

	}

	@Test(dependsOnMethods = "TC_02_EditProductInShoppingCart")
	public void TC_03_RemoveFromCart() {
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("TC_03_RemoveFromCart - Step 01: Click to 'Shopping cart' header link");
		homePage.clickToNopCommerceHeaderOtherLinkByText(driver, "Shopping cart");

		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("TC_03_RemoveFromCart - Step 02: Click to 'Remove' icon");
		shoppingCartPage.clickToNopCommerceProductButtonIconByColumn(driver, productName1, 7);

		log.info("TC_03_RemoveFromCart - Step 03: Verify the Product is removed successfully");
		verifyEquals(shoppingCartPage.getShoppingCardResultMsgByClass(driver, "no-data"), "Your Shopping Cart is empty!");
		verifyTrue(shoppingCartPage.isNopCommerceProductUndisplayed(driver, productName1));

		log.info("TC_03_RemoveFromCart - Step 04: Back to Home page");
		shoppingCartPage.clickToNopCommerceHomePage(driver);
	}

	@Test(dependsOnMethods = "TC_03_RemoveFromCart")
	public void TC_04_UpdateShoppingCart() {
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("TC_04_UpdateShoppingCart - Step 01: Click to 'Desktops' menu");
		homePage.clickToNopCommerceSubMenuByText(driver, "Computers ", "Desktops ");
		productPage = PageGeneratorManager.getProductPage(driver);

		log.info("TC_04_UpdateShoppingCart - Step 02: Add Product into Cart");
		productPage.clickToNopCommerceSubButtonByProductName(driver, productName2, "Add to cart");

		log.info("TC_04_UpdateShoppingCart - Step 03: Verify Product is added successfully");
		productPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "shopping cart");

		log.info("TC_04_UpdateShoppingCart - Step 04: Click to 'Shopping cart' link");
		productPage.clickToNopCommerceLinkByClass(driver, "bar-notification", "shopping cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("TC_04_UpdateShoppingCart - Step 05: Verify Product's Name is displayed");
		verifyEquals(shoppingCartPage.getNopCommerceProductNameByText(driver, productName2), productName2);

		log.info("TC_04_UpdateShoppingCart - Step 06: Verify Product's Price is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName1, 4), "$500.00");

		log.info("TC_04_UpdateShoppingCart - Step 07: Verify Product's quantity is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductQtyByColumn(driver, productName1, 5), "1");

		log.info("TC_04_UpdateShoppingCart - Step 08: Verify Product's total price is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName1, 6), "$500.00");

		log.info("TC_04_UpdateShoppingCart - Step 09: Update the Product's quantity");
		shoppingCartPage.inputNopcommerceProductQuantity(driver, "5");

		log.info("TC_04_UpdateShoppingCart - Step 10: Click to 'Update shopping cart' button");
		shoppingCartPage.clickToNopCommerceSubButtonByValue(driver, "Update shopping cart");

		log.info("TC_04_UpdateShoppingCart - Step 11: Verify the Product's total price after updating quantity");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName1, 6), "$2,500.00");

		log.info("TC_04_UpdateShoppingCart - Step 11: Clear the Product in Shopping cart");
		shoppingCartPage.clickToNopCommerceProductButtonIconByColumn(driver, productName2, 7);

		log.info("TC_04_UpdateShoppingCart - Step 12: Verify delete Product successfully");
		verifyEquals(shoppingCartPage.getShoppingCardResultMsgByClass(driver, "no-data"), "Your Shopping Cart is empty!");
		verifyTrue(shoppingCartPage.isNopCommerceProductUndisplayed(driver, productName2));

		log.info("TC_04_UpdateShoppingCart - Step 13: Back to the Home page");
		shoppingCartPage.clickToNopCommerceHomePage(driver);
	}

	@Test(dependsOnMethods = "TC_04_UpdateShoppingCart")
	public void TC_05_CheckOutOrderProductByCheque() {
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("TC_05_CheckOutOrderProductByCheque - Step 01: Click to 'Notebooks' menu");
		homePage.clickToNopCommerceSubMenuByText(driver, "Computers ", "Notebooks ");
		noteBookPage = PageGeneratorManager.getNoteBooksPage(driver);

		log.info("TC_05_CheckOutOrderProductByCheque - Step 02: Click to Product's link");
		noteBookPage.clickToNopCommerceLinkByClass(driver, "product-item", productName3);
		productPage = PageGeneratorManager.getProductPage(driver);

		log.info("TC_05_CheckOutOrderProductByCheque - Step 03: Click to 'Add to cart' button");
		productPage.clickToAddToCartButton(driver);

		log.info("TC_05_CheckOutOrderProductByCheque - Step 04: Verify the Product is added into 'Shopping cart' successfully");
		productPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "shopping cart");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 05: Click to 'Shopping cart' link");
		productPage.clickToNopCommerceLinkByClass(driver, "bar-notification", "shopping cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("TC_05_CheckOutOrderProductByCheque - Step 06: Verify Product's Name is displayed");
		verifyEquals(shoppingCartPage.getNopCommerceProductNameByText(driver, productName3), productName3);

		log.info("TC_05_CheckOutOrderProductByCheque - Step 07: Verify Product's Code is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName3, 1), productCode3);

		log.info("TC_05_CheckOutOrderProductByCheque - Step 08: Verify Product's price is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName3, 4), "$1,800.00");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 09: Verify Product's quantity is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductQtyByColumn(driver, productName3, 5), "2");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 10: Verify Product's total price is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName3, 6), "$3,600.00");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 11: Select 'Gift wrapping' option");
		shoppingCartPage.selectNopCommerceDropdownListByName(driver, "checkout_attribute_1", "No");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 12: Check on 'Term service' checkbox");
		shoppingCartPage.checkOnNopCommerceCheckboxByID(driver, "termsofservice");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 13: Click on 'Check out' button");
		shoppingCartPage.clickToNopCommerceSubButtonByValue(driver, " Checkout ");
		checkOutPage = PageGeneratorManager.getCheckOutPage(driver);

		log.info("TC_05_CheckOutOrderProductByCheque - Step 14: Verify 'Billing address' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Billing address"));

		log.info("TC_05_CheckOutOrderProductByCheque - Step 15: Input required information of Billing address");
		checkOutPage.unCheckOnNopCommerceCheckboxByText(driver, "Ship to the same address");
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "BillingNewAddress_FirstName", billFirstName);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "BillingNewAddress_LastName", billLastName);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "BillingNewAddress_Email", billEmail);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "BillingNewAddress_Company", billCompany);

		checkOutPage.selectNopCommerceDropdownListByName(driver, "BillingNewAddress.CountryId", billCountry);
		checkOutPage.selectNopCommerceDropdownListByName(driver, "BillingNewAddress.StateProvinceId", billState);

		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "BillingNewAddress_City", billCity);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "BillingNewAddress_Address1", billAdd1);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "BillingNewAddress_Address2", billAdd2);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "BillingNewAddress_ZipPostalCode", billZipcode);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "BillingNewAddress_PhoneNumber", billPhone);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "BillingNewAddress_FaxNumber", billFax);

		log.info("TC_05_CheckOutOrderProductByCheque - Step 16: Click on 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "billing-buttons-container", "Continue");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 17: Verify 'Shipping address' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Shipping address"));
		verifyTrue(checkOutPage.isShippingTextDisplayed(driver));

		log.info("TC_05_CheckOutOrderProductByCheque - Step 18: Select 'New Address' option");
		checkOutPage.selectNopCommerceDropdownListByName(driver, "shipping_address_id", "New Address");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 19: Input required field of Shipping address");
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_FirstName", shipFirstName);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_LastName", shipLastName);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_Email", shipEmail);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_Company", shipCompany);

		checkOutPage.selectNopCommerceDropdownListByName(driver, "ShippingNewAddress.CountryId", shipCountry);
		checkOutPage.sleepInSecond(driver, 1);
		checkOutPage.selectNopCommerceDropdownListByName(driver, "ShippingNewAddress.StateProvinceId", shipState);

		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_City", shipCity);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_Address1", shipAdd1);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_Address2", shipAdd2);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_ZipPostalCode", shipZipcode);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_PhoneNumber", shipPhone);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "ShippingNewAddress_FaxNumber", shipFax);

		log.info("TC_05_CheckOutOrderProductByCheque - Step 20: Click on 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "shipping-buttons-container", "Continue");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 21: Verify 'Shipping method' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Shipping method"));

		log.info("TC_05_CheckOutOrderProductByCheque - Step 22: Select Shipping method");
		checkOutPage.clickToNopCommerceRadioButtonByText(driver, "Ground ($0.00)");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 23: Click to 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "shipping-method-buttons-container", "Continue");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 24: Verify 'Payment method' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Payment method"));

		log.info("TC_05_CheckOutOrderProductByCheque - Step 25: Select Payment method");
		checkOutPage.clickToNopCommerceRadioButtonByText(driver, "Check / Money Order");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 26: Click on 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "payment-method-buttons-container", "Continue");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 27: Verify 'Payment information' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Payment information"));
		verifyTrue(checkOutPage.isPaymentInforDisplayedByText(driver, "Mail Personal or Business Check, Cashier's Check or money order to:"));
		verifyTrue(checkOutPage.isPaymentInforDisplayedByText(driver, "NOP SOLUTIONS"));
		verifyTrue(checkOutPage.isPaymentInforDisplayedByText(driver, "your address here,"));
		verifyTrue(checkOutPage.isPaymentInforDisplayedByText(driver, "New York, NY 10001"));
		verifyTrue(checkOutPage.isPaymentInforDisplayedByText(driver, "USA"));

		log.info("TC_05_CheckOutOrderProductByCheque - Step 28: Click on 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "checkout-step-payment-info", "Continue");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 29: Verify 'Confirm order' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Confirm order"));

		log.info("TC_05_CheckOutOrderProductByCheque - Step 30: Verify Billing information are correct");
		verifyTrue(checkOutPage.isConfirmOrderTitleDisplayedByText(driver, "billing-info", "Billing Address"));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "name").contains(billFirstName + " " + billLastName));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "email").contains(billEmail));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "phone").contains(billPhone));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "fax").contains(billFax));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "company").contains(billCompany));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "address1").contains(billAdd1));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "address2").contains(billAdd2));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "city-state-zip").contains(billCity + "," + billZipcode));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "country").contains(billCountry));

		log.info("TC_05_CheckOutOrderProductByCheque - Step 31: Verify Payment method is correct");
		verifyTrue(checkOutPage.isConfirmOrderTitleDisplayedByText(driver, "payment-method-info", "Payment"));
		verifyTrue(checkOutPage.getConfirmOrderMethodByClass(driver, "payment-method-info", "Payment Method:").contains("Check / Money Order"));

		log.info("TC_05_CheckOutOrderProductByCheque - Step 32: Verify Shipping address are correct");
		verifyTrue(checkOutPage.isConfirmOrderTitleDisplayedByText(driver, "shipping-info", "Shipping Address"));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "name").contains(shipFirstName + " " + shipLastName));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "email").contains(shipEmail));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "phone").contains(shipPhone));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "fax").contains(shipFax));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "company").contains(shipCompany));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "address1").contains(shipAdd1));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "city-state-zip").contains(shipCity + "," + shipState + "," + shipZipcode));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "country").contains(shipCountry));

		log.info("TC_05_CheckOutOrderProductByCheque - Step 33: Verify Shipping method is correct");
		verifyTrue(checkOutPage.isConfirmOrderTitleDisplayedByText(driver, "shipping-method-info", "Shipping"));
		verifyTrue(checkOutPage.getConfirmOrderMethodByClass(driver, "shipping-method-info", "Shipping Method:").contains("Ground"));

		log.info("TC_05_CheckOutOrderProductByCheque - Step 33: Verify the Product information are correct");
		verifyEquals(checkOutPage.getNopCommerceProductInforByColumn(driver, productName3, 1), productCode3);
		verifyEquals(checkOutPage.getNopCommerceProductNameByText(driver, productName3), productName3);
		verifyEquals(checkOutPage.getNopCommerceProductInforByColumn(driver, productName3, 4), "$1,800.00");
		verifyEquals(checkOutPage.getNopCommerceProductInforByColumn(driver, productName3, 5), "2");
		verifyEquals(checkOutPage.getNopCommerceProductInforByColumn(driver, productName3, 6), "$3,600.00");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 34: Verify Payment information are correct");
		verifyTrue(checkOutPage.getCartOptionStatus(driver).contains("No"));
		verifyEquals(checkOutPage.getTotalInforByText(driver, "total-info", "Sub-Total:"), "$3,600.00");
		verifyEquals(checkOutPage.getTotalInforByText(driver, "total-info", "Shipping:"), "$0.00");
		verifyEquals(checkOutPage.getTotalInforByText(driver, "total-info", "Tax:"), "$0.00");
		verifyEquals(checkOutPage.getTotalInforByText(driver, "total-info", "Total:"), "$3,600.00");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 35: Click to 'Confirm' button");
		checkOutPage.clickToNopCommerceButtonByValue(driver, "Confirm");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 36: Verify the Product is ordered successfully");
		verifyEquals(checkOutPage.getCheckOutCompletedPageTitleByClass(driver), "Thank you");
		verifyEquals(checkOutPage.getCheckOutCompletedDetailByClass(driver, "title"), "Your order has been successfully processed!");

		orderNo = checkOutPage.getCheckOutOrderNo(driver, "details");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 37: Back to the Home page");
		checkOutPage.clickToNopCommerceButtonByValue(driver, "Continue");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("TC_05_CheckOutOrderProductByCheque - Step 38: Click to 'My account' link");
		homePage.clickToNopCommerceHeaderLinkByText(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);

		log.info("TC_05_CheckOutOrderProductByCheque - Step 39: Click to 'Orders' list menu");
		myAccountPage.clickToNopCommerceListBoxMenuByName(driver, "Orders");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 40: Verify Order information is displayed");
		verifyEquals(myAccountPage.getMyAccountTitleByClass(driver, "page-title"), "My account - Orders");
		verifyTrue(myAccountPage.isMyAccountOrderDisplayed(driver, orderNo));
		verifyEquals(myAccountPage.getMyAccountOrderInfoByOrderNo(driver, orderNo, "order-total"), "$3,600.00");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 41: Click to 'Detail' link based on Order No");
		myAccountPage.clickToOrderDetailButtonByOrderNo(driver, orderNo);
		orderDetailPage = PageGeneratorManager.getOrderDetailPage(driver);

		log.info("TC_05_CheckOutOrderProductByCheque - Step 42: Verify Order information are displayed correctly");
		verifyTrue(orderDetailPage.getOrderDetailTextByClass(driver, "order-overview", "order-number").contains(orderNo));
		verifyEquals(orderDetailPage.getOrderDetailTextByClass(driver, "order-overview", "order-number"), "ORDER #" + orderNo);
		verifyEquals(orderDetailPage.getOrderDetailContentByClass(driver, "order-overview", "order-date"), "Order Date: " +getCSTDateTime());
		verifyTrue(orderDetailPage.getOrderDetailContentByClass(driver, "order-overview", "order-status").contains("Pending"));
		verifyEquals(orderDetailPage.getOrderTotalPriceByClass(driver, "order-total"), "$3,600.00");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 43: Verify Billing information / Shipping information are displayed correctly");
		verifyTrue(orderDetailPage.getOrderDetailContentByClass(driver, "billing-info", "email").contains(billEmail));
		verifyTrue(orderDetailPage.getOrderDetailContentByClass(driver, "shipping-info", "email").contains(shipEmail));
		verifyEquals(orderDetailPage.getOrderMethodByClass(driver, "payment-method-info", "payment-method"), "Check / Money Order");
		verifyEquals(orderDetailPage.getOrderMethodByClass(driver, "shipping-method-info", "shipping-method"), "Ground");

		log.info("TC_05_CheckOutOrderProductByCheque - Step 44: Verify Product's detail are displayed successfully");
		verifyEquals(orderDetailPage.getNopCommerceProductInforByColumn(driver, productName3, 1), productCode3);
		verifyEquals(orderDetailPage.getNopCommerceProductNameByText(driver, productName3), productName3);
		verifyEquals(orderDetailPage.getNopCommerceProductInforByColumn(driver, productName3, 3), "$1,800.00");
		verifyEquals(orderDetailPage.getNopCommerceProductInforByColumn(driver, productName3, 4), "2");
		verifyEquals(orderDetailPage.getNopCommerceProductInforByColumn(driver, productName3, 5), "$3,600.00");
		verifyEquals(orderDetailPage.getTotalInforByText(driver, "total-info", "Order Total:"), "$3,600.00");
	}

	@Test(dependsOnMethods = "TC_05_CheckOutOrderProductByCheque")
	public void TC_06_CheckOutOrderProductByCreditCard() {
		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 01: Click to 'Electronics' menu");
		homePage.clickToNopCommerceSubMenuByText(driver, "Electronics ", "Cell phones ");
		noteBookPage = PageGeneratorManager.getNoteBooksPage(driver);

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 02: Click to Product's link");
		noteBookPage.clickToNopCommerceLinkByClass(driver, "product-item", productName4);
		productPage = PageGeneratorManager.getProductPage(driver);

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 03: Click to 'Add to cart' button");
		productPage.clickToAddToCartButton(driver);

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 04: Verify the Product is added into 'Shopping cart' successfully");
		productPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "shopping cart");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 05: Click to 'Shopping cart' link");
		productPage.clickToNopCommerceLinkByClass(driver, "bar-notification", "shopping cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 06: Verify Product's Name is displayed");
		verifyEquals(shoppingCartPage.getNopCommerceProductNameByText(driver, productName4), productName4);

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 07: Verify Product's Code is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName4, 1), productCode4);

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 08: Verify Product's price is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName4, 4), "$245.00");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 09: Verify Product's quantity is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductQtyByColumn(driver, productName4, 5), "1");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 10: Verify Product's total price is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName4, 6), "$245.00");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 11: Select 'Gift wrapping' option");
		shoppingCartPage.selectNopCommerceDropdownListByName(driver, "checkout_attribute_1", "Yes [+$10.00]");
		shoppingCartPage.isNopCommerceDropdownListSelectedByText(driver, "checkout_attribute_1", "Yes [+$10.00]");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 12: Verify total info are correct");
		verifyTrue(shoppingCartPage.getShoppingCardTextByClass(driver, "Yes [+$10.00]").contains("Yes [+$10.00]"));
		verifyEquals(shoppingCartPage.getShoppingCArdTotalInfoByLabel(driver, "Total"), "$255.00");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 13: Check on 'Term service' checkbox");
		shoppingCartPage.checkOnNopCommerceCheckboxByID(driver, "termsofservice");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 14: Click on 'Check out' button");
		shoppingCartPage.clickToNopCommerceSubButtonByValue(driver, " Checkout ");
		checkOutPage = PageGeneratorManager.getCheckOutPage(driver);

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 15: Verify 'Billing address' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Billing address"));

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 16: Unchecked 'Ship to the same address' checkbox");
		checkOutPage.unCheckOnNopCommerceCheckboxByText(driver, "Ship to the same address");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 17: Select existing Billing address");
		checkOutPage.selectNopCommerceDropdownListByName(driver, "billing_address_id", existingBillAdd);

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 18: Click on 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "billing-buttons-container", "Continue");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 19: Verify 'Shipping address' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Shipping address"));
		verifyTrue(checkOutPage.isShippingTextDisplayed(driver));

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 20: Select existing Shipping address option");
		checkOutPage.selectNopCommerceDropdownListByName(driver, "shipping_address_id", existingShipAdd);

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 21: Click on 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "shipping-buttons-container", "Continue");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 22: Verify 'Shipping method' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Shipping method"));

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 23: Select Shipping method");
		checkOutPage.clickToNopCommerceRadioButtonByText(driver, "Ground ($0.00)");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 24: Click to 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "shipping-method-buttons-container", "Continue");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 25: Verify 'Payment method' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Payment method"));

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 26: Select Payment method");
		checkOutPage.clickToNopCommerceRadioButtonByText(driver, "Credit Card");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 27: Click on 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "payment-method-buttons-container", "Continue");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 28: Verify 'Payment information' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Payment information"));

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 29: Select/input Credit Card information");
		checkOutPage.selectNopCommerceDropdownListByName(driver, "CreditCardType", cardType);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "CardholderName", cardName);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "CardNumber", cardNumber);
		checkOutPage.selectNopCommerceDropdownListByName(driver, "ExpireMonth", expireMonth);
		checkOutPage.selectNopCommerceDropdownListByName(driver, "ExpireYear", expireYear);
		checkOutPage.setNopCommerceValueForTextBoxByID(driver, "CardCode", cardCode);

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 30: Click on 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "checkout-step-payment-info", "Continue");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 31: Verify 'Confirm order' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Confirm order"));

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 32: Verify Billing information are correct");
		verifyTrue(checkOutPage.isConfirmOrderTitleDisplayedByText(driver, "billing-info", "Billing Address"));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "name").contains(billFirstName + " " + billLastName));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "email").contains(billEmail));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "phone").contains(billPhone));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "fax").contains(billFax));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "company").contains(billCompany));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "address1").contains(billAdd1));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "address2").contains(billAdd2));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "city-state-zip").contains(billCity + "," + billZipcode));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "country").contains(billCountry));

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 33: Verify Payment method is correct");
		verifyTrue(checkOutPage.isConfirmOrderTitleDisplayedByText(driver, "payment-method-info", "Payment"));
		verifyTrue(checkOutPage.getConfirmOrderMethodByClass(driver, "payment-method-info", "Payment Method:").contains("Credit Card"));

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 34: Verify Shipping address are correct");
		verifyTrue(checkOutPage.isConfirmOrderTitleDisplayedByText(driver, "shipping-info", "Shipping Address"));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "name").contains(shipFirstName + " " + shipLastName));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "email").contains(shipEmail));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "phone").contains(shipPhone));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "fax").contains(shipFax));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "company").contains(shipCompany));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "address1").contains(shipAdd1));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "city-state-zip").contains(shipCity + "," + shipState + "," + shipZipcode));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "country").contains(shipCountry));

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 34: Verify Shipping method is correct");
		verifyTrue(checkOutPage.isConfirmOrderTitleDisplayedByText(driver, "shipping-method-info", "Shipping"));
		verifyTrue(checkOutPage.getConfirmOrderMethodByClass(driver, "shipping-method-info", "Shipping Method:").contains("Ground"));

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 35: Verify the Product information are correct");
		verifyEquals(checkOutPage.getNopCommerceProductInforByColumn(driver, productName4, 1), productCode4);
		verifyEquals(checkOutPage.getNopCommerceProductNameByText(driver, productName4), productName4);
		verifyEquals(checkOutPage.getNopCommerceProductInforByColumn(driver, productName4, 4), "$245.00");
		verifyEquals(checkOutPage.getNopCommerceProductInforByColumn(driver, productName4, 5), "1");
		verifyEquals(checkOutPage.getNopCommerceProductInforByColumn(driver, productName4, 6), "$245.00");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 36: Verify Payment information are correct");
		verifyTrue(checkOutPage.getCartOptionStatus(driver).contains("Yes [+$10.00]"));
		verifyEquals(checkOutPage.getTotalInforByText(driver, "total-info", "Sub-Total:"), "$255.00");
		verifyEquals(checkOutPage.getTotalInforByText(driver, "total-info", "Shipping:"), "$0.00");
		verifyEquals(checkOutPage.getTotalInforByText(driver, "total-info", "Tax:"), "$0.00");
		verifyEquals(checkOutPage.getTotalInforByText(driver, "total-info", "Total:"), "$255.00");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 37: Click to 'Confirm' button");
		checkOutPage.clickToNopCommerceButtonByValue(driver, "Confirm");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 38: Verify the Product is ordered successfully");
		verifyEquals(checkOutPage.getCheckOutCompletedPageTitleByClass(driver), "Thank you");
		verifyEquals(checkOutPage.getCheckOutCompletedDetailByClass(driver, "title"), "Your order has been successfully processed!");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 39: Get Order No");
		orderNo = checkOutPage.getCheckOutOrderNo(driver, "details");
		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 40: Click to Order detail linnk");
		checkOutPage.clickToNopCommerceLinkByClass(driver, "details-link", "Click here for order details.");

		orderDetailPage = PageGeneratorManager.getOrderDetailPage(driver);

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 41: Verify Order information are displayed correctly");
		verifyTrue(orderDetailPage.getOrderDetailTextByClass(driver, "order-overview", "order-number").contains(orderNo));
		verifyEquals(orderDetailPage.getOrderDetailTextByClass(driver, "order-overview", "order-number"), "ORDER #" + orderNo);
		verifyEquals(orderDetailPage.getOrderDetailContentByClass(driver, "order-overview", "order-date"), "Order Date: " +getCSTDateTime());
		verifyTrue(orderDetailPage.getOrderDetailContentByClass(driver, "order-overview", "order-status").contains("Pending"));
		verifyEquals(orderDetailPage.getOrderTotalPriceByClass(driver, "order-total"), "$255.00");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 42: Verify Billing information / Shipping information are displayed correctly");
		verifyTrue(orderDetailPage.getOrderDetailContentByClass(driver, "billing-info", "email").contains(billEmail));
		verifyTrue(orderDetailPage.getOrderDetailContentByClass(driver, "shipping-info", "email").contains(shipEmail));
		verifyEquals(orderDetailPage.getOrderMethodByClass(driver, "payment-method-info", "payment-method"), "Credit Card");
		verifyEquals(orderDetailPage.getOrderMethodByClass(driver, "shipping-method-info", "shipping-method"), "Ground");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 43: Verify Product's detail are displayed successfully");
		verifyEquals(orderDetailPage.getNopCommerceProductInforByColumn(driver, productName4, 1), productCode4);
		verifyEquals(orderDetailPage.getNopCommerceProductNameByText(driver, productName4), productName4);
		verifyEquals(orderDetailPage.getNopCommerceProductInforByColumn(driver, productName4, 3), "$245.00");
		verifyEquals(orderDetailPage.getNopCommerceProductInforByColumn(driver, productName4, 4), "1");
		verifyEquals(orderDetailPage.getNopCommerceProductInforByColumn(driver, productName4, 5), "$245.00");
		verifyTrue(orderDetailPage.getSelectedOptionStatus(driver).contains("Yes [+$10.00]"));
		verifyEquals(orderDetailPage.getTotalInforByText(driver, "total-info", "Order Total:"), "$255.00");

		log.info("TC_06_CheckOutOrderProductByCreditCard - Step 44: Back to the Home page");
		orderDetailPage.clickToNopCommerceHomePage(driver);
	}

	@Test(dependsOnMethods = "TC_06_CheckOutOrderProductByCreditCard")
	public void TC_07_ReOrderProduct() {
		
		homePage = PageGeneratorManager.getHomePage(driver);
		
		log.info("TC_07_ReOrderProduct - Step 01: Click to 'My account' link from Home page");
		homePage.clickToNopCommerceHeaderLinkByText(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);

		log.info("TC_07_ReOrderProduct - Step 02: Click to 'Orders' menu list");
		myAccountPage.clickToNopCommerceListBoxMenuByName(driver, "Orders");
		
		log.info("TC_07_ReOrderProduct - Step 03: Click to 'Detail' icon of Order No");
		myAccountPage.clickToOrderDetailButtonByOrderNo(driver, orderNo);

		log.info("TC_07_ReOrderProduct - Step 04: Click 'Re-0rder' button");
		checkOutPage.clickToNopCommerceSubButtonByValue(driver, "Re-order");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);

		log.info("TC_07_ReOrderProduct - Step 05: Verify Product's Name is displayed");
		verifyEquals(shoppingCartPage.getNopCommerceProductNameByText(driver, productName4), productName4);

		log.info("TC_07_ReOrderProduct - Step 06: Verify Product's Code is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName4, 1), productCode4);

		log.info("TC_07_ReOrderProduct - Step 07: Verify Product's price is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName4, 4), "$245.00");

		log.info("TC_07_ReOrderProduct - Step 08: Verify Product's quantity is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductQtyByColumn(driver, productName4, 5), "1");

		log.info("TC_07_ReOrderProduct - Step 9: Verify Product's total price is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName4, 6), "$245.00");

		log.info("TC_07_ReOrderProduct - Step 10: Update the Quantity into 10");
		shoppingCartPage.inputNopcommerceProductQuantity(driver, "10");
		
		log.info("TC_07_ReOrderProduct - Step 11: Click to 'Update shopping cart' button");
		shoppingCartPage.clickToNopCommerceSubButtonByValue(driver, "Update shopping cart");

		log.info("TC_07_ReOrderProduct - Step 12: Verify Product's quantity is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductQtyByColumn(driver, productName4, 5), "10");

		log.info("TC_07_ReOrderProduct - Step 13: Verify Product's total price is correct");
		verifyEquals(shoppingCartPage.getNopCommerceProductInforByColumn(driver, productName4, 6), "$2,450.00");

		log.info("TC_07_ReOrderProduct - Step 14: Verify Gift's option is selected");
		shoppingCartPage.isNopCommerceDropdownListSelectedByText(driver, "checkout_attribute_1", "Yes [+$10.00]");

		log.info("TC_07_ReOrderProduct - Step 15: Verify total info are correct");
		verifyTrue(shoppingCartPage.getShoppingCardTextByClass(driver, "Yes [+$10.00]").contains("Yes [+$10.00]"));
		verifyEquals(shoppingCartPage.getShoppingCArdTotalInfoByLabel(driver, "Total"), "$2,460.00");

		log.info("TC_07_ReOrderProduct - Step 16: Check on 'Term service' checkbox");
		shoppingCartPage.checkOnNopCommerceCheckboxByID(driver, "termsofservice");

		log.info("TC_07_ReOrderProduct - Step 17: Click on 'Check out' button");
		shoppingCartPage.clickToNopCommerceSubButtonByID(driver, "checkout");
		checkOutPage = PageGeneratorManager.getCheckOutPage(driver);

		log.info("TC_07_ReOrderProduct - Step 18: Verify 'Billing address' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Billing address"));
		
		log.info("TC_07_ReOrderProduct - Step 19: Select existing Billing address");
		checkOutPage.unCheckOnNopCommerceCheckboxByText(driver, "Ship to the same address");
		checkOutPage.selectNopCommerceDropdownListByName(driver, "billing_address_id", existingBillAdd);

		log.info("TC_07_ReOrderProduct - Step 20: Click on 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "billing-buttons-container", "Continue");
		

		log.info("TC_07_ReOrderProduct - Step 23: Verify 'Shipping address' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Shipping address"));
		verifyTrue(checkOutPage.isShippingTextDisplayed(driver));

		log.info("TC_07_ReOrderProduct - Step 24: Select existing Shipping address option");
		checkOutPage.selectNopCommerceDropdownListByName(driver, "shipping_address_id", existingShipAdd);

		log.info("TC_07_ReOrderProduct - Step 25: Click on 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "shipping-buttons-container", "Continue");

		log.info("TC_07_ReOrderProduct - Step 26: Verify 'Shipping method' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Shipping method"));

		log.info("TC_07_ReOrderProduct - Step 27: Select Shipping method");
		checkOutPage.clickToNopCommerceRadioButtonByText(driver, "Next Day Air ($0.00)");

		log.info("TC_07_ReOrderProduct - Step 28: Click to 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "shipping-method-buttons-container", "Continue");

		log.info("TC_07_ReOrderProduct - Step 29: Verify 'Payment method' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Payment method"));

		log.info("TC_07_ReOrderProduct - Step 30: Select Payment method");
		checkOutPage.clickToNopCommerceRadioButtonByText(driver, "Check / Money Order");

		log.info("TC_07_ReOrderProduct - Step 31: Click on 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "payment-method-buttons-container", "Continue");

		log.info("TC_07_ReOrderProduct - Step 32: Verify 'Payment information' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Payment information"));
		verifyTrue(checkOutPage.isPaymentInforDisplayedByText(driver, "Mail Personal or Business Check, Cashier's Check or money order to:"));
		verifyTrue(checkOutPage.isPaymentInforDisplayedByText(driver, "NOP SOLUTIONS"));
		verifyTrue(checkOutPage.isPaymentInforDisplayedByText(driver, "your address here,"));
		verifyTrue(checkOutPage.isPaymentInforDisplayedByText(driver, "New York, NY 10001"));
		verifyTrue(checkOutPage.isPaymentInforDisplayedByText(driver, "USA"));

		log.info("TC_07_ReOrderProduct - Step 33: Click on 'Continue' button");
		checkOutPage.clickToButtonByID(driver, "checkout-step-payment-info", "Continue");

		log.info("TC_07_ReOrderProduct - Step 34: Verify 'Confirm order' is displayed");
		verifyTrue(checkOutPage.isTitleDisplayedByText(driver, "Confirm order"));

		log.info("TC_07_ReOrderProduct - Step 35: Verify Billing information are correct");
		verifyTrue(checkOutPage.isConfirmOrderTitleDisplayedByText(driver, "billing-info", "Billing Address"));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "name").contains(billFirstName + " " + billLastName));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "email").contains(billEmail));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "phone").contains(billPhone));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "fax").contains(billFax));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "company").contains(billCompany));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "address1").contains(billAdd1));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "address2").contains(billAdd2));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "city-state-zip").contains(billCity + "," + billZipcode));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "billing-info", "country").contains(billCountry));

		log.info("TC_07_ReOrderProduct - Step 36: Verify Payment method is correct");
		verifyTrue(checkOutPage.isConfirmOrderTitleDisplayedByText(driver, "payment-method-info", "Payment"));
		verifyTrue(checkOutPage.getConfirmOrderMethodByClass(driver, "payment-method-info", "Payment Method:").contains("Check / Money Order"));

		log.info("TC_07_ReOrderProduct - Step 37: Verify Shipping address are correct");
		verifyTrue(checkOutPage.isConfirmOrderTitleDisplayedByText(driver, "shipping-info", "Shipping Address"));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "name").contains(shipFirstName + " " + shipLastName));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "email").contains(shipEmail));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "phone").contains(shipPhone));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "fax").contains(shipFax));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "company").contains(shipCompany));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "address1").contains(shipAdd1));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "city-state-zip").contains(shipCity + "," + shipState + "," + shipZipcode));
		verifyTrue(checkOutPage.getConfirmOrderDetailByClass(driver, "shipping-info", "country").contains(shipCountry));

		log.info("TC_07_ReOrderProduct - Step 38: Verify Shipping method is correct");
		verifyTrue(checkOutPage.isConfirmOrderTitleDisplayedByText(driver, "shipping-method-info", "Shipping"));
		verifyTrue(checkOutPage.getConfirmOrderMethodByClass(driver, "shipping-method-info", "Shipping Method:").contains("Next Day Air"));

		log.info("TC_07_ReOrderProduct - Step 39: Verify the Product information are correct");
		verifyEquals(checkOutPage.getNopCommerceProductInforByColumn(driver, productName4, 1), productCode4);
		verifyEquals(checkOutPage.getNopCommerceProductNameByText(driver, productName4), productName4);
		verifyEquals(checkOutPage.getNopCommerceProductInforByColumn(driver, productName4, 4), "$245.00");
		verifyEquals(checkOutPage.getNopCommerceProductInforByColumn(driver, productName4, 5), "10");
		verifyEquals(checkOutPage.getNopCommerceProductInforByColumn(driver, productName4, 6), "$2,450.00");

		log.info("TC_07_ReOrderProduct - Step 40: Verify Payment information are correct");
		verifyTrue(checkOutPage.getCartOptionStatus(driver).contains("Yes [+$10.00]"));
		verifyEquals(checkOutPage.getTotalInforByText(driver, "total-info", "Sub-Total:"), "$2,460.00");
		verifyEquals(checkOutPage.getTotalInforByText(driver, "total-info", "Shipping:"), "$0.00");
		verifyEquals(checkOutPage.getTotalInforByText(driver, "total-info", "Tax:"), "$0.00");
		verifyEquals(checkOutPage.getTotalInforByText(driver, "total-info", "Total:"), "$2,460.00");

		log.info("TC_07_ReOrderProduct - Step 41: Click to 'Confirm' button");
		checkOutPage.clickToNopCommerceButtonByValue(driver, "Confirm");

		log.info("TC_07_ReOrderProduct - Step 42: Verify the Product is ordered successfully");
		verifyEquals(checkOutPage.getCheckOutCompletedPageTitleByClass(driver), "Thank you");
		verifyEquals(checkOutPage.getCheckOutCompletedDetailByClass(driver, "title"), "Your order has been successfully processed!");

		log.info("TC_07_ReOrderProduct - Step 43: Get Order No");
		orderNo = checkOutPage.getCheckOutOrderNo(driver, "details");
		log.info("TC_07_ReOrderProduct - Step 44: Click to Order detail linnk");
		checkOutPage.clickToNopCommerceLinkByClass(driver, "details-link", "Click here for order details.");

		orderDetailPage = PageGeneratorManager.getOrderDetailPage(driver);

		log.info("TC_07_ReOrderProduct - Step 45: Verify Order information are displayed correctly");
		verifyTrue(orderDetailPage.getOrderDetailTextByClass(driver, "order-overview", "order-number").contains(orderNo));
		verifyEquals(orderDetailPage.getOrderDetailTextByClass(driver, "order-overview", "order-number"), "ORDER #" + orderNo);
		verifyEquals(orderDetailPage.getOrderDetailContentByClass(driver, "order-overview", "order-date"), "Order Date: " +getCSTDateTime());
		verifyTrue(orderDetailPage.getOrderDetailContentByClass(driver, "order-overview", "order-status").contains("Pending"));
		verifyEquals(orderDetailPage.getOrderTotalPriceByClass(driver, "order-total"), "$2,460.00");

		log.info("TC_07_ReOrderProduct - Step 46: Verify Billing information / Shipping information are displayed correctly");
		verifyTrue(orderDetailPage.getOrderDetailContentByClass(driver, "billing-info", "email").contains(billEmail));
		verifyTrue(orderDetailPage.getOrderDetailContentByClass(driver, "shipping-info", "email").contains(shipEmail));
		verifyEquals(orderDetailPage.getOrderMethodByClass(driver, "payment-method-info", "payment-method"), "Check / Money Order");
		verifyEquals(orderDetailPage.getOrderMethodByClass(driver, "shipping-method-info", "shipping-method"), "Next Day Air");

		log.info("TC_07_ReOrderProduct - Step 47: Verify Product's detail are displayed successfully");
		verifyEquals(orderDetailPage.getNopCommerceProductInforByColumn(driver, productName4, 1), productCode4);
		verifyEquals(orderDetailPage.getNopCommerceProductNameByText(driver, productName4), productName4);
		verifyEquals(orderDetailPage.getNopCommerceProductInforByColumn(driver, productName4, 3), "$245.00");
		verifyEquals(orderDetailPage.getNopCommerceProductInforByColumn(driver, productName4, 4), "10");
		verifyEquals(orderDetailPage.getNopCommerceProductInforByColumn(driver, productName4, 5), "$2,450.00");
		verifyTrue(orderDetailPage.getSelectedOptionStatus(driver).contains("Yes [+$10.00]"));
		verifyEquals(orderDetailPage.getTotalInforByText(driver, "total-info", "Order Total:"), "$2,460.00");

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
	private MyAccountPageObject myAccountPage;
	private OrderDetailPageObject orderDetailPage;
}
