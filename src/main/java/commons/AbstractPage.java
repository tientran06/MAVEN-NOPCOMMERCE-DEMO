package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.nopcommerce.AbstractPageNopCommerceUI;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AbstractPage {
	private WebDriverWait waitExplicit;
	private WebElement element;
	private List<WebElement> elements;
	private By byXpath;
	private JavascriptExecutor jsExecutor;
	private Select select;
	private Actions action;
	private int index;
	private String locator;

	public void openUrl(WebDriver driver, String urlValue) {
		driver.get(urlValue);
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public By byXpathLocator(String locator) {
		return By.xpath(locator);
	}

	public By byXpathLocator(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return By.xpath(locator);
	}

	public WebElement findElementByXpath(WebDriver driver, String locator) {
		return driver.findElement(byXpathLocator(locator));
	}

	public WebElement findElementByXpath(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return driver.findElement(byXpathLocator(locator));
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		return driver.findElements(byXpathLocator(locator));
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return driver.findElements(byXpathLocator(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (driver.toString().toLowerCase().contains("internetexplorer")) {
			clickToElementByJS(driver, locator);
			sleepInSecond(driver, 5);
		} else {
			element.click();
		}
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, locator, values);
		if (driver.toString().toLowerCase().contains("internetexplorer")) {
			clickToElementByJS(driver, locator);
			sleepInSecond(driver, 5);
		} else {
			element.click();
		}
	}

	public void sendKeyToElement(WebDriver driver, String locator, String inputValue) {
		element = findElementByXpath(driver, locator);
		element.clear();
		element.sendKeys(inputValue);
	}

	public void sendKeyToElement(WebDriver driver, String locator, String inputValue, String... values) {
		element = findElementByXpath(driver, locator, values);
		element.clear();
		element.sendKeys(inputValue);
	}

	public String getAttributeValue(WebDriver driver, String locator, String attributeValue) {
		return findElementByXpath(driver, locator).getAttribute(attributeValue);
	}

	public String getAttributeValue(WebDriver driver, String locator, String attributeValue, String... values) {
		return findElementByXpath(driver, locator, values).getAttribute(attributeValue);
	}

	public String getTextElement(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText();
	}

	public String getTextElement(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, locator, values).getText();
	}

	public int countElementNumber(WebDriver driver, String locator) {
		return findElementsByXpath(driver, locator).size();
	}

	public int countElementNumber(WebDriver driver, String locator, String... values) {
		return findElementsByXpath(driver, locator, values).size();
	}

	public void checkTheCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkTheCheckbox(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, locator, values);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckTheCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void unCheckTheCheckbox(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, locator, values);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			element = findElementByXpath(driver, locator);
			return element.isDisplayed();

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		try {
			element = findElementByXpath(driver, locator, values);
			return element.isDisplayed();

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean isElementUnDisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = findElementsByXpath(driver, locator);
		if (elements.size() == 0) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is not in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is in DOM but not Displayed");
			return true;
		} else {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is Displayed");
			return false;
		}
	}

	public boolean isElementUnDisplayed(WebDriver driver, String locator, String... values) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = findElementsByXpath(driver, locator, values);
		if (elements.size() == 0) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is not in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is in DOM but not Displayed");
			return true;
		} else {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is Displayed");
			return false;
		}
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementSelected(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, locator, values);
		if (element.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			element = findElementByXpath(driver, locator);
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return element.isEnabled();

		} catch (Exception ex) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			ex.printStackTrace();
			return false;
		}
	}

	public boolean isElementEnabled(WebDriver driver, String locator, String... values) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			element = findElementByXpath(driver, locator, values);
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return element.isEnabled();

		} catch (Exception ex) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			ex.printStackTrace();
			return false;
		}
	}

	public boolean isDropDownMultiple(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		select = new Select(element);
		if (select.isMultiple()) {
			return true;
		} else {
			return false;
		}
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String termWindow : allWindows) {
			if (!termWindow.equals(parentID)) {
				driver.switchTo().window(termWindow);
				break;
			}
		}

	}

	public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String termWindow : allWindows) {
			driver.switchTo().window(termWindow);
			String currentTitle = driver.getTitle();
			if (currentTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String termWindow : allWindows) {
			if (!termWindow.equals(parentID)) {
				driver.close();
			}
		}
	}

	public void switchToIframe(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		driver.switchTo().frame(element);
	}

	public void switchToIframe(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, locator, values);
		driver.switchTo().frame(element);
	}

	public void backToMainPage(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void moveToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator);
		action.moveToElement(element).perform();
	}

	public void moveToElement(WebDriver driver, String locator, String... values) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator, values);
		action.moveToElement(element).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator);
		action.doubleClick(element).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator, String... values) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator, values);
		action.doubleClick(element).perform();
	}

	public void righClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator);
		action.contextClick(element).perform();
	}

	public void righClickToElement(WebDriver driver, String locator, String... values) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator, values);
		action.contextClick(element).perform();
	}

	public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(findElementByXpath(driver, sourceLocator), findElementByXpath(driver, targetLocator)).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator);
		action.sendKeys(element, key).perform();

	}

	public void waitForElementPresence(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		byXpath = byXpathLocator(locator);
		try {
			waitExplicit.until(ExpectedConditions.presenceOfElementLocated(byXpath));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void waitForElementPresence(WebDriver driver, String locator, String... values) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		byXpath = byXpathLocator(locator, values);
		try {
			waitExplicit.until(ExpectedConditions.presenceOfElementLocated(byXpath));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		byXpath = byXpathLocator(locator);
		try {
			System.out.println("Start time for wait visible = " + new Date());
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
			System.out.println("End time for wait visible = " + new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... values) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		byXpath = byXpathLocator(locator, values);
		try {
			System.out.println("Start time for wait visible = " + new Date());
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
			System.out.println("End time for wait visible = " + new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		byXpath = byXpathLocator(locator);
		try {
			waitExplicit.until(ExpectedConditions.elementToBeClickable(byXpath));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... values) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		byXpath = byXpathLocator(locator, values);
		try {
			waitExplicit.until(ExpectedConditions.elementToBeClickable(byXpath));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		byXpath = byXpathLocator(locator);
		try {
			System.out.println("Start time for wait invisible= " + new Date());
			waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byXpath));
			System.out.println("End time for wait invisible= " + new Date());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}

	public void waitForElementInvisible(WebDriver driver, String locator, String... values) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		byXpath = byXpathLocator(locator, values);
		try {
			System.out.println("Start time for wait invisible= " + new Date());
			waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byXpath));
			System.out.println("End time for wait invisible= " + new Date());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}

	public void waitForAlertPresence(WebDriver driver) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		try {
			System.out.println("Start time for wait Alert presence = " + new Date());
			waitExplicit.until(ExpectedConditions.alertIsPresent());
			System.out.println("End time for wait Alert presence = " + new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void selectDefaultDropdownListByVisibleText(WebDriver driver, String locator, String valueItem) {
		element = findElementByXpath(driver, locator);
		select = new Select(element);
		select.selectByVisibleText(valueItem);
	}

	public void selectDefaultDropdownListByVisibleText(WebDriver driver, String locator, String valueItem, String... values) {
		element = findElementByXpath(driver, locator, values);
		select = new Select(element);
		select.selectByVisibleText(valueItem);
	}

	public void selectDefaultDropdownListByValue(WebDriver driver, String locator, String valueItem) {
		element = findElementByXpath(driver, locator);
		select = new Select(element);
		select.selectByValue(valueItem);
	}

	public void selectDefaultDropdownListByValue(WebDriver driver, String locator, String valueItem, String... values) {
		element = findElementByXpath(driver, locator, values);
		select = new Select(element);
		select.selectByValue(valueItem);
	}

	public String getSelectItemInHtmlDropdown(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public void selectCustomDropdownList(WebDriver driver, String parentXpath, String allItemsXpath, String expectedText) {
		clickToElement(driver, parentXpath);
		List<WebElement> allItems = findElementsByXpath(driver, allItemsXpath);
		waitForElementPresence(driver, allItemsXpath);
		for (WebElement item : allItems) {
			if (item.getText().contains(expectedText)) {
				item.click();
				break;

			}
		}
	}

	public void inputItemInCustomDropdown(WebDriver driver, String parentXpath, String inputXpath, String expectedXpath) {
		clickToElement(driver, parentXpath);
		sendKeyToElement(driver, inputXpath, expectedXpath);
		sendKeyboardToElement(driver, inputXpath, Keys.ENTER);
	}

	public Object executeJSForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public void scrollToBottomPageByJS(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public String getTextByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.querySelector('" + locator + "').text");
	}

	public Object executeJSForElement(WebDriver driver, String javaScript, String locator) {
		element = findElementByXpath(driver, locator);
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript, element);
	}

	public void scrollToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void highlightElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void clickToElementByJS(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator, values);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		sleepInSecond(driver, 1);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator, values);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		sleepInSecond(driver, 1);
	}

	public void setAttributeValueByJS(WebDriver driver, String locator, String attributeSet, String textValue) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeSet, textValue);
		sleepInSecond(driver, 1);
	}

	public void setAttributeValueByJS(WebDriver driver, String locator, String attributeSet, String textValue, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator, values);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeSet, textValue);
		sleepInSecond(driver, 1);
	}

	public boolean verifyTextInInnerTextByJS(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public boolean checkAnyImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void upload1File(WebDriver driver, String uploadFile, String fileName, String buttonStart) {
		String file = GlobalConstants.UPLOAD_FILE_PATH + fileName;

		waitForElementPresence(driver, uploadFile);
		sendKeyToElement(driver, uploadFile, file);
		sleepInSecond(driver, 2);

		waitForElementVisible(driver, buttonStart);
		clickToElement(driver, buttonStart);
		sleepInSecond(driver, 2);
	}

	public void uploadMultipleFiles(WebDriver driver, String uploadFile, String buttonStart, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FILE_PATH;
		String fullFileName = "";

		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();

		waitForElementPresence(driver, uploadFile);
		sendKeyToElement(driver, uploadFile, fullFileName);
		sleepInSecond(driver, 2);

		waitForElementVisible(driver, buttonStart);
		List<WebElement> buttonStarts = findElementsByXpath(driver, buttonStart);
		for (WebElement button : buttonStarts) {
			button.click();
			sleepInSecond(driver, 2);
		}
		sleepInSecond(driver, 2);
	}

	public void uploadFileByRobot(WebDriver driver, String uploadFile, String picturePath, String buttonStart) throws Exception {
		clickToElement(driver, uploadFile);
		Thread.sleep(1000);

		StringSelection select = new StringSelection(picturePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		Robot robot = new Robot();
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		waitForElementVisible(driver, buttonStart);
		clickToElement(driver, buttonStart);
	}

	public void uploadAutoIT(WebDriver driver, String uploadFile, String picturePath, String buttonStart) throws Exception {
		String chromePath = ".\\uploadAutoIT\\chrome.exe";
		String firefoxPath = ".\\uploadAutoIT\\firefox.exe";
		String iePath = ".\\uploadAutoIT\\ie.exe";

		clickToElement(driver, uploadFile);
		Thread.sleep(1000);

		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxPath, picturePath });

		} else if (driver.toString().contains("chromePath")) {
			Runtime.getRuntime().exec(new String[] { chromePath, picturePath });
		} else {
			Runtime.getRuntime().exec(new String[] { iePath, picturePath });
		}

		waitForElementVisible(driver, buttonStart);
		clickToElement(driver, buttonStart);

	}

	public void sleepInSecond(WebDriver driver, long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public int convertStringToInt(String value) {
		int i = Integer.parseInt(value);
		return i;
	}

	public String convertIntToString(int numberValue) {
		String value = String.valueOf(numberValue);
		return value;
	}

	// **************** Common Methods of NOP COMMERCE ************************** //

	public void clickToNopCommerceHomePage(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageNopCommerceUI.HOMEPAGE_LOGO);
		clickToElement(driver, AbstractPageNopCommerceUI.HOMEPAGE_LOGO);

	}

	public void clickToNopCommerceHeaderLinkByText(WebDriver driver, String pageName) {
		waitForElementClickable(driver, AbstractPageNopCommerceUI.DYNAMIC_HEADER_LINK, pageName);
		clickToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_HEADER_LINK, pageName);
	}

	public void clickToNopCommerceButtonByClass(WebDriver driver, String classValue) {
		waitForElementClickable(driver, AbstractPageNopCommerceUI.DYNAMIC_BUTTON_BAR, classValue);
		clickToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_BUTTON_BAR, classValue);

	}

	public void clickToNopCommerceHeaderOtherLinkByText(WebDriver driver, String pageName) {
		waitForElementClickable(driver, AbstractPageNopCommerceUI.DYNAMIC_HEADER_OTHER_LINK, pageName);
		clickToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_HEADER_OTHER_LINK, pageName);
	}

	public void clickToNopCommerceHeaderMenuByText(WebDriver driver, String menuName) {
		waitForElementPresence(driver, AbstractPageNopCommerceUI.DYNAMIC_HEADER_MENU, menuName);
		clickToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_HEADER_MENU, menuName);
	}

	public void clickToNopCommerceFooterLinkByName(WebDriver driver, String footerMenuName) {
		waitForElementPresence(driver, AbstractPageNopCommerceUI.DYNAMIC_FOOTER_MENU, footerMenuName);
		moveToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_FOOTER_MENU, footerMenuName);

		waitForElementClickable(driver, AbstractPageNopCommerceUI.DYNAMIC_FOOTER_MENU, footerMenuName);
		clickToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_FOOTER_MENU, footerMenuName);
	}

	public void clickToNopCommerceSubMenuByText(WebDriver driver, String parentMenu, String subMenu) {
		waitForElementPresence(driver, AbstractPageNopCommerceUI.DYNAMIC_HEADER_MENU, parentMenu);
		moveToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_HEADER_MENU, parentMenu);

		waitForElementClickable(driver, AbstractPageNopCommerceUI.DYNAMIC_HEADER_SUB_MENU, parentMenu, subMenu);
		clickToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_HEADER_SUB_MENU, parentMenu, subMenu);

	}

	public void clickToNopCommerceListBoxMenuByName(WebDriver driver, String menuName) {
		waitForElementClickable(driver, AbstractPageNopCommerceUI.DYNAMIC_LISTBOX_MENU, menuName);
		clickToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_LISTBOX_MENU, menuName);
	}

	public void clickToRadioButtonByID(WebDriver driver, String radioValue) {
		waitForElementClickable(driver, AbstractPageNopCommerceUI.DYNAMIC_RADIO, radioValue);
		clickToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_RADIO, radioValue);

	}

	public void inputToNopCommerceTextBoxByID(WebDriver driver, String idValue, String inputValue) {
		waitForElementVisible(driver, AbstractPageNopCommerceUI.DYNAMIC_TEXTBOX, idValue);
		sendKeyToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_TEXTBOX, inputValue, idValue);
	}

	public void inputToNopCommerceTextAreaByClass(WebDriver driver, String classValue, String inputValue) {
		waitForElementVisible(driver, AbstractPageNopCommerceUI.DYNAMIC_TEXTAREA, classValue);
		sendKeyToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_TEXTAREA, inputValue, classValue);
	}

	public void selectNopCommerceDropdownListByName(WebDriver driver, String dropdownName, String valueItem) {
		waitForElementPresence(driver, AbstractPageNopCommerceUI.DYNAMIC_DROPDOWN_LIST, dropdownName);
		selectDefaultDropdownListByVisibleText(driver, AbstractPageNopCommerceUI.DYNAMIC_DROPDOWN_LIST, valueItem, dropdownName);
	}

	public void clickToNopCommerceSubButtonByValue(WebDriver driver, String buttonValue) {
		waitForElementClickable(driver, AbstractPageNopCommerceUI.DYNAMIC_DETAIL_BUTTON, buttonValue);
		clickToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_DETAIL_BUTTON, buttonValue);
	}

	public void clickToNopCommerceButtonByValue(WebDriver driver, String buttonValue) {
		waitForElementClickable(driver, AbstractPageNopCommerceUI.DYNAMIC_BUTTON, buttonValue);
		clickToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_BUTTON, buttonValue);
	}

	public boolean isNopCommerceRadioButtonSelectedByID(WebDriver driver, String idValue) {
		waitForElementPresence(driver, AbstractPageNopCommerceUI.DYNAMIC_RADIO, idValue);
		return isElementSelected(driver, AbstractPageNopCommerceUI.DYNAMIC_RADIO, idValue);
	}

	public String getNopCommerceAttributeValueByID(WebDriver driver, String idValue) {
		waitForElementVisible(driver, AbstractPageNopCommerceUI.DYNAMIC_TEXTBOX, idValue);
		return getAttributeValue(driver, AbstractPageNopCommerceUI.DYNAMIC_TEXTBOX, "value", idValue);
	}

	public boolean isNopCommerceDropdownListSelectedByText(WebDriver driver, String dropdownName, String selectedText) {
		waitForElementPresence(driver, AbstractPageNopCommerceUI.DYNAMIC_SELECTED_DROPDOWN_LIST, dropdownName, selectedText);
		return isElementSelected(driver, AbstractPageNopCommerceUI.DYNAMIC_SELECTED_DROPDOWN_LIST, dropdownName, selectedText);
	}

	public String getNopCommerceTextByClass(WebDriver driver, String classValue) {
		waitForElementVisible(driver, AbstractPageNopCommerceUI.DYNAMIC_TEXT_MSG, classValue);
		return getTextElement(driver, AbstractPageNopCommerceUI.DYNAMIC_TEXT_MSG, classValue);
	}

	public String getNopCommerceNoticeByClass(WebDriver driver, String classValue) {
		waitForElementVisible(driver, AbstractPageNopCommerceUI.DYNAMIC_TEXT_NOTIFICATION, classValue);
		return getTextElement(driver, AbstractPageNopCommerceUI.DYNAMIC_TEXT_NOTIFICATION, classValue);
	}

	public void clickToNopCommerceLinkByClass(WebDriver driver, String classValue, String textValue) {
		waitForElementClickable(driver, AbstractPageNopCommerceUI.DYNAMIC_LINK, classValue, textValue);
		clickToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_LINK, classValue, textValue);
	}

	public String getNopCommerceQtyProductByText(WebDriver driver, String productName) {
		waitForElementPresence(driver, AbstractPageNopCommerceUI.DYNAMIC_PRODUCT_TITLE, productName);
		return (countElementNumber(driver, AbstractPageNopCommerceUI.DYNAMIC_PRODUCT_TITLE, productName) + "");
	}

	public void checkOnNopCommerceCheckboxByID(WebDriver driver, String idValue) {
		waitForElementVisible(driver, AbstractPageNopCommerceUI.DYNAMIC_CHECKBOX, idValue);
		checkTheCheckbox(driver, AbstractPageNopCommerceUI.DYNAMIC_CHECKBOX, idValue);
	}

	public boolean isNopCommerceBarNotificationDisplayed(WebDriver driver, String textValue, String linkTextValue) {
		waitForElementVisible(driver, AbstractPageNopCommerceUI.DYNAMIC_BAR_NOTIFICATION, textValue, linkTextValue);
		return isElementDisplayed(driver, AbstractPageNopCommerceUI.DYNAMIC_BAR_NOTIFICATION, textValue, linkTextValue);
	}

	public String getNopCommerceProductNameByText(WebDriver driver, String productName) {
		waitForElementVisible(driver, AbstractPageNopCommerceUI.DYNAMIC_PRODUCT_DETAIL, productName);
		return getTextElement(driver, AbstractPageNopCommerceUI.DYNAMIC_PRODUCT_DETAIL, productName);
	}

	public void clickToNopCommerceSubButtonByProductName(WebDriver driver, String productName, String buttonValue) {
		waitForElementClickable(driver, AbstractPageNopCommerceUI.DYNAMIC_SUB_BUTTON, productName, buttonValue);
		clickToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_SUB_BUTTON, productName, buttonValue);
		sleepInSecond(driver, 1);
	}

	public void clickToNopCommerceButtonIconByProductName(WebDriver driver, String productName, String classValue) {
		waitForElementClickable(driver, AbstractPageNopCommerceUI.DYNAMIC_PRODUCT_ITEM_INFOR, productName, classValue);
		clickToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_PRODUCT_ITEM_INFOR, productName, classValue);

	}

	public boolean isNopCommerceProductUndisplayed(WebDriver driver, String productName) {
		waitForElementInvisible(driver, AbstractPageNopCommerceUI.DYNAMIC_PRODUCT_DETAIL, productName);
		return isElementUnDisplayed(driver, AbstractPageNopCommerceUI.DYNAMIC_PRODUCT_DETAIL, productName);
	}

	public void backToNopCommercePreviousPage(WebDriver driver) {
		back(driver);
	}

	public boolean isNopCommerceLinkDisplayedByClass(WebDriver driver, String classValue, String textValue) {
		waitForElementVisible(driver, AbstractPageNopCommerceUI.DYNAMIC_LINK, classValue, textValue);
		return isElementDisplayed(driver, AbstractPageNopCommerceUI.DYNAMIC_LINK, classValue, textValue);
	}

	public boolean isNopCommerceLinkUnDisplayedByClass(WebDriver driver, String classValue, String textValue) {
		waitForElementInvisible(driver, AbstractPageNopCommerceUI.DYNAMIC_LINK, classValue, textValue);
		return isElementUnDisplayed(driver, AbstractPageNopCommerceUI.DYNAMIC_LINK, classValue, textValue);
	}

	public void clickToNopCommerceRadioButtonByText(WebDriver driver, String textValue) {
		waitForElementClickable(driver, AbstractPageNopCommerceUI.DYNAMIC_RADIO_BY_LABEL, textValue);
		clickToElement(driver, AbstractPageNopCommerceUI.DYNAMIC_RADIO_BY_LABEL, textValue);
	}

	public void checkOnNopCommerceCheckboxByText(WebDriver driver, String textValue) {
		waitForElementVisible(driver, AbstractPageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, textValue);
		checkTheCheckbox(driver, AbstractPageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, textValue);
	}

	public String getNopCommerceProductInforByColumn(WebDriver driver, String productName, int columnNumber) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		index = findElementsByXpath(driver, AbstractPageNopCommerceUI.DYNAMIC_PRODUCT_INFOR, productName).size() + 1;
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		// get Index base on productName and input to locator (row and column) to get new locator
		locator = "//tr[" + index + "]/td[" + columnNumber + "]/span";
		waitForElementVisible(driver, locator);
		return getTextElement(driver, locator);
	}

	public String getNopCommerceProductDescriptionByColumn(WebDriver driver, String productName, int columnNumber, String textValue) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		index = findElementsByXpath(driver, AbstractPageNopCommerceUI.DYNAMIC_PRODUCT_INFOR, productName).size() + 1;
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		// get Index base on productName and input to locator (row and column) to get new locator
		locator = "//tr[" + index + "]/td[" + columnNumber + "]/div[contains(.,'%s')]";
		waitForElementVisible(driver, locator, textValue);
		return getTextElement(driver, locator, textValue);
	}

	public void unCheckOnNopCommerceCheckboxByText(WebDriver driver, String textValue) {
		waitForElementVisible(driver, AbstractPageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, textValue);
		unCheckTheCheckbox(driver, AbstractPageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, textValue);
	}

	public String getNopCommerceProductQtyByColumn(WebDriver driver, String productName, int columnNumber) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		index = findElementsByXpath(driver, AbstractPageNopCommerceUI.DYNAMIC_PRODUCT_INFOR, productName).size() + 1;
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		locator = "//tbody/tr[" + index + "]/td[" + columnNumber + "]/input";
		waitForElementVisible(driver, locator);
		return getAttributeValue(driver, locator, "value");
	}

	public void clickToNopCommerceProductButtonIconByColumn(WebDriver driver, String productName, int columnNumber) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		index = findElementsByXpath(driver, AbstractPageNopCommerceUI.DYNAMIC_PRODUCT_INFOR, productName).size() + 1;
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		locator = "//tbody/tr[" + index + "]/td[" + columnNumber + "]/button";
		waitForElementClickable(driver, locator);
		clickToElement(driver, locator);
	}

	 public void inputNopcommerceProductQuantity(WebDriver driver, String qty){
		waitForElementVisible(driver, AbstractPageNopCommerceUI.QUANTITY_TEXTBOX);
		setAttributeValueByJS(driver, AbstractPageNopCommerceUI.QUANTITY_TEXTBOX,"value", qty);
    }

	public void setNopCommerceValueForTextBoxByID(WebDriver driver, String idValue, String textValue) {
		waitForElementVisible(driver, AbstractPageNopCommerceUI.DYNAMIC_TEXTBOX, idValue);
		setAttributeValueByJS(driver, AbstractPageNopCommerceUI.DYNAMIC_TEXTBOX, "value", textValue, idValue);
	}
}
