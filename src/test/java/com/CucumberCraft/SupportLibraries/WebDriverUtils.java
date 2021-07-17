package com.CucumberCraft.SupportLibraries;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;

public class WebDriverUtils implements DriverUtils {

	private WebDriver driver;
	private Helper helper = TestController.getHelper();
	private WebElement element;

	public WebDriverUtils(WebDriver p_driver) {
		this.driver = p_driver;
	}

	@Override
	public WebElement getElement(String elementName) {
		// TODO Auto-generated method stub
		String jsonPath = "$.elements[?(@.name=='" + elementName + "')].locators.web";
		String pageName = helper.extractPageNameFromElementname(elementName);
		File jsonFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\pages\\" + pageName + ".json");

		Configuration conf = Configuration.builder().jsonProvider(new GsonJsonProvider()).build();
		JsonArray ja = null;
		try {
			ja = JsonPath.using(conf).parse(jsonFile).read(jsonPath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			helper.writeStepFAIL(e1.getMessage());
		}
		JsonObject jo = (JsonObject) ja.get(0);

		String selector = jo.getAsJsonObject().get("selector").toString().replaceAll("^\"+|\"+$", "");
		String strategy = jo.getAsJsonObject().get("strategy").toString().replaceAll("^\"+|\"+$", "");

		try {
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
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			helper.writeStepFAIL("Element <" + elementName + "> not found");
		}
		return null;
	}

	@Override
	public void clickOnElement(String elementName) {
		// TODO Auto-generated method stub
		element = getElement(elementName);
		element.clear();
	}

	@Override
	public void clickOnElementByVisibleText(String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clickOnElementByVisibleTextAtIndex(String text, int index) {
		// TODO Auto-generated method stub

	}

	@Override
	public void typeTextIntoElement(String text, String elementName) {
		// TODO Auto-generated method stub
		element = getElement(elementName);
		element.clear();
		element.sendKeys(text);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void goToUrl() {
		// TODO Auto-generated method stub

	}

	@Override
	public void navigateBack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void navigateForward() {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchToIframe() {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchToDefault() {
		// TODO Auto-generated method stub

	}

	@Override
	public void accessWebView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void scrollUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void scrollUpNTimes(int n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void scrollDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void scrollDownNTimes(int n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void scrollToElement(String elementName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assertPageShowUp(String pageName) {
		// TODO Auto-generated method stub
		String jsonPath = "$.detectors.web";
		File jsonFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\pages\\" + pageName + ".json");

		Configuration conf = Configuration.builder().jsonProvider(new GsonJsonProvider()).build();
		String elementName = null;
		try {
			elementName = JsonPath.using(conf).parse(jsonFile).read(jsonPath).toString().replaceAll("^\"+|\"+$", "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			helper.writeStepFAIL(e.getMessage());
		}
		getElement(elementName);
	}

	@Override
	public void assertElementPresent(String elementName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assertElementNotPresent(String elementName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assertElementShowText(String elementName, String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assertElementContainText(String elementName, String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assertElementAttributeHasValue(String elementName, String attributeName, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void assertElementAttributeContainValue(String elementName, String attributeName, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void launchAUT() {
		// TODO Auto-generated method stub
		String Url = helper.getConfig("web.url");
		driver.get(Url);
	}

	@Override
	public boolean waitForJSandJQueryToLoad() {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					System.out.println(
							"Request = " + ((JavascriptExecutor) driver).executeScript("return jQuery.active"));
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					// no jQuery present
					System.out.println("no jQuery present");
					return true;
				}
			}
		};

		// wait for Java script to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					System.out.println("Request = "
							+ ((JavascriptExecutor) driver).executeScript("return document.readyState").toString());
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
							.equals("complete");
				} catch (Exception e) {
					// no jQuery present
					System.out.println("no jQuery present");
					return true;
				}
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

}
