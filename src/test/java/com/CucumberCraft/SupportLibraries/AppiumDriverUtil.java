package com.CucumberCraft.SupportLibraries;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import com.CucumberCraft.PageObjects.*;

import io.appium.java_client.AppiumDriver;

/**
 * Class containing useful WebDriver utility functions
 * 
 */
@SuppressWarnings("rawtypes")
public class AppiumDriverUtil {

	private AppiumDriver driver;
	private final int TIMEOUT = 10;
	private WebElement element;

	/**
	 * Constructor to initialize the {@link AppiumDriverUtil} object
	 * 
	 * @param driver The {@link AppiumDriver} object
	 */
	public AppiumDriverUtil(AppiumDriver p_driver) {
		driver = p_driver;
	}

	public WebElement getWebElement(String elementName) throws Exception {
		String locator = TestController.getHelper().getElementLocator(elementName, elementName.split("_")[0]);

		if (locator.startsWith("xpath"))
			return driver.findElement(By.xpath(locator.replace("xpath=", "")));
		else if (locator.startsWith("id"))
			return driver.findElement(By.id(locator.replace("id=", "")));
		else if (locator.startsWith("name"))
			return driver.findElement(By.name(locator.replace("name=", "")));
		else
			TestController.getHelper().writeStepFAIL("Unable to locate the element: " + locator);
		return null;
	}

	public boolean verifyPageShowsUp(String pageName) {
		String locator = null;

		switch (pageName) {
		case "LOGIN_ZALOPAY_PAGE":
			LoginZaloPayPage LZP = new LoginZaloPayPage();
			locator = LZP.ZALOPAY_LOGO;
			break;
			
		case "ZALOPAY_PIN_PAGE":
			ZaloPayPinPage ZPP = new ZaloPayPinPage();
			locator = ZPP.ZALOPAY_PIN;
			break;
			
		case "HOME_PAGE":
			HomePage HP = new HomePage();
			locator = HP.MAINMENU_TOP;
			break;

		default: // Optional
			TestController.getHelper().writeStepFAIL("Unable to verify page shows up: " + pageName);
		}

		try {
			element = getWebElement(locator);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	public void mobileSwipe(String direction) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", direction.toLowerCase());
		js.executeScript("mobile: scroll", scrollObject);
	}

	public void dismissAlert() {
		try {
			driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[1]")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	
	public void tapOnNumberPad(String value) {
		char[] chars = value.toCharArray();
		for (char c : chars) {
			driver.findElement(By.xpath("//*[@text='" + c + "']")).click();
		}
	}

	public WebElement scrollToFindElement(String scroll, String elementName, int retry) throws Exception {
		TouchActions action = new TouchActions(driver);
		WebElement scrollElem = getWebElement(scroll);

		int i = 0;
		while (i < retry) {
			action.scroll(scrollElem, 10, 100);
			action.perform();
			return getWebElement(elementName);
		}
		TestController.getHelper().writeStepFAIL("Unable to scroll then find the element: " + elementName);
		return null;
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void navigateBackUntil(String pageName) {
		driver.navigate().back();
	}
}