package pageObjects.nopcommerce;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class ProductReviewPageObject extends AbstractPage {
	WebDriver driver;

	public ProductReviewPageObject(WebDriver _driver) {
		driver = _driver;
	}

}
