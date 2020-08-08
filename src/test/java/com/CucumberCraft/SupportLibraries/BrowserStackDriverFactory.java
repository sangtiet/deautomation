package com.CucumberCraft.SupportLibraries;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

//import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.log4testng.Logger;

public class BrowserStackDriverFactory {

	private static Properties mobileProperties = Settings.getInstance();

	static Logger log = Logger.getLogger(BrowserStackDriverFactory.class);

	private BrowserStackDriverFactory() {
		// To prevent external instantiation of this class
	}

	public static WebDriver getBrowserStackRemoteWebDriverForDesktop(SeleniumTestParameters testParameters) {

		RemoteWebDriver driver = null;
		String browserName = getBrowerName(testParameters);
		String username = mobileProperties.getProperty("browserstack.user");
		String accessKey = mobileProperties.getProperty("browserstack.key");
		String server = mobileProperties.getProperty("browserstack.server");
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

		desiredCapabilities.setCapability("browserName", browserName);

		try {
			driver = new RemoteWebDriver(new URL("http://" + username + ":" + accessKey + "@" + server + "/wd/hub"),
					desiredCapabilities);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}

	private static String getBrowerName(SeleniumTestParameters testParameters) {
		String browserName = null;
		if (testParameters.getBrowser().equals(Browser.CHROME)) {
			browserName = "Chrome";
		} else if (testParameters.getBrowser().equals(Browser.FIREFOX)) {
			browserName = "Firefox";
		} else if (testParameters.getBrowser().equals(Browser.INTERNET_EXPLORER)) {
			browserName = "Internet Explorer";
		}
		return browserName;
	}

	public static WebDriver getBrowserStackRemoteDriverForMobile(SeleniumTestParameters testParameters) {

		RemoteWebDriver driver = null;

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("user", mobileProperties.getProperty("PerfectoUser"));
		desiredCapabilities.setCapability("password", mobileProperties.getProperty("PerfectoPassword"));
		try {
			switch (testParameters.getMobileExecutionPlatform()) {

			case WEB_ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufacturer", testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model", testParameters.getModelName());
				}
				desiredCapabilities.setCapability("browserName", "Chrome");
				desiredCapabilities.setCapability("takesScreenshot", true);

				try {
					driver = new RemoteWebDriver(new URL(mobileProperties.getProperty("PerfectoHost")),
							desiredCapabilities);
				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}
				break;

			case WEB_IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufacturer", testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model", testParameters.getModelName());
				}
				// desiredCapabilities.setCapability("automationName",
				// "Appium");
				desiredCapabilities.setCapability("browserName", "Safari");
				desiredCapabilities.setCapability("takesScreenshot", true);
				try {
					driver = new RemoteWebDriver(new URL(mobileProperties.getProperty("PerfectoHost")),
							desiredCapabilities);

				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}
				break;

			default:

			}
		} catch (Exception ex) {
			log.error(ex.getMessage());

		}
		return driver;

	}
}
