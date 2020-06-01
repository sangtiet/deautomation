package com.CucumberCraft.supportLibraries;

import io.appium.java_client.AppiumDriver;

//import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

/**
 * DriverFactory which will create respective driver Object
 * 
 */
public class DriverFactory {

	static Logger log = Logger.getLogger(DriverFactory.class);

	@SuppressWarnings("rawtypes")
	public static AppiumDriver createInstance(SeleniumTestParameters testParameters) {

		AppiumDriver driver = null;
		try {
			switch (testParameters.getExecutionMode()) {

			case MOBILE:
				driver = AppiumDriverFactory.getAppiumDriver(testParameters.getMobileExecutionPlatform(),
						testParameters.getDeviceName(), testParameters.getOsVersion(), false);
				break;
			case PERFECTO:

				driver = PerfectoDriverFactory.getPerfectoAppiumDriver(testParameters);

				break;

			default:
				log.warn("No Such ExecutionMode Available, please modify accordingly");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return driver;
	}

	public static WebDriver createInstanceWebDriver(SeleniumTestParameters testParameters) {
		WebDriver driver = null;
		try {
			switch (testParameters.getExecutionMode()) {

			case LOCAL:
				driver = WebDriverFactory.getWebDriver(testParameters.getBrowser());
				break;

			case REMOTE:
				driver = WebDriverFactory.getRemoteWebDriver(testParameters.getBrowser());
				break;
				
			case PERFECTO:
				driver = PerfectoDriverFactory.getPerfectoRemoteDriver(testParameters);
				break;

			case PERFECTODESKTOP:
				driver = PerfectoDriverFactory.getPerfectoRemoteWebDriverForDesktop(testParameters);
				break;

			case BROWSERSTACK:
				driver = BrowserStackDriverFactory.getBrowserStackRemoteWebDriverForDesktop(testParameters);
				break;
				
			default:
				log.warn("No Such ExecutionMode Available, please modify accordingly");
				break;
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return driver;
	}

}