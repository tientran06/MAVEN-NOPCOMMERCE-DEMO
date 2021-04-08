package pageUIs.nopcommerce;

public class CheckOutPageUI {
    public static final String DYNAMIC_BUTTON = "//div[@id = '%s']//button[text() = '%s']";
    public static final String DYNAMIC_TITLE_TEXT = "//div//h2[text() = '%s' and @class = 'title']";
    public static final String SHIPPING_ADDRESS_TEXT = "//div/label[text() = 'Select a shipping address from your address book or enter a new address.']";
    public static final String DYNAMIC_PAYMENT_INFOR_TEXT = "//div[@class = 'info']//p[contains(.,\"%s\")]";
    public static final String DYNAMIC_CONFIRM_ORDER_TITLE = "//div[@class = '%s']//strong[text() = '%s']";
    public static final String DYNAMIC_CONFIRM_ORDER_DETAIL = "//div[@class = '%s']//li[@class = '%s']";
    public static final String DYNAMIC_CONFIRM_ORDER_METHOD_INFOR = "//div[@class = '%s']//span[contains(.,'%s')]/following-sibling::span";
    public static final String DYNAMIC_TOTAL_INFO = "//div[@class = '%s']//label[text() = '%s']/parent::td/following-sibling::td/span";
    public static final String CART_OPTIONS = "//div[@class = 'cart-options']/div[contains(.,'Gift wrapping:')]";
    public static final String COMPLETED_ORDER_TITLE = "//div[@class = 'page checkout-page order-completed-page']//h1";
    public static final String DYNAMIC_COMPLETED_ORDER_DETAIL = "//div[@class = 'section order-completed']//div[@class = '%s']//strong";
    public static final String DYNAMIC_COMPLETED_ORDER_DETAIL_LINK = "//div[@class = 'section order-completed']//div[@class = '%s']//a";

}
