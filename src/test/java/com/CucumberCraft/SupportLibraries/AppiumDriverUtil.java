package com.CucumberCraft.SupportLibraries;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.CucumberCraft.PageObjects.HomePage;
import com.CucumberCraft.PageObjects.LoginZaloPayPage;
import com.CucumberCraft.PageObjects.ZaloPayPinPage;

import io.appium.java_client.AppiumDriver;
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
		else
			TestController.getHelper().writeStepFAIL("Unable to locate the element: " + locator);
		return null;
	}

	public boolean verifyPageShowsUp(String pageName) {
		String locator = null;
		String platformName = TestController.getTestParameters().getMobileExecutionPlatform().toString().toLowerCase();

		switch (pageName) {

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

	public WebElement scrollToFindElement(String scroll, String elementName, int retry) throws Exception {
		int i = 0;
		while (i < retry) {
			swipeUp(0.8, 0.1, 0.5, 2000);
			return getWebElement(elementName);
		}
		TestController.getHelper().writeStepFAIL("Element <" + elementName + "> not found");
		return null;
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void navigateBackUntil(String pageName) {
		driver.navigate().back();
	}
}