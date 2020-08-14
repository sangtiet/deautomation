package com.CucumberCraft.SupportLibraries;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.CucumberCraft.PageObjects.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

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
		else if (locator.startsWith("text"))
			return driver.findElement(
					MobileBy.AndroidUIAutomator(("new UiSelector().text(\"" + locator.replace("text=", "") + "\")")));
		else
			TestController.getHelper().writeStepFAIL("Unable to locate the element: " + locator);
		return null;
	}

	public boolean verifyPageShowsUp(String pageName) {
		String locator = null;
		String platformName = TestController.getTestParameters().getMobileExecutionPlatform().toString().toLowerCase();

		switch (pageName) {

		case "BANK_LINK_PAGE":
			BankLinkPage BLP = new BankLinkPage();

			if (platformName.equals("android"))
				locator = BLP.PAGE_INDICATOR_ANDROID;
			else if (platformName.equals("ios"))
				locator = BLP.PAGE_INDICATOR_IOS;
			break;
		
		case "LOGIN_ZALOPAY_PAGE":
			LoginZaloPayPage LZP = new LoginZaloPayPage();

			if (platformName.equals("android"))
				locator = LZP.PAGE_INDICATOR_ANDROID;
			else if (platformName.equals("ios"))
				locator = LZP.PAGE_INDICATOR_IOS;
			break;

		case "ZALOPAY_PIN_PAGE":
			ZaloPayPinPage ZPP = new ZaloPayPinPage();

			if (platformName.equals("android"))
				locator = ZPP.PAGE_INDICATOR_ANDROID;
			else if (platformName.equals("ios"))
				locator = ZPP.PAGE_INDICATOR_IOS;
			break;

		case "CHOSE_LINK_PAGE":
			ChoseLinkPage CLP = new ChoseLinkPage();

			if (platformName.equals("android"))
				locator = CLP.PAGE_INDICATOR_ANDROID;
			else if (platformName.equals("ios"))
				locator = CLP.PAGE_INDICATOR_IOS;
			break;

		case "HOME_PAGE":
			HomePage HP = new HomePage();

			if (platformName.equals("android"))
				locator = HP.PAGE_INDICATOR_ANDROID;
			else if (platformName.equals("ios"))
				locator = HP.PAGE_INDICATOR_IOS;
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

	public void swipeUp(double startPercentage, double finalPercentage, double anchorPercentage, int duration) {
		Dimension size = driver.manage().window().getSize();
		int anchor = (int) (size.width * anchorPercentage);
		int startPoint = (int) (size.height * startPercentage);
		int endPoint = (int) (size.height * finalPercentage);
		getTouchAction().press(PointOption.point(anchor, startPoint))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
				.moveTo(PointOption.point(anchor, endPoint)).release().perform();
	}

	public void swipDown(int howManySwipes) {
		Dimension size = driver.manage().window().getSize();
		// calculate coordinates for vertical swipe
		int startVerticalY = (int) (size.height * 0.8);
		int endVerticalY = (int) (size.height * 0.21);
		int startVerticalX = (int) (size.width / 2.1);
		try {
			for (int i = 1; i <= howManySwipes; i++) {
				new TouchAction<>(driver).press(PointOption.point(startVerticalX, endVerticalY))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
						.moveTo(PointOption.point(startVerticalX, startVerticalY)).release().perform();
			}
		} catch (Exception e) {
			// print error or something
		}
	}

	private TouchAction getTouchAction() {
		return new TouchAction(driver);
	}

	public void dismissAlert() {
		try {
			String platformName = TestController.getTestParameters().getMobileExecutionPlatform().toString()
					.toLowerCase();
			if (platformName.equals("android"))
				driver.findElement(By.xpath(
						"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[1]"))
						.click();
			else if (platformName.equals("ios"))
				driver.findElement(By.xpath("/ios")).click();

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

	public WebElement scrollToFindElement(String elementName, int retry) throws Exception {
		int i = 0;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		while (i < retry) {
			swipeUp(0.8, 0.1, 0.5, 2000);
			try {
				return getWebElement(elementName);
			} catch (Exception e) {
				// TODO Auto-generated catch block

			}
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		TestController.getHelper().writeStepFAIL("Element <" + elementName + "> not found");
		return null;
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void navigateBackUntil(String pageName) {
		driver.navigate().back();
	}

	@SuppressWarnings("unchecked")
	public void clickElementByVisibleText(String text) {
		String platformName = TestController.getTestParameters().getMobileExecutionPlatform().toString().toLowerCase();
		List<WebElement> lstElem = null;

		if (platformName.equals("android"))
			lstElem = driver.findElements(MobileBy.AndroidUIAutomator(("new UiSelector().text(\"" + text + "\")")));
		else if (platformName.equals("ios"))
			lstElem = driver.findElements(MobileBy.IosUIAutomation(("new UiSelector().text(\"" + text + "\")")));

		if (lstElem.size() == 1)
			lstElem.get(0).click();
		else
			TestController.getHelper().writeStepFAIL("Element not found or match more than one");
	}
}