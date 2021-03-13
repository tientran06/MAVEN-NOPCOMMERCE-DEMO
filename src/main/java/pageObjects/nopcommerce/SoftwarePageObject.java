package pageObjects.nopcommerce;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class SoftwarePageObject extends AbstractPage {
	WebDriver driver;

	public SoftwarePageObject(WebDriver _driver) {
		driver = _driver;
	}

}
