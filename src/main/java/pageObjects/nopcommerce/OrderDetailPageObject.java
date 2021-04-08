package pageObjects.nopcommerce;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.OrderDetailPageUI;

import java.util.Collection;

public class OrderDetailPageObject extends AbstractPage {
	WebDriver driver;

	public OrderDetailPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public String getOrderDetailTextByClass(WebDriver driver, String classValue, String classDetail) {
		waitForElementVisible(driver, OrderDetailPageUI.DYNAMIC_ORDER_DETAIL_TITLE, classValue, classDetail);
		return getTextElement(driver, OrderDetailPageUI.DYNAMIC_ORDER_DETAIL_TITLE, classValue, classDetail);
	}

	public String getOrderDetailContentByClass(WebDriver driver, String classValue, String classDetail) {
		waitForElementVisible(driver, OrderDetailPageUI.DYNAMIC_ORDER_DETAIL_CONTENT, classValue, classDetail);
		return getTextElement(driver, OrderDetailPageUI.DYNAMIC_ORDER_DETAIL_CONTENT, classValue, classDetail);
	}

	public Object getOrderTotalPriceByClass(WebDriver driver, String classValue) {
		waitForElementVisible(driver, OrderDetailPageUI.DYNAMIC_ORDER_TOTAL_PRICE, classValue);
		return getTextElement(driver, OrderDetailPageUI.DYNAMIC_ORDER_TOTAL_PRICE, classValue);
	}

	public String getOrderMethodByClass(WebDriver driver, String classValue, String classDetail) {
		waitForElementVisible(driver, OrderDetailPageUI.DYNAMIC_ORDER_METHOD, classValue, classDetail);
		return getTextElement(driver, OrderDetailPageUI.DYNAMIC_ORDER_METHOD, classValue, classDetail);
	}

	public String getTotalInforByText(WebDriver driver, String classValue, String textValue) {
		waitForElementVisible(driver, OrderDetailPageUI.DYNAMIC_ORDER_TOTAL_INFO, classValue, textValue);
		return getTextElement(driver, OrderDetailPageUI.DYNAMIC_ORDER_TOTAL_INFO, classValue, textValue);
	}

}
