package com.CucumberCraft.SupportLibraries;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

@SuppressWarnings("rawtypes")
public class AppiumDriverUtils implements DriverUtils{

	
	private AppiumDriver driver;
	private WebElement element;	
	private Helper helper = TestController.getHelper();

	public AppiumDriverUtils(AppiumDriver p_driver) {
		this.driver = p_driver;		
	}
	
	@Override
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
		case "ios_predicate":
			return driver.findElement(MobileBy.iOSNsPredicateString(selector));
		case "ios_class_chain":
			return driver.findElement(MobileBy.iOSClassChain(selector));
		case "android_ui_automator":
			return driver.findElement(MobileBy.AndroidUIAutomator(selector));
		default:
			helper.writeStepFAIL("Unable to locate the element: " + elementName);
		}
		return null;		
	}

	@Override
	public void clickOnElement(String elementName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clickOnElementByJS(String elementName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void typeTextIntoElement(String text, String elementName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitForm(String elementName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launchAUT() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToUrl(String Url) {
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
	public void switchToIframe(String elementName) throws Exception {
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
	public void scrollDown(int pixel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scrollDownNTimes(int pixel, int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scrollToElement(String elementName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pressEnterKey() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean waitForJSandJQueryToLoad() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void assertPageShowUp(String pageName) throws Exception {
		// TODO Auto-generated method stub
		String platformName = TestController.getTestParameters().getMobileExecutionPlatform().toString().toLowerCase();
		String jsonPath = "$.detectors." + platformName;
		File jsonFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\pages\\" + pageName + ".json");

		Configuration conf = Configuration.builder().jsonProvider(new GsonJsonProvider()).build();
		String elementName = JsonPath.using(conf).parse(jsonFile).read(jsonPath).toString().replaceAll("^\"+|\"+$", "");
		getElement(elementName);
	}

	@Override
	public void assertPageShowUpInGivenTimeSeconds(String pageName, int timeInSeconds) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertElementIsPresent(String elementName) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertElementIsPresentInGivenTimeSeconds(String elementName, int timeInSeconds) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertElementIsNotPresent(String elementName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertElementIsNotPresentInGivenTimeSeconds(String elementName, int timeInSeconds) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertElementShowText(String elementName, String text) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertElementContainText(String elementName, String text) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertElementAttributeHasValue(String elementName, String attributeName, String value)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertElementAttributeContainValue(String elementName, String attributeName, String value)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
