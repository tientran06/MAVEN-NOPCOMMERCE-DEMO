package com.nopcommerce.login;

import com.nopcommerce.common.Common_01_RegisterUser;
import commons.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.nopcommerce.HomePageObject;
import pageObjects.nopcommerce.PageGeneratorManager;
import pageObjects.nopcommerce.RegisterPageObject;

public class Login_01_Register extends AbstractTest {

	private WebDriver driver;
	private String firstName, lastName, day, month, year, email, company, passWord, confirmPassWord, existEmail;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		firstName = "John";
		lastName = "Canadi";
		email = "automationFC" + randomNumber() + "@gmail.com";
		company = "Automation Selenium";
		passWord = "123456";
		confirmPassWord = "123456";
		day = "8";
		month = "July";
		year = "1980";
		existEmail = Common_01_RegisterUser.email;

	}

	@BeforeMethod
	public void beforeMethod() {
		registerPage = homePage.clickToRegisterLink();
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		log.info("TC_01_RegisterWithEmptyData - Step 01: Input value");
		registerPage.inputToFirstNameTextbox("");
		registerPage.inputToLastNameTextbox("");
		registerPage.selectDayDropdown("Day");
		registerPage.selectMonthDropdown("Month");
		registerPage.selectYearDropdown("Year");
		registerPage.inputToEmailTextbox("");
		registerPage.inputToCompanyTextbox("");
		registerPage.inputToPasswordTextbox("");
		registerPage.inputToConfirmPasswordTextbox("");

		log.info("TC_01_RegisterWithEmptyData - Step 02: Click to 'Register' button");
		registerPage.clickToSubmitButton();

		log.info("TC_01_RegisterWithEmptyData - Step 03: Verify validation error message displays");
		verifyTrue(registerPage.isValidationMsgByIDDisplayed("FirstName-error", "First name is required."));
		verifyTrue(registerPage.isValidationMsgByIDDisplayed("LastName-error", "Last name is required."));
		verifyTrue(registerPage.isValidationMsgByIDDisplayed("Email-error", "Email is required."));
		verifyTrue(registerPage.isValidationMsgByIDDisplayed("Password-error", "Password is required."));
		verifyTrue(registerPage.isValidationMsgByIDDisplayed("ConfirmPassword-error", "Password is required."));

	}

	@Test()
	public void TC_02_RegisterWithInvalidEmail() {

		log.info("TC_02_RegisterWithInvalidEmail - Step 01: Input value");
		registerPage.clickToGenderRadioByID("gender-female");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.selectDayDropdown(day);
		registerPage.selectMonthDropdown(month);
		registerPage.selectYearDropdown(year);
		registerPage.inputToEmailTextbox("automation.com");
		registerPage.inputToCompanyTextbox(company);
		registerPage.inputToPasswordTextbox(passWord);
		registerPage.inputToConfirmPasswordTextbox(confirmPassWord);

		log.info("TC_02_RegisterWithInvalidEmail - Step 02: Click to 'Register' button");
		registerPage.clickToSubmitButton();

		log.info("TC_02_RegisterWithInvalidEmail - Step 03: Verify validation error message displays");
		verifyTrue(registerPage.isValidationMsgByIDDisplayed("Email-error", "Wrong email"));

	}

	@Test()
	public void TC_03_RegisterWithExistEmail() {

		log.info("TC_03_RegisterWithExistEmail - Step 01: Input value");
		registerPage.clickToGenderRadioByID("gender-male");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.selectDayDropdown(day);
		registerPage.selectMonthDropdown(month);
		registerPage.selectYearDropdown(year);
		registerPage.inputToEmailTextbox(existEmail);
		registerPage.inputToCompanyTextbox(company);
		registerPage.inputToPasswordTextbox(passWord);
		registerPage.inputToConfirmPasswordTextbox(confirmPassWord);

		log.info("TC_03_RegisterWithExistEmail - Step 02: Click to 'Register' button");
		registerPage.clickToSubmitButton();

		log.info("TC_03_RegisterWithExistEmail - Step 03: Verify validation error message displays");
		verifyTrue(registerPage.isErrorMessageDisplayed("The specified email already exists"));

	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_RegisterWithInvalidPass() {

		log.info("TC_04_RegisterWithInvalidPass - Step 01: Input value");
		registerPage.clickToGenderRadioByID("gender-male");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.selectDayDropdown(day);
		registerPage.selectMonthDropdown(month);
		registerPage.selectYearDropdown(year);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToCompanyTextbox(company);
		registerPage.inputToPasswordTextbox("1234");
		registerPage.inputToConfirmPasswordTextbox("1234");

		log.info("TC_04_RegisterWithInvalidPass - Step 02: Click to 'Register' button");
		registerPage.clickToSubmitButton();

		log.info("TC_04_RegisterWithInvalidPass - Step 03: Verify validation error message displays");
		verifyTrue(registerPage.isValidationMsgByIDDisplayed("Password-error", "Password must meet the following rules: "));
		verifyTrue(registerPage.isValidationMsgByIDDisplayed("Password-error", "must have at least 6 characters"));

	}

	@Test(description = "Confirm Password is different Password")
	public void TC_05_RegisterWithInvalidConfirmPass() {
		log.info("TC_05_RegisterWithInvalidConfirmPass - Step 01: Input value");
		registerPage.clickToGenderRadioByID("gender-male");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.selectDayDropdown(day);
		registerPage.selectMonthDropdown(month);
		registerPage.selectYearDropdown(year);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToCompanyTextbox(company);
		registerPage.inputToPasswordTextbox(passWord);
		registerPage.inputToConfirmPasswordTextbox("123432");

		log.info("TC_05_RegisterWithInvalidConfirmPass - Step 02: Click to 'Register' button");
		registerPage.clickToSubmitButton();

		log.info("TC_05_RegisterWithInvalidConfirmPass - Step 03: Verify validation error message displays");
		verifyTrue(registerPage.isValidationMsgByIDDisplayed("ConfirmPassword-error", "The password and confirmation password do not match."));
	}

	@Test()
	public void TC_06_RegisterToSystem() {
		log.info("TC_06_RegisterToSystem - Step 01: Input value");
		registerPage.clickToGenderRadioByID("gender-male");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.selectDayDropdown(day);
		registerPage.selectMonthDropdown(month);
		registerPage.selectYearDropdown(year);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToCompanyTextbox(company);
		registerPage.inputToPasswordTextbox(passWord);
		registerPage.inputToConfirmPasswordTextbox(confirmPassWord);

		log.info("TC_06_RegisterToSystem - Step 02: Click to 'Register' button");
		registerPage.clickToSubmitButton();

		log.info("TC_06_RegisterToSystem - Step 03: Verify successful message displays");
		verifyTrue(registerPage.isRegisterSuccessMsgDisplayed("Your registration completed"));
		registerPage.clickToNopCommerceHeaderLinkByText(driver, "Log out");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
