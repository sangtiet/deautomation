package com.CucumberCraft.SupportLibraries;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.CucumberCraft.PageObjects.LoginZaloPayPage;

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
}