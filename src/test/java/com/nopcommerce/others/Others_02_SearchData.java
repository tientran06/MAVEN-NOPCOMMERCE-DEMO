package com.nopcommerce.others;

import com.nopcommerce.common.Common_01_RegisterUser;
import commons.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.nopcommerce.HomePageObject;
import pageObjects.nopcommerce.LoginPageObject;
import pageObjects.nopcommerce.PageGeneratorManager;
import pageObjects.nopcommerce.SearchPageObject;

public class Others_02_SearchData extends AbstractTest {

	private WebDriver driver;
	private String email, password;
	private String comparativeProduct, productName, productName1, category, incorrectManufacturer, correctManufacturer;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private SearchPageObject searchPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		email = Common_01_RegisterUser.email;
		password = Common_01_RegisterUser.password;

		comparativeProduct = "Lenovo";
		productName = "Thinkpad X1 Carbon";
		productName1 = "Apple MacBook Pro";
		category = "Computers";
		incorrectManufacturer = "HP";
		correctManufacturer = "Apple";

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
		homePage.clickToNopCommerceFooterLinkByName(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
	}

	@Test()
	public void TC_01_SearchWithEmptyData() {
		log.info("TC_01_SearchWithEmptyData - Step 01: Input Search text with empty data");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", "");

		log.info("TC_01_SearchWithEmptyData - Step 02: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_01_SearchWithEmptyData - Step 03: Verify Error message displays");
		verifyEquals(searchPage.getNopCommerceTextByClass(driver, "warning"), "Search term minimum length is 3 characters");

	}

	@Test()
	public void TC_02_SearchWithNotExistData() {
		log.info("TC_02_SearchWithNotExistData - Step 01: Input Search text with not exist data");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", "Macbook Pro 2050");

		log.info("TC_02_SearchWithNotExistData - Step 02: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_02_SearchWithNotExistData - Step 03: Verify displayed result - No result");
		verifyEquals(searchPage.getNopCommerceTextByClass(driver, "no-result"), "No products were found that matched your criteria.");

	}

	@Test()
	public void TC_03_SearchWithProductNameRelative() {
		log.info("TC_03_SearchWithProductNameRelative - Step 01: Input Search text with comparative Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", comparativeProduct);

		log.info("TC_03_SearchWithProductNameRelative - Step 02: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_03_SearchWithProductNameRelative - Step 03: Verify displayed result - 2 related Products");
		log.info("Total product is found: ---------------" + searchPage.getNopCommerceQtyProductByText(driver, "Lenovo"));
		verifyEquals(searchPage.getNopCommerceQtyProductByText(driver, comparativeProduct), "2");

	}

	@Test()
	public void TC_04_SearchWithProductNameAbsolutely() {
		log.info("TC_04_SearchWithProductNameAbsolutely - Step 01: Input Search text with absolute Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", productName);

		log.info("TC_04_SearchWithProductNameAbsolutely - Step 02: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_04_SearchWithProductNameAbsolutely - Step 03: Verify displayed result - 1 Product");
		log.info("Total product is found: ---------------" + searchPage.getNopCommerceQtyProductByText(driver, productName));
		verifyEquals(searchPage.getNopCommerceQtyProductByText(driver, productName), "1");
	}

	@Test()
	public void TC_05_AdvancedSearchWithParentCategories() {

		log.info("TC_05_AdvancedSearchWithParentCategories - Step 01: Input Search text with Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", productName1);

		log.info("TC_05_AdvancedSearchWithParentCategories - Step 02: Check on Advanced Search and select option Category");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "advs");
		searchPage.selectNopCommerceDropdownListByName(driver, "cid", category);

		log.info("TC_05_AdvancedSearchWithParentCategories - Step 03: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_05_AdvancedSearchWithParentCategories - Step 04: Verify displayed result - No result");
		verifyEquals(searchPage.getNopCommerceTextByClass(driver, "no-result"), "No products were found that matched your criteria.");
	}

	@Test()
	public void TC_06_AdvancedSearchWithSubCategories() {
		log.info("TC_06_AdvancedSearchWithSubCategories - Step 01: Input Search text with Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", productName1);

		log.info("TC_06_AdvancedSearchWithSubCategories - Step 02: Check on 'Advanced Search' and select option 'Category'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "advs");
		searchPage.selectNopCommerceDropdownListByName(driver, "cid", category);

		log.info("TC_06_AdvancedSearchWithSubCategories - Step 03: Check on 'Automatically search sub categories'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "isc");

		log.info("TC_06_AdvancedSearchWithSubCategories - Step 04: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_06_AdvancedSearchWithSubCategories - Step 05: Verify displayed result - 1 Product");
		log.info("Total product is found: ---------------" + searchPage.getNopCommerceQtyProductByText(driver, productName1));
		verifyEquals(searchPage.getNopCommerceQtyProductByText(driver, productName1), "1");
	}

	@Test()
	public void TC_07_AdvancedSearchWithIncorrectManufacturer() {
		log.info("TC_07_AdvancedSearchWithIncorrectManufacturer - Step 01: Input Search text with Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", productName1);

		log.info("TC_07_AdvancedSearchWithIncorrectManufacturer - Step 02: Check on 'Advanced Search' and select option 'Category'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "advs");
		searchPage.selectNopCommerceDropdownListByName(driver, "cid", category);

		log.info("TC_07_AdvancedSearchWithIncorrectManufacturer - Step 03: Check on 'Automatically search sub categories'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "isc");

		log.info("TC_07_AdvancedSearchWithIncorrectManufacturer - Step 04: Select incorrect 'Manufacturer'");
		searchPage.selectNopCommerceDropdownListByName(driver, "mid", incorrectManufacturer);

		log.info("TC_07_AdvancedSearchWithIncorrectManufacturer - Step 05: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_07_AdvancedSearchWithIncorrectManufacturer - Step 06: Verify displayed result - No result");
		verifyEquals(searchPage.getNopCommerceTextByClass(driver, "no-result"), "No products were found that matched your criteria.");
	}

	@Test()
	public void TC_08_AdvancedSearchWithCorrectManufacturer() {
		log.info("TC_08_AdvancedSearchWithCorrectManufacturer - Step 01: Input Search text with Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", productName1);

		log.info("TC_08_AdvancedSearchWithCorrectManufacturer - Step 02: Check on 'Advanced Search' and select option 'Category'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "advs");
		searchPage.selectNopCommerceDropdownListByName(driver, "cid", category);

		log.info("TC_08_AdvancedSearchWithCorrectManufacturer - Step 03: Check on 'Automatically search sub categories'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "isc");

		log.info("TC_08_AdvancedSearchWithCorrectManufacturer - Step 04: Select incorrect 'Manufacturer'");
		searchPage.selectNopCommerceDropdownListByName(driver, "mid", correctManufacturer);

		log.info("TC_08_AdvancedSearchWithCorrectManufacturer - Step 05: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_08_AdvancedSearchWithCorrectManufacturer - Step 06: Verify displayed result - 1 Product");
		log.info("Total product is found: ---------------" + searchPage.getNopCommerceQtyProductByText(driver, productName1));
		verifyEquals(searchPage.getNopCommerceQtyProductByText(driver, productName1), "1");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
