package pageUIs.nopcommerce;

public class RegisterPageUI {
	public static final String DYNAMIC_GENDER_RADIO = "//input[@id ='%s']";
	public static final String FIRST_NAME_TEXTBOX = "//input[@id ='FirstName']";
	public static final String LAST_NAME_TEXTBOX = "//input[@id ='LastName']";
	public static final String DAY_DROPDOWN = "//select[@name = 'DateOfBirthDay']";
	public static final String MONTH_DROPDOWN = "//select[@name = 'DateOfBirthMonth']";
	public static final String YEAR_DROPDOWN = "//select[@name = 'DateOfBirthYear']";
	public static final String EMAIL_TEXTBOX = "//input[@id ='Email']";
	public static final String COMPANY_NAME_TEXTBOX = "//input[@id ='Company']";
	public static final String PASSWORD_TEXTBOX = "//input[@id ='Password']";
	public static final String CONFIRM_PASSWORD_TEXTBOX = "//input[@id ='ConfirmPassword']";
	public static final String REGISTER_BUTTON = "//button[@id ='register-button']";
	public static final String DYNAMIC_REGISTER_VALIDATION_MSG = "//span[@class = 'field-validation-error']//span[@id = '%s' and contains(.,'%s')]";
	public static final String DYNAMIC_ERROR_MSG = "//div[contains(@class,'message-error')]//li[text() = '%s']";
	public static final String DYNAMIC_RESULT_MSG = "//div[@class = 'result' and text() = '%s']";
}
