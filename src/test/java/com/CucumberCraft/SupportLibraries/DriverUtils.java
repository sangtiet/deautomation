package com.CucumberCraft.SupportLibraries;

import org.openqa.selenium.WebElement;

public interface DriverUtils {
	WebElement getElement(String elementName);
	void clickOnElement(String elementName);
	void clickOnElementByVisibleText(String text);
	void clickOnElementByVisibleTextAtIndex(String text, int index);
	void typeTextIntoElement(String text, String elementName);
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
	
	void assertPageShowUp(String pageName);
	void assertElementPresent(String elementName);
	void assertElementNotPresent(String elementName);
	void assertElementShowText(String elementName, String text);
	void assertElementContainText(String elementName, String text);
	void assertElementAttributeHasValue(String elementName, String attributeName, String value);
	void assertElementAttributeContainValue(String elementName, String attributeName, String value);
}
