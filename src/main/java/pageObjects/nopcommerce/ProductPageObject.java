package pageObjects.nopcommerce;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.ProductPageUI;

public class ProductPageObject extends AbstractPage {
	WebDriver driver;

	public ProductPageObject(WebDriver _driver) {
		driver = _driver;
	}


    public void clickToAddToCartButton(WebDriver driver) {
		waitForElementClickable(driver, ProductPageUI.ADD_TO_CART_BUTTON);
		clickToElement(driver, ProductPageUI.ADD_TO_CART_BUTTON);
    }
}
