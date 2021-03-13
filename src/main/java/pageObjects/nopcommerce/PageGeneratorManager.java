package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

	// cap phat viec khoi tao cho doi tuong
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static MyAccountPageObject getMyAccountPage(WebDriver driver) {
		return new MyAccountPageObject(driver);
	}

	public static SoftwarePageObject getSoftwarePage(WebDriver driver) {
		return new SoftwarePageObject(driver);
	}

	public static NoteBooksPageObject getNoteBooksPage(WebDriver driver) {
		return new NoteBooksPageObject(driver);
	}

	public static WishListPageObject getWishListPage(WebDriver driver) {
		return new WishListPageObject(driver);
	}

	public static ShoppingCartPageObject getShoppingCartPage(WebDriver driver) {
		return new ShoppingCartPageObject(driver);
	}

	public static CellPhonePageObject getCellPhonePage(WebDriver driver) {
		return new CellPhonePageObject(driver);
	}

	public static CameraAndPhotoPageObject getCameraAndPhotoPage(WebDriver driver) {
		return new CameraAndPhotoPageObject(driver);
	}

	public static CompareProductsListPageObject getCompareProductsListPage(WebDriver driver) {
		return new CompareProductsListPageObject(driver);
	}

	public static ProductPageObject getProductPage(WebDriver driver) {
		return new ProductPageObject(driver);
	}

	public static ProductReviewPageObject getProductReviewPage(WebDriver driver) {
		return new ProductReviewPageObject(driver);
	}

	public static SearchPageObject getSearchPage(WebDriver driver) {
		return new SearchPageObject(driver);
	}
	public static DesktopsPageObject getDesktopsPage(WebDriver driver) {
		return new DesktopsPageObject(driver);
	}

}
