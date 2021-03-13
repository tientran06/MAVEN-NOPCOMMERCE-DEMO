package pageObjects.nopcommerce;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopcommerce.WishListPageUI;

public class WishListPageObject extends AbstractPage {
	WebDriver driver;

	public WishListPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public void clickToWishListLink() {
		waitForElementClickable(driver, WishListPageUI.DYNAMIC_WISHLIST_SHARE_LINK);
		clickToElement(driver, WishListPageUI.DYNAMIC_WISHLIST_SHARE_LINK);

	}

	public String getWishListTitle() {
		waitForElementVisible(driver, WishListPageUI.WISHLIST_TITLE);
		return getTextElement(driver, WishListPageUI.WISHLIST_TITLE);
	}

	public void checkOnCheckBoxByName(String nameValue) {
		waitForElementClickable(driver, WishListPageUI.DYNAMIC_WISHLIST_CHECKBOX, nameValue);
		checkTheCheckbox(driver, WishListPageUI.DYNAMIC_WISHLIST_CHECKBOX, nameValue);
	}

	public String getWishListWarningMsgByClass(String classValue) {
		waitForElementVisible(driver, WishListPageUI.DYNAMIC_WISHLIST_BODY, classValue);
		return getTextElement(driver, WishListPageUI.DYNAMIC_WISHLIST_BODY, classValue);
	}

}
