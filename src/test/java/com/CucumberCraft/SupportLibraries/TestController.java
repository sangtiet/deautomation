package com.CucumberCraft.SupportLibraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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
	private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();	
	private static ThreadLocal<SeleniumTestParameters> testParameters = new ThreadLocal<SeleniumTestParameters>();
	private static ThreadLocal<Helper> helper = new ThreadLocal<Helper>();

	static Logger log;

	static {
		log = Logger.getLogger(TestController.class);
	}

	// AppiumDriver Object Creation

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getAppiumDriver() {
		try {
			if (driver.get() == null) {
				// this is need when running tests from IDE
				log.info("Thread has no WedDriver, creating new one");
				setDriver(DriverFactory.createInstance(getTestParameters()));
			}
			log.debug("Getting instance of remote driver" + driver.get().getClass());
			return driver.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return null;
	}	

	public static WebDriver getWebDriver() {
		try {
			if (webDriver.get() == null) {
				// this is need when running tests from IDE
				log.info("Thread has no WedDriver, creating new one");
				setDriver(DriverFactory.createInstanceWebDriver(getTestParameters()));
			}
			log.debug("Getting instance of remote driver" + webDriver.get().getClass());
			return webDriver.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static void setDriver(AppiumDriver p_driver) {
		try {
			driver.set(p_driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public static void setDriver(WebDriver driver) {
		webDriver.set(driver);
	}
	
	public static void setTestParameters(SeleniumTestParameters p_testParameters) {
		testParameters.set(p_testParameters);

	}

	public static SeleniumTestParameters getTestParameters() {
		return testParameters.get();
	}

	public static void endAppiumDriver() {
		try {			
			driver.get().quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public static void endWebDriver() {
		try {
			webDriver.get().close();
			webDriver.get().quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public static Helper getHelper() {
		return helper.get();
	}

	public static void setHelper(Helper p_helper) {
		helper.set(p_helper);
	}
}