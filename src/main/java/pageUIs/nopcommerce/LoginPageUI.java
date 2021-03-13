package pageUIs.nopcommerce;

public class LoginPageUI {
	public static final String EMAIL_TEXTBOX = "//input[@id ='Email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id ='Password']";
	public static final String LOGIN_BUTTON = "//button[text() = 'Log in']";
	public static final String DYNAMIC_ERROR_MSG = "//span[@class = 'field-validation-error']//span[@id = '%s' and contains(.,'%s')]";
	public static final String DYNAMIC_VALIDATION_MSG = "//div[contains(@class,'%s') and contains(.,'%s')]";

}
