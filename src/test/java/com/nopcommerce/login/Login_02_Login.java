package com.nopcommerce.login;

import com.nopcommerce.common.Common_01_RegisterUser;
import commons.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.nopcommerce.HomePageObject;
import pageObjects.nopcommerce.LoginPageObject;
import pageObjects.nopcommerce.PageGeneratorManager;

public class Login_02_Login extends AbstractTest {

	private WebDriver driver;
	private String email, password;
	private HomePageObject homePage;
	private LoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		email = Common_01_RegisterUser.email;
		password = Common_01_RegisterUser.password;

	}

	@BeforeMethod
	public void beforeMethod() {
		homePage.clickToNopCommerceHeaderLinkByText(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);

	}

	@Test
	public void TC_01_LoginWithEmptyData() {
		log.info("TC_01_LoginWithEmptyData - Step 01: Input value");
		loginPage.inputToEmailTextbox("");
		loginPage.inputToPasswordTextbox("");

		log.info("TC_01_LoginWithEmptyData - Step 02: Click to Log in button");
		loginPage.clickToLoginButton();

		log.info("TC_01_LoginWithEmptyData - Step 03: Verify Error message displays");
		verifyTrue(loginPage.isLoginErrorMsgByIDDisplayed("Email-error", "Please enter your email"));

	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		log.info("TC_02_LoginWithInvalidEmail - Step 01: Input value");
		loginPage.inputToEmailTextbox("automation.com");
		loginPage.inputToPasswordTextbox(password);

		log.info("TC_02_LoginWithInvalidEmail - Step 02: Click to Log in button");
		loginPage.clickToLoginButton();

		log.info("TC_02_LoginWithInvalidEmail - Step 03: Verify Error message displays");
		verifyTrue(loginPage.isLoginErrorMsgByIDDisplayed("Email-error", "Wrong email"));

	}

	@Test
	public void TC_03_LoginWithIncorrectEmail() {
		log.info("TC_03_LoginWithIncorrectEmail - Step 01: Input value");
		loginPage.inputToEmailTextbox("automation_fc12@gmail.com");
		loginPage.inputToPasswordTextbox(password);

		log.info("TC_03_LoginWithIncorrectEmail - Step 02: Click to Log in button");
		loginPage.clickToLoginButton();

		log.info("TC_03_LoginWithIncorrectEmail - Step 03: Verify Validation message displays");
		verifyTrue(loginPage.isResultErrorMsgByClassDisplayed("message-error", "Login was unsuccessful. Please correct the errors and try again."));
		verifyTrue(loginPage.isResultErrorMsgByClassDisplayed("message-error", "No customer account found"));

	}

	@Test
	public void TC_04_LoginWithEmptyPass() {
		log.info("TC_04_LoginWithEmptyPass - Step 01: Input value");
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox("");

		log.info("TC_04_LoginWithEmptyPass - Step 02: Click to Log in button");
		loginPage.clickToLoginButton();

		log.info("TC_04_LoginWithEmptyPass - Step 03: Verify Validation message displays");
		verifyTrue(loginPage.isResultErrorMsgByClassDisplayed("message-error", "Login was unsuccessful. Please correct the errors and try again."));
		verifyTrue(loginPage.isResultErrorMsgByClassDisplayed("message-error", "The credentials provided are incorrect"));

	}

	@Test
	public void TC_05_LoginWithWrongPass() {
		log.info("TC_05_LoginWithWrongPass - Step 01: Input value");
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox("333222");

		log.info("TC_05_LoginWithWrongPass - Step 02: Click to Log in button");
		loginPage.clickToLoginButton();

		log.info("TC_05_LoginWithWrongPass - Step 03: Verify Validation message displays");
		verifyTrue(loginPage.isResultErrorMsgByClassDisplayed("message-error", "Login was unsuccessful. Please correct the errors and try again."));
		verifyTrue(loginPage.isResultErrorMsgByClassDisplayed("message-error", "The credentials provided are incorrect"));
	}

	@Test
	public void TC_06_LoginToTheSystem() {
		log.info("TC_06_LoginToTheSystem - Step 01: Input value");
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(password);

		log.info("TC_06_LoginToTheSystem - Step 02: Click to Log in button");
		loginPage.clickToLoginButton();

		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("TC_06_LoginToTheSystem - Step 03: Verify succesfull message displays");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
		homePage.clicktoLogoutButton();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
