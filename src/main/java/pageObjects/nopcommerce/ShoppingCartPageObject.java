package pageObjects.nopcommerce;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.ShoppingCartPageUI;

import java.util.Collection;

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

    public String getShoppingCardTextByClass(WebDriver driver, String textValue) {
		waitForElementVisible(driver, ShoppingCartPageUI.WRAPPING_TEXT, textValue);
		return getTextElement(driver, ShoppingCartPageUI.WRAPPING_TEXT, textValue);
    }

	public String getShoppingCArdTotalInfoByLabel(WebDriver driver, String labelValue) {
		findElementByXpath(driver, ShoppingCartPageUI.DYNAMIC_TOTAL_INFO, labelValue);
		waitForElementVisible(driver, ShoppingCartPageUI.DYNAMIC_TOTAL_INFO, labelValue);
		return getTextElement(driver, ShoppingCartPageUI.DYNAMIC_TOTAL_INFO, labelValue);
	}
}
