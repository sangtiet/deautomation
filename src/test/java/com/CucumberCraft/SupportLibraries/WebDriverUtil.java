package com.CucumberCraft.supportLibraries;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

/**
 * Class containing useful WebDriver utility functions
 * 
 */
public class WebDriverUtil {
	private WebDriver driver;
	private WebDriverWait driverWait = null;
	private JavascriptExecutor executor = null;
	private Actions builder = null;
	private final int TIMEOUT = 10;

	/**
	 * Constructor to initialize the {@link WebDriverUtil} object
	 * 
	 * @param driver The {@link WebDriver} object
	 */
	public WebDriverUtil(WebDriver p_driver) {
		driver = p_driver;
		executor = (JavascriptExecutor) driver;
		builder = new Actions(driver);
	}

	public void refreshCurrentPage() {
		driver.navigate().refresh();
	}
	
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}

	public void goToURL(String URL) {
		driver.get(URL);
	}

	public void switchToIframe(WebElement iframe) {
		driver.switchTo().frame(iframe);
	}

	public void switchToDefault() {
		driver.switchTo().defaultContent();
	}

	public void selectListItem(By by, String item) {
		Select dropDownList = new Select(driver.findElement(by));
		dropDownList.selectByVisibleText(item);
	}

	public Boolean isTextPresent(String textPattern) {
		if (driver.findElement(By.cssSelector("BODY")).getText().matches(textPattern)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean isAlertPresent(long timeOutInSeconds) {
		try {
			new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (TimeoutException ex) {
			return false;
		}
	}

	public void waitForPageLoad(int timeout) {
		Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				System.out.println("Current Window State       : "
						+ String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	public void waitUntilElementVisible(String locator, int timeOutInSec) {
		driverWait = new WebDriverWait(driver, timeOutInSec);
		if (locator.startsWith("xpath"))
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.replace("xpath=", ""))));
		else if (locator.startsWith("id"))
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator.replace("id=", ""))));
		else if (locator.startsWith("name"))
			driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator.replace("name=", ""))));
		else if (locator.startsWith("cssSelector"))
			driverWait.until(
					ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator.replace("cssSelector=", ""))));
		else if (locator.startsWith("className"))
			driverWait.until(
					ExpectedConditions.visibilityOfElementLocated(By.className(locator.replace("className=", ""))));
		else if (locator.startsWith("linkText"))
			driverWait.until(
					ExpectedConditions.visibilityOfElementLocated(By.linkText(locator.replace("linkText=", ""))));
		else if (locator.startsWith("partialLinkText"))
			driverWait.until(ExpectedConditions
					.visibilityOfElementLocated(By.partialLinkText(locator.replace("partialLinkText=", ""))));
		else
			TestController.getHelper().writeStepFAIL("Unable to locate web element: " + locator);
	}

	public void waitUntilElementLocated(String locator, int timeOutInSec) {
		driverWait = new WebDriverWait(driver, timeOutInSec);
		if (locator.startsWith("xpath"))
			driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator.replace("xpath=", ""))));
		else if (locator.startsWith("id"))
			driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator.replace("id=", ""))));
		else if (locator.startsWith("name"))
			driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name(locator.replace("name=", ""))));
		else if (locator.startsWith("cssSelector"))
			driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator.replace("cssSelector=", ""))));
		else if (locator.startsWith("className"))
			driverWait.until(
					ExpectedConditions.presenceOfElementLocated(By.className(locator.replace("className=", ""))));
		else if (locator.startsWith("linkText"))
			driverWait
					.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator.replace("linkText=", ""))));
		else if (locator.startsWith("partialLinkText"))
			driverWait.until(ExpectedConditions
					.presenceOfElementLocated(By.partialLinkText(locator.replace("partialLinkText=", ""))));
		else
			TestController.getHelper().writeStepFAIL("Unable to locate web element: " + locator);
	}

	public void waitUntilElementNotLocated(String locator, int timeOutInSec) {
		try {
			waitUntilElementLocated(locator, timeOutInSec);
			Assert.fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return;
		}
	}	

	public void waitForAlert(int timeout) {
		driverWait = new WebDriverWait(driver, timeout);
		driverWait.until(ExpectedConditions.alertIsPresent());
	}

	public WebElement getWebElement(String locator) {
		if (locator.startsWith("xpath"))
			return driver.findElement(By.xpath(locator.replace("xpath=", "")));
		else if (locator.startsWith("id"))
			return driver.findElement(By.id(locator.replace("id=", "")));
		else if (locator.startsWith("name"))
			return driver.findElement(By.name(locator.replace("name=", "")));
		else if (locator.startsWith("cssSelector"))
			return driver.findElement(By.cssSelector(locator.replace("cssSelector=", "")));
		else if (locator.startsWith("className"))
			return driver.findElement(By.className(locator.replace("className=", "")));
		else if (locator.startsWith("linkText"))
			return driver.findElement(By.linkText(locator.replace("linkText=", "")));
		else if (locator.startsWith("partialLinkText"))
			return driver.findElement(By.partialLinkText(locator.replace("partialLinkText=", "")));
		else
			TestController.getHelper().writeStepFAIL("Unable to locate web element: " + locator);
		return null;
	}

	public List<WebElement> getListOfWebElement(String locator) {
		if (locator.startsWith("xpath"))
			return driver.findElements(By.xpath(locator.replace("xpath=", "")));
		else if (locator.startsWith("id"))
			return driver.findElements(By.id(locator.replace("id=", "")));
		else if (locator.startsWith("name"))
			return driver.findElements(By.name(locator.replace("name=", "")));
		else if (locator.startsWith("cssSelector"))
			return driver.findElements(By.cssSelector(locator.replace("cssSelector=", "")));
		else if (locator.startsWith("className"))
			return driver.findElements(By.className(locator.replace("className=", "")));
		else if (locator.startsWith("linkText"))
			return driver.findElements(By.linkText(locator.replace("linkText=", "")));
		else if (locator.startsWith("partialLinkText"))
			return driver.findElements(By.partialLinkText(locator.replace("partialLinkText=", "")));
		else
			TestController.getHelper().writeStepFAIL("Unable to locate web element: " + locator);
		return null;
	}

	public void clickByJS(WebElement elem) {
		executor.executeScript("arguments[0].click();", elem);
	}

	public void clickByActions(WebElement elem) {
		builder.moveToElement(elem).click(elem);
		builder.perform();
	}

	public void sendKeysFromClipboard(WebElement elem, String value) {
		setClipboardContents(value);
		elem.sendKeys(Keys.CONTROL + "v");
	}

	public void pressTABkey() {
		builder.sendKeys(Keys.TAB).build().perform();
	}

	public void pressENTERkey() {
		builder.sendKeys(Keys.ENTER).build().perform();
	}

	public void pasteText(String text) {
		setClipboardContents(text);
		builder.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
	}

	private void setClipboardContents(String text) {
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	public void sendKeysByJS(WebElement elem, String value) {
		executor.executeScript("arguments[0].setAttribute('value', '" + value + "')", elem);
	}

	public void scrollDown(int pixel) {
		executor.executeScript("window.scrollBy(0," + String.valueOf(pixel) + ")");
	}

	public void scrollToElement(WebElement elem) {
		executor.executeScript("arguments[0].scrollIntoView();", elem);
	}

	public void dragAndDrop(WebElement source, WebElement target) {
		builder.dragAndDrop(source, target).build().perform();
	}

	public void hover(WebElement elem) {
		builder.moveToElement(elem).perform();
	}

	public void sendValueToNumberPad(String p_value) throws Exception {
		builder.sendKeys("c").build().perform();

		for (int i = 0, n = p_value.length(); i < n; i++) {
			char c = p_value.charAt(i);

			switch (c) {
			case '1':
				builder.sendKeys(Keys.NUMPAD1).build().perform();
				break;
			case '2':
				builder.sendKeys(Keys.NUMPAD2).build().perform();
				break;
			case '3':
				builder.sendKeys(Keys.NUMPAD3).build().perform();
				break;
			case '4':
				builder.sendKeys(Keys.NUMPAD4).build().perform();
				break;
			case '5':
				builder.sendKeys(Keys.NUMPAD5).build().perform();
				break;
			case '6':
				builder.sendKeys(Keys.NUMPAD6).build().perform();
				break;
			case '7':
				builder.sendKeys(Keys.NUMPAD7).build().perform();
				break;
			case '8':
				builder.sendKeys(Keys.NUMPAD8).build().perform();
				break;
			case '9':
				builder.sendKeys(Keys.NUMPAD9).build().perform();
				break;
			case '0':
				builder.sendKeys(Keys.NUMPAD0).build().perform();
				break;
			}
		}
		builder.sendKeys(Keys.ENTER).build().perform();
		TestController.getHelper().wait(1);
	}
}