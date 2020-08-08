package com.CucumberCraft.SupportLibraries;

import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.log4testng.Logger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class AppiumDriverFactory {

	private static Properties mobileProperties;
	static Logger log = Logger.getLogger(AppiumDriverFactory.class);

	private AppiumDriverFactory() {
	}

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getAppiumDriver(MobileExecutionPlatform executionPlatform, String deviceName,
			String version, Boolean installApp) throws Exception {

		AppiumDriver driver = null;
		mobileProperties = Settings.getInstance();

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		switch (executionPlatform) {

		case ANDROID:
			desiredCapabilities.setCapability("platformName", "Android");
			desiredCapabilities.setCapability("deviceName", deviceName);
			desiredCapabilities.setCapability("udid", deviceName);
			desiredCapabilities.setCapability("platformVersion", version);
			desiredCapabilities.setCapability("appPackage", mobileProperties.getProperty("Application_Package_Name"));
			desiredCapabilities.setCapability("appActivity",
					mobileProperties.getProperty("Application_MainActivity_Name"));
			desiredCapabilities.setCapability("noReset", "true");
			desiredCapabilities.setCapability("noSign", "true");
			desiredCapabilities.setCapability("autoGrantPermissions", "true");
			desiredCapabilities.setCapability("automationName", mobileProperties.getProperty("automationName"));

			driver = new AndroidDriver(new URL("http://0.0.0.0:4724/wd/hub"), desiredCapabilities);
			break;

		case IOS:
			desiredCapabilities.setCapability("platformName", "ios");
			desiredCapabilities.setCapability("platformVersion", version);
			desiredCapabilities.setCapability("deviceName", deviceName);
			desiredCapabilities.setCapability("udid", deviceName);
			desiredCapabilities.setCapability("bundleId", mobileProperties.getProperty("iPhoneBundleID"));
			desiredCapabilities.setCapability("newCommandTimeout", 120);

			driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
			break;

		case WEB_ANDROID:
			desiredCapabilities.setCapability("platformName", "Android");
			desiredCapabilities.setCapability("deviceName", deviceName);
			// desiredCapabilities.setCapability("udid",deviceName);
			desiredCapabilities.setCapability("platformVersion", version);
			desiredCapabilities.setCapability("browserName", "chrome");

			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
			break;

		case WEB_IOS:
			desiredCapabilities.setCapability("platformName", "ios");
			desiredCapabilities.setCapability("platformVersion", version);
			desiredCapabilities.setCapability("deviceName", deviceName);
			// desiredCapabilities.setCapability("udid",deviceName);
			desiredCapabilities.setCapability("automationName", "Appium");
			desiredCapabilities.setCapability("browserName", "Safari");

			driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
			break;

		default:

		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
}
