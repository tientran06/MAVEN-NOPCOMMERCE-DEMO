package com.nopcommerce.others;

import com.nopcommerce.common.Common_01_RegisterUser;
import commons.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.nopcommerce.*;

public class Others_01_MyAccount extends AbstractTest {

	private WebDriver driver;
	private String email, password;
	private String editFirstName, editLastName, editDay, editMonth, editYear, editCompany, editEmail, addFirstName, addLastName, addEmail, addCompany, addCountry, addState, addCity, addAddress1, addAddress2, addZipCode, addPhone, addFax, newPassword;
	private String productReview, titleReview, contentsReview;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;
	private SoftwarePageObject softwarePage;
	private ProductPageObject productPage;
	private ProductReviewPageObject productReviewPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		email = Common_01_RegisterUser.email;
		password = Common_01_RegisterUser.password;
		newPassword = "454546";

		editFirstName = "Automation";
		editLastName = "Selenium";
		editEmail = "Seleniumfc" + randomNumber() + "@gmail.com";
		editCompany = "Automation Selenium";
		editDay = "25";
		editMonth = "May";
		editYear = "1990";

		addFirstName = "Selenium";
		addLastName = "Automation";
		addEmail = "automation_fc" + randomNumber() + "@hot.com";
		addCompany = "Cyberlogitec";
		addCountry = "Viet Nam";
		addState = "Other";
		addCity = "Ho Chi Minh";
		addAddress1 = "123 Nguyen Duc Thuan";
		addAddress2 = "12 Ngo Be";
		addZipCode = "70000";
		addPhone = "09754382222";
		addFax = "028433335444";

		productReview = "Adobe Photoshop CS4";
		titleReview = "Review about Product";
		contentsReview = "This product is good to use. I will leave more information after using it";

		log.info("Pre-conditions: Login to the System and go to My Account page");

		homePage.clickToNopCommerceHeaderLinkByText(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		homePage = PageGeneratorManager.getHomePage(driver);

	}

	@BeforeMethod
	public void beforeMethod() {
		homePage.clickToNopCommerceHeaderLinkByText(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);
	}

	@Test
	public void TC_01_UpdateCustomerInfor() {
		log.info("TC_01_UpdateCustomerInfor - Step 01: Edit data");
		myAccountPage.clickToNopCommerceListBoxMenuByName(driver, "Customer info");
		myAccountPage.clickToRadioButtonByID(driver, "gender-female");
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "FirstName", editFirstName);
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "LastName", editLastName);
		myAccountPage.selectNopCommerceDropdownListByName(driver, "DateOfBirthDay", editDay);
		myAccountPage.selectNopCommerceDropdownListByName(driver, "DateOfBirthMonth", editMonth);
		myAccountPage.selectNopCommerceDropdownListByName(driver, "DateOfBirthYear", editYear);
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "Email", editEmail);
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "Company", editCompany);

		log.info("TC_01_UpdateCustomerInfor - Step 02: Click Save button");
		myAccountPage.clickToNopCommerceButtonByValue(driver, "Save");

		log.info("TC_01_UpdateCustomerInfor - Step 03: Verify result after editing data");
		verifyTrue(myAccountPage.isNopCommerceRadioButtonSelectedByID(driver, "gender-female"));
		verifyEquals(myAccountPage.getNopCommerceAttributeValueByID(driver, "FirstName"), editFirstName);
		verifyEquals(myAccountPage.getNopCommerceAttributeValueByID(driver, "LastName"), editLastName);

		verifyTrue(myAccountPage.isNopCommerceDropdownListSelectedByText(driver, "DateOfBirthDay", editDay));
		verifyTrue(myAccountPage.isNopCommerceDropdownListSelectedByText(driver, "DateOfBirthMonth", editMonth));
		verifyTrue(myAccountPage.isNopCommerceDropdownListSelectedByText(driver, "DateOfBirthYear", editYear));

		verifyEquals(myAccountPage.getNopCommerceAttributeValueByID(driver, "Email"), editEmail);
		verifyEquals(myAccountPage.getNopCommerceAttributeValueByID(driver, "Company"), editCompany);
	}

	@Test(dependsOnMethods = "TC_01_UpdateCustomerInfor")
	public void TC_02_AddAddress() {
		log.info("TC_02_AddAddress - Step 01: Click to 'Addresses' List menu");
		myAccountPage.clickToNopCommerceListBoxMenuByName(driver, "Addresses");

		log.info("TC_02_AddAddress - Step 02: Click to 'Add new' button and input Address infor");
		myAccountPage.clickToNopCommerceSubButtonByValue(driver, "Add new");
		
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "Address_FirstName", addFirstName);
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "Address_LastName", addLastName);
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "Address_Email", addEmail);
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "Address_Company", addCompany);

		myAccountPage.selectNopCommerceDropdownListByName(driver, "Address.CountryId", addCountry);
		myAccountPage.selectNopCommerceDropdownListByName(driver, "Address.StateProvinceId", addState);

		myAccountPage.inputToNopCommerceTextBoxByID(driver, "Address_City", addCity);
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "Address_Address1", addAddress1);
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "Address_Address2", addAddress2);
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "Address_ZipPostalCode", addZipCode);
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "Address_PhoneNumber", addPhone);
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "Address_FaxNumber", addFax);

		log.info("TC_02_AddAddress - Step 03: Click Save button");
		myAccountPage.clickToNopCommerceButtonByValue(driver,  "Save");

		log.info("TC_02_AddAddress - Step 04: Verify Address infor are saved successfully");
		verifyTrue(myAccountPage.isAddressAccountInfoDisplayedByClass("name", addFirstName + " " + addLastName));
		verifyTrue(myAccountPage.isAddressAccountInfoDisplayedByClass("email", addEmail));
		verifyTrue(myAccountPage.isAddressAccountInfoDisplayedByClass("phone", addPhone));
		verifyTrue(myAccountPage.isAddressAccountInfoDisplayedByClass("fax", addFax));

		verifyEquals(myAccountPage.getAddressAccountInfoByClass("company"), addCompany);
		verifyEquals(myAccountPage.getAddressAccountInfoByClass("address1"), addAddress1);
		verifyEquals(myAccountPage.getAddressAccountInfoByClass("address2"), addAddress2);
		verifyTrue(myAccountPage.getAddressAccountInfoByClass("city-state-zip").contains(addCity));
		verifyTrue(myAccountPage.getAddressAccountInfoByClass("city-state-zip").contains(addZipCode));
		verifyEquals(myAccountPage.getAddressAccountInfoByClass("country"), addCountry);
	}

	@Test(dependsOnMethods = "TC_02_AddAddress")
	public void TC_03_ChangePassword() {
		log.info("TC_03_ChangePassword - Step 01: Click to 'Change password' List menu");
		myAccountPage.clickToNopCommerceListBoxMenuByName(driver, "Change password");

		log.info("TC_03_ChangePassword - Step 02: Input old password and new password info");
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "OldPassword", password);
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "NewPassword", newPassword);
		myAccountPage.inputToNopCommerceTextBoxByID(driver, "ConfirmNewPassword", newPassword);

		log.info("TC_03_ChangePassword - Step 03: Click to 'Change password' button");
		myAccountPage.clickToNopCommerceButtonByValue(driver, "Change password");

		log.info("TC_03_ChangePassword - Step 04: Verify message displayed after changing password successfully");
		verifyEquals(myAccountPage.getNopCommerceNoticeByClass(driver, "content"), "Password was changed");
		
		myAccountPage.clickToNopCommerceButtonByClass(driver, "close");

		log.info("TC_03_ChangePassword - Step 05: Click Log out button");
		myAccountPage.clickToNopCommerceHeaderLinkByText(driver, "Log out");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("TC_03_ChangePassword - Step 06: Log in into the system with old password and verify Error message displays");
		homePage.clickToNopCommerceHeaderLinkByText(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToEmailTextbox(editEmail);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();

		verifyTrue(loginPage.isResultErrorMsgByClassDisplayed("message-error", "Login was unsuccessful. Please correct the errors and try again."));
		verifyTrue(loginPage.isResultErrorMsgByClassDisplayed("message-error", "The credentials provided are incorrect"));

		log.info("TC_03_ChangePassword - Step 07: Log in into the system with password which is change and verify log in successfully");
		loginPage.clickToNopCommerceHeaderLinkByText(driver, "Log in");
		loginPage.inputToEmailTextbox(editEmail);
		loginPage.inputToPasswordTextbox(newPassword);
		loginPage.clickToLoginButton();
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test(dependsOnMethods = "TC_03_ChangePassword")
	public void TC_04_AddReviewProduct() {
		log.info("TC_04_AddReviewProduct - Step 01: Click to Sub Menu");
		myAccountPage.clickToNopCommerceSubMenuByText(driver, "Computers ", "Software ");
		softwarePage = PageGeneratorManager.getSoftwarePage(driver);

		log.info("TC_04_AddReviewProduct - Step 02: Click to Product link");
		softwarePage.clickToNopCommerceLinkByClass(driver, "product-item", productReview);
		productPage = PageGeneratorManager.getProductPage(driver);

		log.info("TC_04_AddReviewProduct - Step 03: Click to 'Add your review' link");
		productPage.clickToNopCommerceLinkByClass(driver, "product-review", "Add your review");
		productReviewPage = PageGeneratorManager.getProductReviewPage(driver);

		log.info("TC_04_AddReviewProduct - Step 04: Leave a review about Product");
		productReviewPage.inputToNopCommerceTextBoxByID(driver, "AddProductReview_Title", titleReview);
		productReviewPage.inputToNopCommerceTextAreaByClass(driver, "review-text", contentsReview);
		productReviewPage.clickToRadioButtonByID(driver, "addproductrating_4");

		log.info("TC_04_AddReviewProduct - Step 05: Click to 'Submit review' button");
		productReviewPage.clickToNopCommerceButtonByValue(driver, "Submit review");

		log.info("TC_04_AddReviewProduct - Step 06: Verify Submit review successfully");
		verifyEquals(productReviewPage.getNopCommerceTextByClass(driver, "result"), "Product review is successfully added.");

		log.info("TC_04_AddReviewProduct - Step 07: Click to 'My account' link");
		productReviewPage.clickToNopCommerceHeaderLinkByText(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);

		log.info("TC_04_AddReviewProduct - Step 08: Click to 'My product reviews' List menu");
		myAccountPage.clickToNopCommerceListBoxMenuByName(driver, "My product reviews");

		log.info("TC_04_AddReviewProduct - Step 09: Verify the Review is added into 'My product reviews' with detail");
		verifyTrue(myAccountPage.getReviewInfoByClass("review-title", titleReview).contains(titleReview));
		verifyTrue(myAccountPage.getReviewInfoByClass("review-text", contentsReview).contains(contentsReview));
		verifyTrue(myAccountPage.getReviewInfoByClass("review-info", productReview).contains(productReview));
		// verifyTrue(myAccountPage.getReviewInfoByClass("review-info", "Date").contains("Date"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
