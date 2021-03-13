package pageObjects.nopcommerce;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.CompareProductsListUI;

public class CompareProductsListPageObject extends AbstractPage {
	WebDriver driver;

	public CompareProductsListPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public boolean isCompareProductInforDisplayedByText(String labelValue, String textValue) {
		waitForElementVisible(driver, CompareProductsListUI.DYNAMIC_PRODUCT_INFOR, labelValue, textValue);
		return isElementDisplayed(driver, CompareProductsListUI.DYNAMIC_PRODUCT_INFOR, labelValue, textValue);
	}

	public boolean isCompareProductInforUnDisplayedByText(String labelValue, String textValue) {
		waitForElementInvisible(driver, CompareProductsListUI.DYNAMIC_PRODUCT_INFOR, labelValue, textValue);
		return isElementUnDisplayed(driver, CompareProductsListUI.DYNAMIC_PRODUCT_INFOR, labelValue, textValue);
	}

	public String getProductInforByRow(String productName, String rowNumber) {
		int index;
		String locator;
		index = findElementsByXpath(driver, CompareProductsListUI.DYNAMIC_LOCATOR_PRODUCT, productName).size() + 1;

		/* get Index base on productName and input to locator (row and column) to get new locator */
		locator = "//tbody/tr[" + rowNumber + "]/td[" + index + "]";
		waitForElementVisible(driver, locator);
		return getTextElement(driver, locator);
	}

	public void clickToButtonByColumnAndRow(String productName, String rowNumber) {
		int index;
		String locator;
		index = findElementsByXpath(driver, CompareProductsListUI.DYNAMIC_LOCATOR_PRODUCT, productName).size() + 1;
		locator = "//tbody/tr[" + rowNumber + "]/td[" + index + "]/button[" + rowNumber + "]";
		waitForElementClickable(driver, locator);
		clickToElement(driver, locator);
		sleepInSecond(driver, 1);
	}

}
