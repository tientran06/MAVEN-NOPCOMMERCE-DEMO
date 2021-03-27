package pageObjects.nopcommerce;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.CheckOutPageUI;
import pageUIs.nopcommerce.WishListPageUI;

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
}
