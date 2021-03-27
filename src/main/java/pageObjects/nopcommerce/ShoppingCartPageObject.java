package pageObjects.nopcommerce;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.ShoppingCartPageUI;

public class ShoppingCartPageObject extends AbstractPage {
	WebDriver driver;

	public ShoppingCartPageObject(WebDriver _driver) {
		driver = _driver;
	}

    public void clickToEditLink(WebDriver driver) {
		waitForElementClickable(driver, ShoppingCartPageUI.EDIT_LINK);
		clickToElement(driver, ShoppingCartPageUI.EDIT_LINK);
    }

	public String getShoppingCardResultMsgByClass(WebDriver driver, String classValue) {
		waitForElementVisible(driver, ShoppingCartPageUI.DYNAMIC_RESULT_MSG, classValue);
		return getTextElement(driver, ShoppingCartPageUI.DYNAMIC_RESULT_MSG, classValue);
	}
}
