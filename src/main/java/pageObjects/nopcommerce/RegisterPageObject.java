package pageObjects.nopcommerce;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public void inputToFirstNameTextbox(String firstNameValue) {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstNameValue);

	}

	public void inputToLastNameTextbox(String lastNameValue) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastNameValue);

	}

	public void selectDayDropdown(String expectedDay) {
		waitForElementPresence(driver, RegisterPageUI.DAY_DROPDOWN);
		selectDefaultDropdownListByVisibleText(driver, RegisterPageUI.DAY_DROPDOWN, expectedDay);
	}

	public void selectMonthDropdown(String expectedMonth) {
		waitForElementPresence(driver, RegisterPageUI.MONTH_DROPDOWN);
		selectDefaultDropdownListByVisibleText(driver, RegisterPageUI.MONTH_DROPDOWN, expectedMonth);

	}

	public void selectYearDropdown(String expectedYear) {
		waitForElementPresence(driver, RegisterPageUI.YEAR_DROPDOWN);
		selectDefaultDropdownListByVisibleText(driver, RegisterPageUI.YEAR_DROPDOWN, expectedYear);

	}

	public void inputToEmailTextbox(String emailValue) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailValue);

	}

	public void inputToCompanyTextbox(String companyValue) {
		waitForElementVisible(driver, RegisterPageUI.COMPANY_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.COMPANY_NAME_TEXTBOX, companyValue);

	}

	public void inputToPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, passwordValue);

	}

	public void inputToConfirmPasswordTextbox(String confirmPasswordValue) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPasswordValue);

	}

	public void clickToSubmitButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);

	}

	public boolean isValidationMsgByIDDisplayed(String idValue, String errorMessage) {
		waitForElementVisible(driver, RegisterPageUI.DYNAMIC_REGISTER_VALIDATION_MSG, idValue, errorMessage);
		return isElementDisplayed(driver, RegisterPageUI.DYNAMIC_REGISTER_VALIDATION_MSG, idValue, errorMessage);
	}

	public void clickToGenderRadioByID(String idValue) {
		waitForElementClickable(driver, RegisterPageUI.DYNAMIC_GENDER_RADIO, idValue);
		clickToElement(driver, RegisterPageUI.DYNAMIC_GENDER_RADIO, idValue);

	}

	public boolean isErrorMessageDisplayed(String errorMsg) {
		waitForElementVisible(driver, RegisterPageUI.DYNAMIC_ERROR_MSG, errorMsg);
		return isElementDisplayed(driver, RegisterPageUI.DYNAMIC_ERROR_MSG, errorMsg);
	}

	public boolean isRegisterSuccessMsgDisplayed(String messageValue) {
		waitForElementVisible(driver, RegisterPageUI.DYNAMIC_RESULT_MSG, messageValue);
		return isElementDisplayed(driver, RegisterPageUI.DYNAMIC_RESULT_MSG, messageValue);
	}

}
