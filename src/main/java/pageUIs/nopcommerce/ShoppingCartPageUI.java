package pageUIs.nopcommerce;

public class ShoppingCartPageUI {
	public static final String  EDIT_LINK = "//div[@class = 'edit-item']//a[text() = 'Edit']";
	public static final String  DYNAMIC_RESULT_MSG = "//div[@class = 'page-body']//div[@class = '%s']";
	public static final String  WRAPPING_TEXT = "//div[@class = 'selected-checkout-attributes' and contains(.,'%s')]";
	public static final String  DYNAMIC_TOTAL_INFO = "//div[@class = 'total-info']//label[contains(text(), '%s')]/parent::td/following-sibling::td//strong";
}
