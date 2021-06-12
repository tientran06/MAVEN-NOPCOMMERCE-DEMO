package com.nopcommerce.others;

import com.nopcommerce.common.Common_01_RegisterUser;
import commons.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.nopcommerce.HomePageObject;
import pageObjects.nopcommerce.LoginPageObject;
import pageObjects.nopcommerce.NoteBooksPageObject;
import pageObjects.nopcommerce.PageGeneratorManager;

public class Others_06_SortAndPaging extends AbstractTest {

	private WebDriver driver;
	private String email, password;
	private LoginPageObject loginPage;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String Url) {
		driver = getBrowserDriver(browserName, Url);
		homePage = PageGeneratorManager.getHomePage(driver);

		email = Common_01_RegisterUser.email;
		password = Common_01_RegisterUser.password;

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
		homePage.clickToNopCommerceSubMenuByText(driver, "Computers ", "Notebooks ");
		noteBooksPage = PageGeneratorManager.getNoteBooksPage(driver);
	}

	@Test()
	public void TC_01_SortWithNameAscending() {
		log.info("TC_01_SortWithNameAscending - Step 01: Select item 'Name: A to Z' from DropDown list");
		noteBooksPage.selectNopCommerceDropdownListByName(driver, "products-orderby", "Name: A to Z");
		noteBooksPage.sleepInSecond(driver, 2);

		log.info("TC_01_SortWithNameAscending - Step 02: Verify Product Names are sorted Ascending");
		verifyTrue(noteBooksPage.isNopCommerceProNameSortedAscending(driver));
	}

	@Test()
	public void TC_02_SortWithNameDescending() {
		log.info("TC_02_SortWithNameDescending - Step 01: Select item 'Name: Z to A' from DropDown list");
		noteBooksPage.selectNopCommerceDropdownListByName(driver, "products-orderby", "Name: Z to A");
		noteBooksPage.sleepInSecond(driver, 2);

		log.info("TC_02_SortWithNameDescending - Step 02:Verify Product Names are sorted Descending");
		verifyTrue(noteBooksPage.isNopCommerceProNameSortedDescending(driver));
	}

	@Test()
	public void TC_03_SortWithPriceLowToHigh() {
		log.info("TC_03_SortWithPriceLowToHigh - Step 01: Select item 'Price: Low to High' from DropDown list");
		noteBooksPage.selectNopCommerceDropdownListByName(driver, "products-orderby", "Price: Low to High");
		noteBooksPage.sleepInSecond(driver, 2);

		log.info("TC_03_SortWithPriceLowToHigh - Step 02: Verify the Product prices are sorted Ascending");
		verifyTrue(noteBooksPage.isNopCommerceProPriceSortedAscending(driver));
	}

	@Test()
	public void TC_04_SortWithPriceHighToLow() {
		log.info("TC_04_SortWithPriceHighToLow - Step 01: Select item 'Price: High to Low' from DropDown List");
		noteBooksPage.selectNopCommerceDropdownListByName(driver, "products-orderby", "Price: High to Low");
		noteBooksPage.sleepInSecond(driver, 2);

		log.info("TC_04_SortWithPriceHighToLow - Step 02: Verify Products Prices are sorted Descending");
		verifyTrue(noteBooksPage.isNopCommerceProPriceSortedDescending(driver));
	}

	@Test()
	public void TC_05_DisplayWith3ProductFor1Page() {
		log.info("TC_05_DisplayWith3ProductFor1Page - Step 01: Select pagezise '3' from Dropdown List");
		noteBooksPage.selectNopCommerceDropdownListByName(driver, "products-pagesize", "3");
		noteBooksPage.sleepInSecond(driver, 2);

		log.info("TC_05_DisplayWith3ProductFor1Page - Step 02: Verify Products are displayed less or equal 3 ");
		verifyTrue(noteBooksPage.isNopCommerceProductCountDisplayedCorrectly(driver, 3));

		log.info("TC_05_DisplayWith3ProductFor1Page - Step 03: Verify paging (Next icon) is displayed on Page number 1");
		verifyEquals(noteBooksPage.getNopCommerceCurrentPage(driver), "1");
		verifyTrue(noteBooksPage.isNopcommercePagingIconDisplayedByText(driver, "Next"));

		log.info("TC_05_DisplayWith3ProductFor1Page - Step 04: Click to page number 2");
		noteBooksPage.clickToNopCommercePageIconByText(driver, "2");
		noteBooksPage.sleepInSecond(driver, 2);

		log.info("TC_05_DisplayWith3ProductFor1Page - Step 05: Verify page (Next icon) is undisplayed and (Previous icon) is displayed on Page number 2");
		verifyEquals(noteBooksPage.getNopCommerceCurrentPage(driver), "2");
		verifyTrue(noteBooksPage.isNopcommercePagingIconUnDisplayedByText(driver, "Next"));
		verifyTrue(noteBooksPage.isNopcommercePagingIconDisplayedByText(driver, "Previous"));
	}

	@Test()
	public void TC_06_DisplayWith6ProductFor1Page() {

		log.info("TC_06_DisplayWith6ProductFor1Page - Step 01: Select pagezise '6' from Dropdown List");
		noteBooksPage.selectNopCommerceDropdownListByName(driver, "products-pagesize", "6");
		noteBooksPage.sleepInSecond(driver, 2);

		log.info("TC_06_DisplayWith6ProductFor1Page - Step 02: Verify Products are displayed less or equal 6");
		verifyTrue(noteBooksPage.isNopCommerceProductCountDisplayedCorrectly(driver, 6));

		log.info("TC_06_DisplayWith6ProductFor1Page - Step 03: Verify Page is not displayed");
		verifyTrue(noteBooksPage.isNopcommercePagingIconUnDisplayed(driver));

	}

	@Test()
	public void TC_07_DisplayWith9ProductFor1Page() {
		log.info("TC_07_DisplayWith9ProductFor1Page - Step 01: Select pagezise '9' from Dropdown List");
		noteBooksPage.selectNopCommerceDropdownListByName(driver, "products-pagesize", "9");
		noteBooksPage.sleepInSecond(driver, 2);

		log.info("TC_07_DisplayWith9ProductFor1Page - Step 02: Verify Products are displayed less or equal 9");
		verifyTrue(noteBooksPage.isNopCommerceProductCountDisplayedCorrectly(driver, 9));

		log.info("TC_07_DisplayWith9ProductFor1Page - Step 03: Verify Page is not displayed");
		verifyTrue(noteBooksPage.isNopcommercePagingIconUnDisplayed(driver));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	private HomePageObject homePage;
	private NoteBooksPageObject noteBooksPage;
}
