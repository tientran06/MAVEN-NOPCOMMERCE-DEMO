package pageObjects.nopcommerce;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.MyAccountPageUI;
import pageUIs.nopcommerce.ProductReviewPageUI;

public class MyAccountPageObject extends AbstractPage {
	WebDriver driver;

	public MyAccountPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public String getAddressAccountInfoByClass(String classValue) {
		waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_ADDRESS_TEXT_INFO, classValue);
		return getTextElement(driver, MyAccountPageUI.DYNAMIC_ADDRESS_TEXT_INFO, classValue);
	}

	public boolean isAddressAccountInfoDisplayedByClass(String classValue, String textInfo) {
		waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_ADDRESS_INFO, classValue, textInfo);
		return isElementDisplayed(driver, MyAccountPageUI.DYNAMIC_ADDRESS_INFO, classValue, textInfo);
	}

	public String getReviewInfoByClass(String classValue, String textValue) {
		waitForElementVisible(driver, ProductReviewPageUI.DYNAMIC_REVIEW_INFO, classValue, textValue);
		return getTextElement(driver, ProductReviewPageUI.DYNAMIC_REVIEW_INFO, classValue, textValue);
	}

}
