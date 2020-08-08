package com.CucumberCraft.SupportLibraries;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.log4testng.Logger;

public class AppiumDriverFactory {

	private static Properties mobileProperties;
	static Logger log = Logger.getLogger(AppiumDriverFactory.class);

	private AppiumDriverFactory() {
		// To prevent external instantiation of this class
	}

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getAppiumDriver(MobileExecutionPlatform executionPlatform, String deviceName,
			String version, Boolean installApp) {

		AppiumDriver driver = null;
		mobileProperties = Settings.getInstance();		
		
		int port = 4724;
		if(!AppiumServerJava.checkIfServerIsRunnning(port)) {
			AppiumServerJava.startServer();			
		} else {
			System.out.println("Appium Server already running on Port - " + port);
		}
		
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		try {
			switch (executionPlatform) {

			case ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				desiredCapabilities.setCapability("udid", deviceName);
				desiredCapabilities.setCapability("platformVersion", version);
				// desiredCapabilities.setCapability("app", appPath);
				desiredCapabilities.setCapability("appPackage",
						mobileProperties.getProperty("Application_Package_Name"));
				desiredCapabilities.setCapability("appActivity",
						mobileProperties.getProperty("Application_MainActivity_Name"));
				desiredCapabilities.setCapability("noReset", "true");
				desiredCapabilities.setCapability("noSign", "true");
				desiredCapabilities.setCapability("autoGrantPermissions", "true");
				desiredCapabilities.setCapability("automationName", mobileProperties.getProperty("automationName"));
				try {
					driver = new AndroidDriver(new URL("http://0.0.0.0:4724/wd/hub"), desiredCapabilities);
				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}
				break;

			case IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				// desiredCapabilities.setCapability("app",
				// properties.getProperty("iPhoneApplicationPath"));
				desiredCapabilities.setCapability("bundleId", mobileProperties.getProperty("iPhoneBundleID"));
				desiredCapabilities.setCapability("newCommandTimeout", 120);
				try {
					driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}
				break;

			case WEB_ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("browserName", "chrome");
				try {
					driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
				} catch (MalformedURLException e) {

				}
				break;

			case WEB_IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("automationName", "Appium");
				desiredCapabilities.setCapability("browserName", "Safari");
				try {
					driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);

				} catch (MalformedURLException e) {

				}
				break;

			default:

			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;

	}

	public static WebDriver getAppiumRemoteWebDriver(MobileExecutionPlatform executionPlatform, String deviceName,
			String version, Boolean installApp, String appiumURL) {

		WebDriver driver = null;
		mobileProperties = Settings.getInstance();
		String appPath = installApplication(installApp);
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		try {
			switch (executionPlatform) {

			case ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("app", appPath);
				desiredCapabilities.setCapability("appPackage",
						mobileProperties.getProperty("Application_Package_Name"));
				desiredCapabilities.setCapability("appActivity",
						mobileProperties.getProperty("Application_MainActivity_Name"));
				try {
					driver = new RemoteWebDriver(new URL(appiumURL), desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();

				}
				break;

			case IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				// desiredCapabilities.setCapability("app",
				// properties.getProperty("iPhoneApplicationPath"));
				desiredCapabilities.setCapability("bundleId", mobileProperties.getProperty("iPhoneBundleID"));
				desiredCapabilities.setCapability("newCommandTimeout", 120);

				try {
					driver = new RemoteWebDriver(new URL(appiumURL), desiredCapabilities);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			case WEB_ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("browserName", "Chrome");

				try {
					driver = new RemoteWebDriver(new URL(appiumURL), desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			case WEB_IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("automationName", "Appium");
				desiredCapabilities.setCapability("browserName", "Safari");
				try {
					driver = new RemoteWebDriver(new URL(appiumURL), desiredCapabilities);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			default:

			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		return driver;

	}

	private static String installApplication(Boolean installApp) {
		String appPath = "";

		mobileProperties = Settings.getInstance();
		try {
			if (installApp) {
				File path = new File(mobileProperties.getProperty("AndroidApplicationPath"));
				appPath = path.getAbsolutePath();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return appPath;
	}
}
