package pageUIs.nopcommerce;

public class MyAccountPageUI {
	public static final String DYNAMIC_ADDRESS_INFO = "//ul[@class = 'info']//li[@class = '%s' and contains(.,'%s')]";
	public static final String DYNAMIC_ADDRESS_TEXT_INFO = "//ul[@class = 'info']//li[@class = '%s']";
	public static final String DYNAMIC_MYACCOUNT_ORDER_TITLE = "//div[@class = '%s']//h1";
	public static final String DYNAMIC_MYACCOUNT_ORDER_DETAIL = "//div[@class = 'section order-item']//div[@class = 'title']//strong[contains(text(), '%s')]";
	public  static  final String DYNAMIC_MYACCOUNT_ORDER_INFO = "//strong[contains(text(), '%s')]/parent::div//following-sibling::ul[@class = 'info']//li//span[@class = '%s']";
	public  static  final String DYNAMIC_MYACCOUNT_ORDER_DETAIL_BUTTON = "//strong[contains(text(), '%s')]/parent::div//following-sibling::div[@class = 'buttons']/button";

}
