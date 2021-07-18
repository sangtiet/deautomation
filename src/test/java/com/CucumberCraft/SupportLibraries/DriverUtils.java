package com.CucumberCraft.SupportLibraries;

import org.openqa.selenium.WebElement;

public interface DriverUtils {
	WebElement getElement(String elementName) throws Exception;

	void clickOnElement(String elementName) throws Exception;
	
	void clickOnElementByJS(String elementName) throws Exception;

	void typeTextIntoElement(String text, String elementName) throws Exception;
	
	void submitForm(String elementName) throws Exception;

	void launchAUT();

	void refresh();

	void goToUrl(String Url);

	void navigateBack();

	void navigateForward();

	void switchToIframe(String elementName) throws Exception;

	void switchToDefault();

	void accessWebView();

	void scrollDown(int pixel);

	void scrollDownNTimes(int pixel, int n);

	void scrollToElement(String elementName) throws Exception;
	
	void pressEnterKey();

	boolean waitForJSandJQueryToLoad();

	void assertPageShowUp(String pageName) throws Exception;

	void assertPageShowUpInGivenTimeSeconds(String pageName, int timeInSeconds) throws Exception;

	void assertElementIsPresent(String elementName) throws Exception;

	void assertElementIsPresentInGivenTimeSeconds(String elementName, int timeInSeconds) throws Exception;

	void assertElementIsNotPresent(String elementName);

	void assertElementIsNotPresentInGivenTimeSeconds(String elementName, int timeInSeconds) throws Exception;

	void assertElementShowText(String elementName, String text) throws Exception;

	void assertElementContainText(String elementName, String text) throws Exception;

	void assertElementAttributeHasValue(String elementName, String attributeName, String value) throws Exception;

	void assertElementAttributeContainValue(String elementName, String attributeName, String value) throws Exception;
}
