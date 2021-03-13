package com.nopcommerce.others;

import com.nopcommerce.common.Common_01_RegisterUser;
import commons.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.nopcommerce.*;

public class Others_03_WishlistAndCompare extends AbstractTest {

	private WebDriver driver;
	private String email, password, firstName, lastName;
	private String productName, productName1, compareProduct1, compareProduct2, viewedProduct1, viewedProduct2, viewedProduct3, viewedProduct4, viewedProduct5;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private NoteBooksPageObject noteBooksPage;
	private WishListPageObject wishListPage;
	private ShoppingCartPageObject shoppingCartPage;
	private CellPhonePageObject cellPhonePage;
	private CameraAndPhotoPageObject cameraAndPhotoPage;
	private CompareProductsListPageObject compareProductsListPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		homePage = PageGeneratorManager.getHomePage(driver);

		email = Common_01_RegisterUser.email;
		password = Common_01_RegisterUser.password;
		firstName = Common_01_RegisterUser.firstName;
		lastName = Common_01_RegisterUser.lastName;

		productName = "Asus N551JK-XO076H Laptop";
		productName1 = "HTC One M8 Android L 5.0 Lollipop";
		compareProduct1 = "Nikon D5500 DSLR";
		compareProduct2 = "Leica T Mirrorless Digital Camera";
		viewedProduct1 = "Asus N551JK-XO076H Laptop";
		viewedProduct2 = "HP Envy 6-1180ca 15.6-Inch Sleekbook";
		viewedProduct3 = "HP Spectre XT Pro UltraBook";
		viewedProduct4 = "Lenovo Thinkpad X1 Carbon Laptop";
		viewedProduct5 = "Samsung Series 9 NP900X4C Premium Ultrabook";

		log.info("Pre-conditions: Login to the System and go to My Account page");

		homePage.clickToNopCommerceHeaderLinkByText(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();

		verifyTrue(homePage.isMyAccountLinkDisplayed());
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		homePage.clickToNopCommerceHomePage(driver);
	}

	@Test()
	public void TC_01_AddToWishlist() {
		log.info("TC_01_AddToWishlist - Step 01: Click to 'Sub Menu'");
		homePage.clickToNopCommerceSubMenuByText(driver, "Computers ", "Notebooks ");
		noteBooksPage = PageGeneratorManager.getNoteBooksPage(driver);


		log.info("TC_01_AddToWishlist - Step 02: Click to 'Add to wishlist' button");
		noteBooksPage.clickToNopCommerceSubButtonByProductName(driver, productName,"Add to wishlist");

		log.info("TC_01_AddToWishlist - Step 03: Verify successful Message displays");
		verifyTrue(noteBooksPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "wishlist"));

		log.info("TC_01_AddToWishlist - Step 04: Go to WishList Page");
		noteBooksPage.clickToNopCommerceLinkByClass(driver, "bar-notification", "wishlist");
		wishListPage = PageGeneratorManager.getWishListPage(driver);

		log.info("TC_01_AddToWishlist - Step 05: Verify Product is added into WishList page");
		verifyEquals(wishListPage.getNopCommerceProductNameByText(driver, productName), productName);

		log.info("TC_01_AddToWishlist - Step 06: Click to WishList share link");
		wishListPage.clickToWishListLink();

		log.info("TC_01_AddToWishlist - Step 07: Verify Product is in User's WishList");
		verifyEquals(wishListPage.getWishListTitle(), "Wishlist of " + firstName + " " + lastName);
		verifyEquals(wishListPage.getNopCommerceProductNameByText(driver, productName), productName);
	}

	@Test(dependsOnMethods = "TC_01_AddToWishlist")
	public void TC_02_AddProductToCartFromWishlistPage() {
		log.info("TC_02_AddProductToCartFromWishlistPage - Step 01: Click to 'Wishlist' Link");
		homePage.clickToNopCommerceHeaderOtherLinkByText(driver, "Wishlist");

		log.info("TC_02_AddProductToCartFromWishlistPage - Step 02: Check on checkbox 'Add to cart'");
		wishListPage.checkOnCheckBoxByName("addtocart");

		log.info("TC_02_AddProductToCartFromWishlistPage - Step 03: Click on 'Add to cart' button");
		wishListPage.clickToNopCommerceButtonByValue(driver, "Add to cart");

		log.info("TC_02_AddProductToCartFromWishlistPage - Step 04: Verify Product add to Shopping Cart successfully");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		verifyEquals(shoppingCartPage.getNopCommerceProductNameByText(driver, productName), productName);

		log.info("TC_02_AddProductToCartFromWishlistPage - Step 05: Click to 'WishList' link again");
		shoppingCartPage.clickToNopCommerceHeaderOtherLinkByText(driver, "Wishlist");
		wishListPage = PageGeneratorManager.getWishListPage(driver);

		log.info("TC_02_AddProductToCartFromWishlistPage - Step 06: Verify Product is removed from WishList page");
		verifyTrue(wishListPage.isNopCommerceProductUndisplayed(driver, productName));
	}

	@Test(dependsOnMethods = "TC_02_AddProductToCartFromWishlistPage")
	public void TC_03_RemoveProductInWishlistPage() {
		log.info("TC_03_RemoveProductInWishlistPage - Step 01: Click to 'Sub Menu'");
		homePage.clickToNopCommerceSubMenuByText(driver, "Electronics ", "Cell phones ");
		cellPhonePage = PageGeneratorManager.getCellPhonePage(driver);


		log.info("TC_03_RemoveProductInWishlistPage - Step 02: Click to 'Add to wishlist' button");
		cellPhonePage.clickToNopCommerceSubButtonByProductName(driver, productName1,"Add to wishlist");

		log.info("TC_03_RemoveProductInWishlistPage - Step 03: Verify successful Message displays");
		verifyTrue(cellPhonePage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "wishlist"));

		log.info("TC_03_RemoveProductInWishlistPage - Step 04: Go to WishList Page");
		cellPhonePage.clickToNopCommerceLinkByClass(driver, "bar-notification", "wishlist");

		log.info("TC_03_RemoveProductInWishlistPage - Step 05: Verify Product is added into WishList page");
		wishListPage = PageGeneratorManager.getWishListPage(driver);
		verifyEquals(wishListPage.getNopCommerceProductNameByText(driver, productName1), productName1);


		log.info("TC_03_RemoveProductInWishlistPage - Step 06: Remove product from Wishlist");
		wishListPage.clickToNopCommerceButtonIconByProductName(driver, productName1, "remove-from-cart");

		log.info("TC_03_RemoveProductInWishlistPage - Step 07: Verify Product is removed from WishList page");
		verifyEquals(wishListPage.getWishListWarningMsgByClass("no-data"), "The wishlist is empty!");
		verifyTrue(wishListPage.isNopCommerceProductUndisplayed(driver, productName1));
	}

	@Test(dependsOnMethods = "TC_03_RemoveProductInWishlistPage")
	public void TC_04_AddProductToCompareAndClearList() {
		log.info("TC_04_AddProductToCompareAndClearList - Step 01: Click to 'Sub Menu'");
		homePage.clickToNopCommerceSubMenuByText(driver, "Electronics ", "Camera & photo ");
		cameraAndPhotoPage = PageGeneratorManager.getCameraAndPhotoPage(driver);

		log.info("TC_04_AddProductToCompareAndClearList - Step 02: Add to compare list - Product 1 and verify the product is added successfully");
		cameraAndPhotoPage.clickToNopCommerceSubButtonByProductName(driver, compareProduct1 ,"Add to compare list");
		verifyTrue(cameraAndPhotoPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "product comparison"));

		log.info("TC_04_AddProductToCompareAndClearList - Step 03: Add to compare list - Product 2 and verify the product is added successfully");
		cameraAndPhotoPage.clickToNopCommerceSubButtonByProductName(driver, compareProduct2 ,"Add to compare list");
		verifyTrue(cameraAndPhotoPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "product comparison"));

		log.info("TC_04_AddProductToCompareAndClearList - Step 04: Go to Compare products list");
		cameraAndPhotoPage.clickToNopCommerceFooterLinkByName(driver, "Compare products list");
		compareProductsListPage = PageGeneratorManager.getCompareProductsListPage(driver);

		log.info("TC_04_AddProductToCompareAndClearList - Step 05: Verify Products information (Name and Price) in Compare products list");
		verifyEquals(compareProductsListPage.getProductInforByRow(compareProduct1, "3"), compareProduct1);
		verifyEquals(compareProductsListPage.getProductInforByRow(compareProduct2, "3"), compareProduct2);

		verifyTrue(compareProductsListPage.getProductInforByRow(compareProduct1, "4").contains("$630.00"));
		verifyTrue(compareProductsListPage.getProductInforByRow(compareProduct2, "4").contains("$530.00"));

		log.info("TC_04_AddProductToCompareAndClearList - Step 06: Click to 'Clear list' button");
		compareProductsListPage.clickToNopCommerceLinkByClass(driver, "page-body", "Clear list");

		log.info("TC_04_AddProductToCompareAndClearList - Step 07: Verify Message displays and the products are clear in Compare List");
		verifyEquals(compareProductsListPage.getNopCommerceTextByClass(driver, "no-data"), "You have no items to compare.");
		verifyTrue(compareProductsListPage.isCompareProductInforUnDisplayedByText("Name", compareProduct1));
		verifyTrue(compareProductsListPage.isCompareProductInforUnDisplayedByText("Name", compareProduct2));
	}

	@Test(dependsOnMethods = "TC_04_AddProductToCompareAndClearList")
	public void TC_05_RecentlyViewedProducts() {

		log.info("TC_05_RecentlyViewedProducts - Step 01: Click to 'Sub Menu'");
		homePage.clickToNopCommerceSubMenuByText(driver, "Computers ", "Notebooks ");
		noteBooksPage = PageGeneratorManager.getNoteBooksPage(driver);

		log.info("TC_05_RecentlyViewedProducts - Step 02: View detail of 5 products");
		noteBooksPage.clickToNopCommerceLinkByClass(driver, "product-item", viewedProduct1);
		noteBooksPage.backToNopCommercePreviousPage(driver);
		noteBooksPage.clickToNopCommerceLinkByClass(driver, "product-item", viewedProduct2);
		noteBooksPage.backToNopCommercePreviousPage(driver);
		noteBooksPage.clickToNopCommerceLinkByClass(driver, "product-item", viewedProduct3);
		noteBooksPage.backToNopCommercePreviousPage(driver);
		noteBooksPage.clickToNopCommerceLinkByClass(driver, "product-item", viewedProduct4);
		noteBooksPage.backToNopCommercePreviousPage(driver);
		noteBooksPage.clickToNopCommerceLinkByClass(driver, "product-item", viewedProduct5);
		noteBooksPage.backToNopCommercePreviousPage(driver);

		log.info("TC_05_RecentlyViewedProducts - Step 03: Go to 'Recently viewed products' link");
		noteBooksPage.clickToNopCommerceFooterLinkByName(driver, "Recently viewed products");

		log.info("TC_05_RecentlyViewedProducts - Step 04: Verify only last 3 products display");
		verifyTrue(noteBooksPage.isNopCommerceLinkDisplayedByClass(driver, "product-item", viewedProduct3));
		verifyTrue(noteBooksPage.isNopCommerceLinkDisplayedByClass(driver, "product-item", viewedProduct4));
		verifyTrue(noteBooksPage.isNopCommerceLinkDisplayedByClass(driver, "product-item", viewedProduct5));
		verifyTrue(noteBooksPage.isNopCommerceLinkUnDisplayedByClass(driver, "product-item", viewedProduct1));
		verifyTrue(noteBooksPage.isNopCommerceLinkUnDisplayedByClass(driver, "product-item", viewedProduct2));

	}

	@Test(dependsOnMethods = "TC_05_RecentlyViewedProducts")
	public void TC_06_AddProductToCompareAndRemove() {
		log.info("TC_06_AddProductToCompareAndRemove - Step 01: Click to 'Sub Menu'");
		homePage.clickToNopCommerceSubMenuByText(driver, "Computers ", "Notebooks ");
		noteBooksPage = PageGeneratorManager.getNoteBooksPage(driver);

		log.info("TC_06_AddProductToCompareAndRemove - Step 02: Add to compare list - Product 1 and verify the product is added successfully");
		noteBooksPage.clickToNopCommerceSubButtonByProductName(driver, viewedProduct1, "Add to compare list");
		verifyTrue(noteBooksPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "product comparison"));

		log.info("TC_06_AddProductToCompareAndRemove - Step 03: Add to compare list - Product 2 and verify the product is added successfully");
		noteBooksPage.clickToNopCommerceSubButtonByProductName(driver, viewedProduct2, "Add to compare list");
		verifyTrue(noteBooksPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "product comparison"));

		log.info("TC_06_AddProductToCompareAndRemove - Step 04: Add to compare list - Product 3 and verify the product is added successfully");
		noteBooksPage.clickToNopCommerceSubButtonByProductName(driver, viewedProduct3, "Add to compare list");
		verifyTrue(noteBooksPage.isNopCommerceBarNotificationDisplayed(driver, "The product has been added to your ", "product comparison"));

		log.info("TC_06_AddProductToCompareAndRemove - Step 05: Go to Compare products list");
		noteBooksPage.clickToNopCommerceFooterLinkByName(driver, "Compare products list");
		compareProductsListPage = PageGeneratorManager.getCompareProductsListPage(driver);

		log.info("TC_06_AddProductToCompareAndRemove - Step 06: Verify Products information in Compare products list");
		verifyEquals(compareProductsListPage.getProductInforByRow(viewedProduct1, "3"), viewedProduct1);
		verifyEquals(compareProductsListPage.getProductInforByRow(viewedProduct2, "3"), viewedProduct2);
		noteBooksPage.sleepInSecond(driver, 1);
		verifyEquals(compareProductsListPage.getProductInforByRow(viewedProduct3, "3"), viewedProduct3);

		log.info("TC_06_AddProductToCompareAndRemove - Step 07: Click to 'Remove' button of 3 products");
		compareProductsListPage.clickToButtonByColumnAndRow(viewedProduct1, "1");
		compareProductsListPage.clickToButtonByColumnAndRow(viewedProduct2, "1");
		compareProductsListPage.clickToButtonByColumnAndRow(viewedProduct3, "1");

		log.info("TC_06_AddProductToCompareAndRemove - Step 08: Verify Message displays and the products are clear in Compare List");
		verifyEquals(compareProductsListPage.getNopCommerceTextByClass(driver, "no-data"), "You have no items to compare.");
		verifyTrue(compareProductsListPage.isCompareProductInforUnDisplayedByText("Name", viewedProduct1));
		verifyTrue(compareProductsListPage.isCompareProductInforUnDisplayedByText("Name", viewedProduct2));
		verifyTrue(compareProductsListPage.isCompareProductInforUnDisplayedByText("Name", viewedProduct3));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
