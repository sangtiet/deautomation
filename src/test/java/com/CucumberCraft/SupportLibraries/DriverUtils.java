package com.CucumberCraft.SupportLibraries;

import java.io.IOException;

import org.openqa.selenium.WebElement;

interface DriverUtils {
	WebElement getElement(String elementName) throws IOException;
	void clickOnElement(String elementName);
	void clickOnElementByVisibleText(String text);
	void clickOnElementByVisibleTextAtIndex(String text, int index);
	void typeTextIntoElement(String text, String elementName);
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
	
	void assertPageShowUp(String pageName) throws IOException;
	void assertElementPresent(String elementName);
	void assertElementNotPresent(String elementName);
	void assertElementShowText(String elementName, String text);
	void assertElementContainText(String elementName, String text);
	void assertElementAttributeHasValue(String elementName, String attributeName, String value);
	void assertElementAttributeContainValue(String elementName, String attributeName, String value);
}