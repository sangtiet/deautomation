package com.CucumberCraft.SupportLibraries;

import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import io.appium.java_client.AppiumDriver;

/**
 * A generic WebDriver manager, which handles multiple instances of WebDriver.
 * 
 */
public class TestController {

	/*
	 * Used for Multithreading of WebDriver Object
	 */
	@SuppressWarnings("rawtypes")
	private static ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<AppiumDriver>();
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<SeleniumTestParameters> testParameters = new ThreadLocal<SeleniumTestParameters>();
	private static ThreadLocal<Helper> helper = new ThreadLocal<Helper>();

	static Logger log = Logger.getLogger(TestController.class);

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getAppiumDriver() {
		if (appiumDriver.get() == null) {
			log.info("Thread has no AppiumDriver, creating new one");
			setDriver(DriverFactory.createInstance(getTestParameters()));
		}
		log.debug("Getting instance of AppiumDriver" + appiumDriver.get().getClass());
		return appiumDriver.get();
	}

	public static WebDriver getWebDriver() {
		if (webDriver.get() == null) {
			log.info("Thread has no WebDriver, creating new one");
			setDriver(DriverFactory.createInstanceWebDriver(getTestParameters()));
		}
		log.debug("Getting instance of WebDriver" + webDriver.get().getClass());
		return webDriver.get();
	}

	@SuppressWarnings("rawtypes")
	public static void setDriver(AppiumDriver p_driver) {
		try {
			appiumDriver.set(p_driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
	}

	public static void setDriver(WebDriver driver) {
		try {
			webDriver.set(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
	}

	public static void setTestParameters(SeleniumTestParameters p_testParameters) {
		testParameters.set(p_testParameters);

	}

	public static SeleniumTestParameters getTestParameters() {
		return testParameters.get();
	}

	public static void endAppiumDriver() {
		try {
			appiumDriver.get().quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
	}

	public static void endWebDriver() {
		try {
			webDriver.get().close();
			webDriver.get().quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
	}

	public static Helper getHelper() {
		return helper.get();
	}

	public static void setHelper(Helper p_helper) {
		helper.set(p_helper);
	}
}