package com.CucumberCraft.SupportLibraries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.CucumberCraft.PageObjects.BankLinkPage;
import com.CucumberCraft.PageObjects.ChoseLinkPage;
import com.CucumberCraft.PageObjects.HomePage;
import com.CucumberCraft.PageObjects.LoginPage;
import com.CucumberCraft.PageObjects.PreLoginPage;
import com.CucumberCraft.PageObjects.ZaloPayPinPage;
import com.CucumberCraft.StepDefinitions.CukeHooks;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/**
 * Class containing useful AppiumDriver utility functions
 * 
 */
@SuppressWarnings("rawtypes")
public class AppiumDriverUtil {

	private AppiumDriver driver;
	private Helper helper = TestController.getHelper();
	static Logger log = Logger.getLogger(AppiumDriverUtil.class);

	/**
	 * Constructor to initialize the {@link AppiumDriverUtil} object
	 * 
	 * @param driver The {@link AppiumDriver} object
	 */
	public AppiumDriverUtil(AppiumDriver p_driver) {
		driver = p_driver;
	}	

	public WebElement getElement(String elementName) throws Exception {
		String platformName = TestController.getTestParameters().getMobileExecutionPlatform().toString().toLowerCase();
		String jsonPath = "$.elements[?(@.name=='" + elementName + "')].locators." + platformName;
		String pageName = helper.extractPageNameFromElementname(elementName);
		File jsonFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\pages\\" + pageName + ".json");

		Configuration conf = Configuration.builder().jsonProvider(new GsonJsonProvider()).build();
		JsonArray ja = JsonPath.using(conf).parse(jsonFile).read(jsonPath);
		JsonObject jo = (JsonObject) ja.get(0);

		String selector = jo.getAsJsonObject().get("selector").toString().replaceAll("^\"+|\"+$", "");
		String strategy = jo.getAsJsonObject().get("strategy").toString().replaceAll("^\"+|\"+$", "");

		switch (strategy) {
		case "xpath":
			return driver.findElement(By.xpath(selector));
		case "id":
			return driver.findElement(By.id(selector));
		case "name":
			return driver.findElement(By.name(selector));
		case "cssSelector":
			return driver.findElement(By.cssSelector(selector));
		case "className":
			return driver.findElement(By.className(selector));
		case "linkText":
			return driver.findElement(By.linkText(selector));
		case "tagName":
			return driver.findElement(By.tagName(selector));
		case "partialLinkText":
			return driver.findElement(By.partialLinkText(selector));
		default:
			helper.writeStepFAIL("Unable to locate the element: " + elementName);
		}
		return null;
	}

	private String getMobileExecutionPlatform() {
		return TestController.getTestParameters().getMobileExecutionPlatform().toString().toUpperCase();
	}

	public void verifyPageShowsUp(String pageName) throws Exception {
		String platformName = TestController.getTestParameters().getMobileExecutionPlatform().toString().toLowerCase();
		String jsonPath = "$.detectors." + platformName;
		File jsonFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\pages\\" + pageName + ".json");

		Configuration conf = Configuration.builder().jsonProvider(new GsonJsonProvider()).build();
		String elementName = JsonPath.using(conf).parse(jsonFile).read(jsonPath).toString().replaceAll("^\"+|\"+$", "");
		getElement(elementName);
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

	public void swipeDown(int howManySwipes) {
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
			if (getMobileExecutionPlatform().equals("ANDROID"))
				driver.findElement(By.xpath(
						"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[1]"))
						.click();
			else if (getMobileExecutionPlatform().equals("IOS"))
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
				return getElement(elementName);
			} catch (Exception e) {
				// TODO Auto-generated catch block

			}
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		helper.writeStepFAIL("Element <" + elementName + "> not found");
		return null;
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	@SuppressWarnings("unchecked")
	public void clickElementByVisibleText(String text) {
		List<WebElement> lstElem = null;
		lstElem = driver.findElements(By.xpath("//*[@text='" + text + "']"));

		if (lstElem.size() == 1)
			lstElem.get(0).click();
		else
			helper.writeStepFAIL("Element not found or match more than one");
	}

	public boolean isElementPresent(String locator) throws Exception {
		if (!getElement(locator).isDisplayed())
			return false;
		else
			return true;
	}

	public void verifyElementPresent(String locator) throws Exception {
		if (!getElement(locator).isDisplayed())
			helper.writeStepFAIL("Element is NOT present");
		else
			log.debug("Element <" + locator + "> is present");
	}

	public void verifyElementNotPresent(String locator) throws Exception {
		try {
			getElement(locator);
			helper.writeStepFAIL("Element is present");
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
	}

	public void wait(int secs) throws Exception {
		Thread.sleep(secs * 1000);
	}

	
}