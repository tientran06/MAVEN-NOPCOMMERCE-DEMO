package pageUIs.nopcommerce;

public class OrderDetailPageUI {
    public static final String DYNAMIC_ORDER_DETAIL_TITLE = "//div[@class = '%s']//div[@class = '%s']/strong";
    public static final String DYNAMIC_ORDER_DETAIL_CONTENT = "//div[@class = '%s']//ul//li[@class = '%s']";
    public static final String DYNAMIC_ORDER_TOTAL_PRICE = "//div[@class = 'order-overview']//ul//li[@class = '%s']/strong";
    public static final String DYNAMIC_ORDER_METHOD = "//div[@class = '%s']//ul//li[@class = '%s']//span[@class = 'value']";
    public static final String DYNAMIC_ORDER_TOTAL_INFO = "//div[@class = '%s']//label[text() = '%s']/parent::td/following-sibling::td/span";
    public static final String SELECTED_CHECKOUT_OPTIONS = "//div[@class = 'section options']/div[contains(.,'Gift wrapping:')]";

}
