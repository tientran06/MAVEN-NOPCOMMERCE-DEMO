package pageObjects.nopcommerce;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.CheckOutPageUI;

public class CheckOutPageObject extends AbstractPage {
	WebDriver driver;

	public CheckOutPageObject(WebDriver _driver) {
		driver = _driver;
	}
	public void clickToButtonByID(WebDriver driver, String IDValue,  String buttonText){
	waitForElementClickable(driver, CheckOutPageUI.DYNAMIC_BUTTON, IDValue, buttonText);
	clickToElement(driver, CheckOutPageUI.DYNAMIC_BUTTON, IDValue, buttonText);
	}

	public boolean isTitleDisplayedByText(WebDriver driver, String titleValue) {
		waitForElementVisible(driver, CheckOutPageUI.DYNAMIC_TITLE_TEXT, titleValue);
		return isElementDisplayed(driver, CheckOutPageUI.DYNAMIC_TITLE_TEXT, titleValue);
	}

	public boolean isShippingTextDisplayed(WebDriver driver) {
		waitForElementVisible(driver, CheckOutPageUI.SHIPPING_ADDRESS_TEXT);
		return isElementDisplayed(driver, CheckOutPageUI.SHIPPING_ADDRESS_TEXT);
	}

	public boolean isPaymentInforDisplayedByText(WebDriver driver, String textValue) {
		waitForElementVisible(driver, CheckOutPageUI.DYNAMIC_PAYMENT_INFOR_TEXT, textValue);
		return isElementDisplayed(driver, CheckOutPageUI.DYNAMIC_PAYMENT_INFOR_TEXT, textValue);
	}

	public boolean isConfirmOrderTitleDisplayedByText(WebDriver driver, String classValue, String textValue) {
		waitForElementVisible(driver, CheckOutPageUI.DYNAMIC_CONFIRM_ORDER_TITLE, classValue, textValue);
		return isElementDisplayed(driver, CheckOutPageUI.DYNAMIC_CONFIRM_ORDER_TITLE, classValue, textValue);
	}

	public String getConfirmOrderDetailByClass(WebDriver driver, String classValue, String classDetail) {
		waitForElementVisible(driver, CheckOutPageUI.DYNAMIC_CONFIRM_ORDER_DETAIL, classValue, classDetail);
		return getTextElement(driver, CheckOutPageUI.DYNAMIC_CONFIRM_ORDER_DETAIL, classValue, classDetail);
	}

	public String getConfirmOrderMethodByClass(WebDriver driver, String classValue, String textValue) {
		waitForElementVisible(driver, CheckOutPageUI.DYNAMIC_CONFIRM_ORDER_METHOD_INFOR, classValue, textValue);
		return getTextElement(driver, CheckOutPageUI.DYNAMIC_CONFIRM_ORDER_METHOD_INFOR, classValue, textValue);
	}

	public String getCartOptionStatus(WebDriver driver) {
		waitForElementVisible(driver, CheckOutPageUI.CART_OPTIONS);
		return getTextElement(driver, CheckOutPageUI.CART_OPTIONS);
	}

	public String getTotalInforByText(WebDriver driver, String classValue, String textValue) {
		waitForElementVisible(driver, CheckOutPageUI.DYNAMIC_TOTAL_INFO, classValue, textValue);
		return getTextElement(driver, CheckOutPageUI.DYNAMIC_TOTAL_INFO, classValue, textValue);
	}

	public String getCheckOutCompletedPageTitleByClass(WebDriver driver) {
		waitForElementVisible(driver, CheckOutPageUI.COMPLETED_ORDER_TITLE);
		return getTextElement(driver, CheckOutPageUI.COMPLETED_ORDER_TITLE);
	}
	public String getCheckOutCompletedDetailByClass(WebDriver driver, String classValue) {
		waitForElementVisible(driver, CheckOutPageUI.DYNAMIC_COMPLETED_ORDER_DETAIL, classValue);
		return getTextElement(driver, CheckOutPageUI.DYNAMIC_COMPLETED_ORDER_DETAIL, classValue);
	}

	public String getCheckOutCompletedDetailLinkByClass(WebDriver driver, String classValue) {
		waitForElementVisible(driver, CheckOutPageUI.DYNAMIC_COMPLETED_ORDER_DETAIL_LINK, classValue);
		return getTextElement(driver, CheckOutPageUI.DYNAMIC_COMPLETED_ORDER_DETAIL_LINK, classValue);
	}

	public String getCheckOutOrderNo(WebDriver driver, String classValue) {
		String returnText, orderNo;
		returnText = getCheckOutCompletedDetailByClass(driver,classValue);
		orderNo = returnText.substring(returnText.length()-4);
		return  orderNo;
	}
}
