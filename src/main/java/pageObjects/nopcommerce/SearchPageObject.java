package pageObjects.nopcommerce;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class SearchPageObject extends AbstractPage {
	WebDriver driver;

	public SearchPageObject(WebDriver _driver) {
		driver = _driver;
	}
}
