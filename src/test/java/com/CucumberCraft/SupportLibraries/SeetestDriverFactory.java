package com.CucumberCraft.supportLibraries;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class SeetestDriverFactory {

	private static Properties mobileProperties = Settings.getInstance();
	static Logger log = Logger.getLogger(SeetestDriverFactory.class);

	private SeetestDriverFactory() {
		// To prevent external instantiation of this class
	}

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getSeetestDriver(SeleniumTestParameters testParameters)
			throws MalformedURLException, InterruptedException {

		AppiumDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		String accessKey = mobileProperties.getProperty("env.seetest.accesskey");
		desiredCapabilities.setCapability("testName", mobileProperties.getProperty("env.seetest.testname"));
		desiredCapabilities.setCapability("accessKey", accessKey);

		//desiredCapabilities.setCapability("instrumentApp", true);
		
		try {
			switch (testParameters.getMobileExecutionPlatform()) {
			case ANDROID:
				desiredCapabilities.setCapability("deviceQuery",
						"@os='android' and @version='" + testParameters.getOsVersion()
								+ "' and @category='PHONE' and @serialnumber='" + testParameters.getSerialNumber()
								+ "'");
				if (mobileProperties.getProperty("InstallApp").equals("true"))
					desiredCapabilities.setCapability(MobileCapabilityType.APP,
							"cloud:" + mobileProperties.getProperty("app.android.main_package") + "/"
									+ mobileProperties.getProperty("app.android.main_activity"));
				else {
					desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
							"cloud:" + mobileProperties.getProperty("app.android.main_package"));
					desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
							mobileProperties.getProperty("app.android.main_activity"));
				}
				driver = new AndroidDriver<>(new URL(mobileProperties.getProperty("env.seetest.host")),
						desiredCapabilities);
				break;

			case IOS:
				desiredCapabilities.setCapability("deviceQuery",
						"@os='ios' and @version='" + testParameters.getOsVersion()
								+ "' and @category='PHONE' and @serialnumber='" + testParameters.getSerialNumber()
								+ "'");
				if (mobileProperties.getProperty("InstallApp").equals("true"))
					desiredCapabilities.setCapability(MobileCapabilityType.APP,
							"cloud:" + mobileProperties.getProperty("app.ios.bundleid"));
				else
					desiredCapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID,
							mobileProperties.getProperty("app.ios.bundleid"));
				driver = new IOSDriver<>(new URL(mobileProperties.getProperty("env.seetest.host")), desiredCapabilities);
				break;

			case WEB_ANDROID:

//				desiredCapabilities.setCapability("platformName", "Android");
//				desiredCapabilities.setCapability("deviceName", deviceName);
//				// desiredCapabilities.setCapability("udid",deviceName);
//				desiredCapabilities.setCapability("platformVersion", version);
//				desiredCapabilities.setCapability("browserName", "chrome");
				break;

			case WEB_IOS:

//				desiredCapabilities.setCapability("platformName", "ios");
//				desiredCapabilities.setCapability("platformVersion", version);
//				desiredCapabilities.setCapability("deviceName", deviceName);
//				// desiredCapabilities.setCapability("udid",deviceName);
//				desiredCapabilities.setCapability("automationName", "Appium");
//				desiredCapabilities.setCapability("browserName", "Safari");
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	public static WebDriver getSeetestRemoteWebDriver(MobileExecutionPlatform executionPlatform, String deviceName,
			String version, Boolean installApp, String appiumURL) {

		WebDriver driver = null;
		mobileProperties = Settings.getInstance();
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		try {
			switch (executionPlatform) {

			case ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("platformVersion", version);
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
}
