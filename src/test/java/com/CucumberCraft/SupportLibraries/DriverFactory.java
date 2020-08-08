package com.CucumberCraft.SupportLibraries;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

/**
 * DriverFactory which will create respective driver Object
 * 
 */
public class DriverFactory {

	static Logger log = Logger.getLogger(DriverFactory.class);

	@SuppressWarnings("rawtypes")
	public static AppiumDriver createInstance(SeleniumTestParameters testParameters) throws Throwable {
		AppiumDriver driver = null;
		switch (testParameters.getExecutionMode()) {

		case MOBILE:
			driver = AppiumDriverFactory.getAppiumDriver(testParameters.getMobileExecutionPlatform(),
					testParameters.getDeviceName(), testParameters.getOsVersion(), false);
			break;

		default:
			log.warn("No Such ExecutionMode Available, please modify accordingly");
		}
		return driver;
	}

	public static WebDriver createInstanceWebDriver(SeleniumTestParameters testParameters) throws Throwable {
		WebDriver driver = null;
		switch (testParameters.getExecutionMode()) {

		case LOCAL:
			driver = WebDriverFactory.getWebDriver(testParameters.getBrowser());
			break;

		case REMOTE:
			driver = WebDriverFactory.getRemoteWebDriver(testParameters.getBrowser());
			break;

		default:
			log.warn("No Such ExecutionMode Available, please modify accordingly");
			break;
		}
		return driver;
	}

}