package pageObjects.nopcommerce;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	private WebDriver driver;

	public LoginPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public void inputToEmailTextbox(String emailValue) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailValue);

	}

	public void inputToPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passwordValue);

	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);

	}

	public boolean isLoginErrorMsgByIDDisplayed(String idValue, String errorMessage) {
		waitForElementVisible(driver, LoginPageUI.DYNAMIC_ERROR_MSG, idValue, errorMessage);
		return isElementDisplayed(driver, LoginPageUI.DYNAMIC_ERROR_MSG, idValue, errorMessage);
	}

	public boolean isResultErrorMsgByClassDisplayed(String classValue, String errorMessage) {
		waitForElementVisible(driver, LoginPageUI.DYNAMIC_VALIDATION_MSG, classValue, errorMessage);
		return isElementDisplayed(driver, LoginPageUI.DYNAMIC_VALIDATION_MSG, classValue, errorMessage);
	}

}
