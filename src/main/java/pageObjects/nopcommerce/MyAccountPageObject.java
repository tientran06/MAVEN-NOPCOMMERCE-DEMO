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

	public String getMyAccountTitleByClass(WebDriver driver, String classValue) {
		waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_MYACCOUNT_ORDER_TITLE, classValue);
		return getTextElement(driver, MyAccountPageUI.DYNAMIC_MYACCOUNT_ORDER_TITLE, classValue);
	}

	public String getMyAccountOrderInfoByOrderNo(WebDriver driver, String orderNo, String classValue) {
		waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_MYACCOUNT_ORDER_INFO, orderNo, classValue);
		return getTextElement(driver, MyAccountPageUI.DYNAMIC_MYACCOUNT_ORDER_INFO, orderNo, classValue);
	}

	public boolean isMyAccountOrderDisplayed(WebDriver driver, String orderNo) {
		waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_MYACCOUNT_ORDER_DETAIL, orderNo);
		return isElementDisplayed(driver, MyAccountPageUI.DYNAMIC_MYACCOUNT_ORDER_DETAIL, orderNo);
	}

	public void clickToOrderDetailButtonByOrderNo(WebDriver driver, String orderNo) {
		waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_MYACCOUNT_ORDER_DETAIL_BUTTON, orderNo);
		clickToElement(driver, MyAccountPageUI.DYNAMIC_MYACCOUNT_ORDER_DETAIL_BUTTON, orderNo);
	}
}
