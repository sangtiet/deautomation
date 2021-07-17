package com.CucumberCraft.SupportLibraries;

import org.openqa.selenium.WebElement;

public interface DriverUtils {
	WebElement getElement(String elementName) throws Exception;

	void clickOnElement(String elementName) throws Exception;

	void typeTextIntoElement(String text, String elementName) throws Exception;
	
	void submitForm(String elementName) throws Exception;

	void launchAUT();

	void refresh();

	void goToUrl();

	void navigateBack();

	void navigateForward();

	void switchToIframe();

	void switchToDefault();

	void accessWebView();

	void scrollUp();

	void scrollUpNTimes(int n);

	void scrollDown();

	void scrollDownNTimes(int n);

	void scrollToElement(String elementName);

	boolean waitForJSandJQueryToLoad();

	void assertPageShowUp(String pageName) throws Exception;

	void assertPageShowUpInGivenTimeSeconds(String pageName, int timeInSeconds);

	void assertElementIsPresent(String elementName) throws Exception;

	void assertElementIsPresentInGivenTimeSeconds(String elementName, int timeInSeconds) throws Exception;

	void assertElementIsNotPresent(String elementName);

	void assertElementIsNotPresentInGivenTimeSeconds(String elementName, int timeInSeconds);

	void assertElementShowText(String elementName, String text);

	void assertElementContainText(String elementName, String text);

	void assertElementAttributeHasValue(String elementName, String attributeName, String value);

	void assertElementAttributeContainValue(String elementName, String attributeName, String value);
}
