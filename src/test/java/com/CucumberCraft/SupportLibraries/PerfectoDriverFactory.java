package com.CucumberCraft.supportLibraries;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

public class PerfectoDriverFactory {

	private static Properties mobileProperties = Settings.getInstance();

	static Logger log = Logger.getLogger(PerfectoDriverFactory.class);

	private PerfectoDriverFactory() {
		// To prevent external instantiation of this class
	}

	private static URL getUrl(String remoteUrl) {
		URL url = null;
		try {
			url = new URL(remoteUrl);
		} catch (MalformedURLException e) {
			log.error(e.getMessage());

		}
		return url;
	}

	private static DesiredCapabilities getPerfectoExecutionCapabilities() {

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setPlatform(Platform.ANY);
		desiredCapabilities.setJavascriptEnabled(true); // Pre-requisite for
														// remote execution

		mobileProperties = Settings.getInstance();
		desiredCapabilities.setCapability("user", mobileProperties.getProperty("PerfectoUser"));
		desiredCapabilities.setCapability("password", mobileProperties.getProperty("PerfectoPassword"));

		return desiredCapabilities;
	}

	/**
	 * Function to return the Perfecto MobileCloud {@link RemoteWebDriver}
	 * object based on the parameters passed
	 * 
	 * @param platformName
	 *            The device platform to be used for the test execution (iOS,
	 *            Android, etc.)
	 * @param platformVersion
	 *            The device platform version to be used for the test execution
	 * @param browser
	 *            The {@link Browser} to be used for the test execution
	 * @param remoteUrl
	 *            The Perfecto MobileCloud URL to be used for the test execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
	public static WebDriver getPerfectoRemoteWebDriverByDevicePlatform(String deviceId, String osVersionVersion,
			Browser browser, String remoteUrl, MobileExecutionPlatform executionPlatform) {
		String platformName = "";
		if (executionPlatform.equals("ANDROID")) {
			platformName = "Android";
		} else if (executionPlatform.equals("IOS")) {
			platformName = "ios";
		}
		DesiredCapabilities desiredCapabilities = getPerfectoExecutionCapabilities();
		desiredCapabilities.setBrowserName(browser.getValue());
		desiredCapabilities.setCapability("platformName", platformName);
		desiredCapabilities.setCapability("platformVersion", osVersionVersion);

		URL url = getUrl(remoteUrl);

		return new RemoteWebDriver(url, desiredCapabilities);
	}

	/**
	 * Function to return the Perfecto MobileCloud {@link RemoteWebDriver}
	 * object based on the parameters passed
	 * 
	 * @param manufacturer
	 *            The manufacturer of the device to be used for the test
	 *            execution (Samsung, Apple, etc.)
	 * @param model
	 *            The device model to be used for the test execution (Galaxy S6,
	 *            iPad Air, etc.)
	 * @param browser
	 *            The {@link Browser} to be used for the test execution
	 * @param remoteUrl
	 *            The Perfecto MobileCloud URL to be used for the test execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
	public static WebDriver getPerfectoRemoteWebDriverByDeviceModel(String manufacturer, String model, Browser browser,
			String remoteUrl) {
		DesiredCapabilities desiredCapabilities = getPerfectoExecutionCapabilities();
		desiredCapabilities.setCapability("manufacturer", manufacturer);
		desiredCapabilities.setCapability("model", model);

		URL url = getUrl(remoteUrl);

		return new RemoteWebDriver(url, desiredCapabilities);
	}

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getPerfectoAppiumDriver(SeleniumTestParameters testParameters) {

		AppiumDriver driver = null;

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("user", mobileProperties.getProperty("PerfectoUser"));
		desiredCapabilities.setCapability("password", mobileProperties.getProperty("PerfectoPassword"));
		try {
			switch (testParameters.getMobileExecutionPlatform()) {

			case ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufacturer", testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model", testParameters.getModelName());
				}
				desiredCapabilities.setCapability("appPackage",
						mobileProperties.getProperty("Application_Package_Name"));
				desiredCapabilities.setCapability("appActivity",
						mobileProperties.getProperty("Application_MainActivity_Name"));
				// desiredCapabilities.setCapability("app",
				// "PUBLIC:appium/apiDemos.apk");
				try {
					driver = new AndroidDriver(new URL(mobileProperties.getProperty("PerfectoHost")),
							desiredCapabilities);
				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}

				break;

			case IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufacturer", testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model", testParameters.getModelName());
				}
				desiredCapabilities.setCapability("newCommandTimeout", 120);
				desiredCapabilities.setCapability("bundleId", mobileProperties.getProperty("PerfecttoIosBundleID"));
				// desiredCapabilities.setCapability("app",
				// "PUBLIC:appium/apiDemos.ipa");

				try {
					driver = new IOSDriver(new URL(mobileProperties.getProperty("PerfectoHost")), desiredCapabilities);

				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}
				break;

			case WEB_ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufacturer", testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model", testParameters.getModelName());
				}
				desiredCapabilities.setCapability("browserName", "Chrome");

				try {
					driver = new AndroidDriver(new URL(mobileProperties.getProperty("PerfectoHost")),
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
				desiredCapabilities.setCapability("automationName", "Appium");
				desiredCapabilities.setCapability("browserName", "Safari");

				try {
					driver = new IOSDriver(new URL(mobileProperties.getProperty("PerfectoHost")), desiredCapabilities);

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

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getPerfectoAppiumDrivertoInstall(SeleniumTestParameters testParameters) {

		AppiumDriver driver = null;

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("user", mobileProperties.getProperty("PerfectoUser"));
		desiredCapabilities.setCapability("password", mobileProperties.getProperty("PerfectoPassword"));
		try {
			switch (testParameters.getMobileExecutionPlatform()) {

			case ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufacturer", testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model", testParameters.getModelName());
				}
				desiredCapabilities.setCapability("browserName", "Chrome");

				try {
					driver = new AndroidDriver(new URL(mobileProperties.getProperty("PerfectoHost")),
							desiredCapabilities);
					InstallFromWebSiteAnroid(driver);
				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}

				break;

			case IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufacturer", testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model", testParameters.getModelName());
				}
				desiredCapabilities.setCapability("automationName", "Appium");
				desiredCapabilities.setCapability("browserName", "Safari");

				try {
					driver = new IOSDriver(new URL(mobileProperties.getProperty("PerfectoHost")), desiredCapabilities);
					InstallFromWebSiteiOS(driver);

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

	@SuppressWarnings("rawtypes")
	public static String getPerfectoDeviceUdidForModel(SeleniumTestParameters testParameters) {
		AppiumDriver driver = null;

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("user", mobileProperties.getProperty("PerfectoUser"));
		desiredCapabilities.setCapability("password", mobileProperties.getProperty("PerfectoPassword"));
		try {
			switch (testParameters.getMobileExecutionPlatform()) {

			case ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");

				desiredCapabilities.setCapability("manufacturer", testParameters.getManuFacturerName());
				desiredCapabilities.setCapability("model", testParameters.getModelName());

				desiredCapabilities.setCapability("browserName", "Chrome");

				try {
					driver = new AndroidDriver(new URL(mobileProperties.getProperty("PerfectoHost")),
							desiredCapabilities);
				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}

				break;

			case IOS:

				desiredCapabilities.setCapability("platformName", "ios");

				desiredCapabilities.setCapability("manufacturer", testParameters.getManuFacturerName());
				desiredCapabilities.setCapability("model", testParameters.getModelName());

				desiredCapabilities.setCapability("automationName", "Appium");
				desiredCapabilities.setCapability("browserName", "Safari");

				try {
					driver = new IOSDriver(new URL(mobileProperties.getProperty("PerfectoHost")), desiredCapabilities);

				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}
				break;

			default:

			}
		} catch (Exception ex) {
			log.error(ex.getMessage());

		}
		return driver.getCapabilities().getCapability("deviceName").toString();
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	private static void InstallFromWebSiteiOS(AppiumDriver driver) {

		try {

			driver.executeScript("mobile:browser:open");

			driver.get(mobileProperties.getProperty("HockeyAppURL"));

			try {

				try {
					if (driver.findElement(By.xpath("//*[@class='nav']//*[text()='Sign In']")).isDisplayed()) {
						driver.findElement(By.xpath("//*[@class='nav']//*[text()='Sign In']")).click();
					} else {
						System.out.println("Sign in not displayed");
					}
				} catch (Exception e) {
					System.out.println(e);
				}

				WebDriverWait userid = new WebDriverWait(driver, 20);
				WebElement installuserid = userid
						.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='user_email']"))));

				if (driver.findElement(By.xpath("//*[@id='user_email']")).isDisplayed()) {
					driver.findElement(By.xpath("//*[@id='user_email']"))
							.sendKeys(mobileProperties.getProperty("HockeyUserName"));
					driver.findElement(By.xpath("//*[@id='user_password']"))
							.sendKeys(mobileProperties.getProperty("HockeyPassword"));
					driver.findElement(By.xpath("//*[@id='sign_in']/div[2]/button")).click();
					// Thread.sleep(5000);
					WebDriverWait dashwait = new WebDriverWait(driver, 15);
					WebElement dashelement = dashwait.until(ExpectedConditions.visibilityOfElementLocated(
							By.partialLinkText(mobileProperties.getProperty("HockeyappMOVEiOSAPPVersion"))));
				} else {
					log.info("Already Logged in to Hockey, Proceeding with installation");

				}

			} catch (Exception ex) {
				log.info("Already Logged in to Hockey, Proceeding with installation");
			}

			driver.findElement(By.partialLinkText(mobileProperties.getProperty("HockeyappMOVEiOSAPPVersion"))).click();

			/*
			 * Map<String, Object> params2 = new HashMap<>();
			 * params2.put("content", "ManulifeMOVE SIT (iOS)"); Object result2
			 * = driver .executeScript("mobile:text:select", params2);
			 */

			// Thread.sleep(3000);

			WebDriverWait instalFwait = new WebDriverWait(driver, 30);
			WebElement installEleF = instalFwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(
					"//*[@class='install pull-right']//*[@class='btn btn-ha-primary btn-sm start_ios_download']"))));

			Map<String, Object> params5 = new HashMap<>();
			params5.put("content", "Install");

			Object result5 = driver.executeScript("mobile:text:select", params5);

			driver.context("NATIVE_APP");
			WebElement elementInstall = driver.findElementByXPath("//*[@label=\"Install\"]");
			WebDriverWait installwait = new WebDriverWait(driver, 30);
			WebElement installEle = installwait.until(ExpectedConditions.visibilityOf(elementInstall));
			elementInstall.click();

		} catch (Exception ex) {
			log.error("Failed to install the App " + ex.getMessage());
		}

	}

	@SuppressWarnings({ "unused", "rawtypes" })
	private static void InstallFromWebSiteAnroid(AppiumDriver driver) {

		driver.get(mobileProperties.getProperty("HokeyAppURL"));

		try {

			driver.context("NATIVE_APP");
			if (driver.findElement(By.xpath("//*[@resource-id='de.codenauts.hockeyapp:id/loginBtn']")).isDisplayed()) {
				driver.findElement(By.xpath("//*[@resource-id='de.codenauts.hockeyapp:id/loginBtn']")).click();
				driver.context("WEBVIEW");
			} else {
				System.out.println("Page loaded already");
			}
			try {

				if (driver.findElement(By.xpath("//*[@id='user_email']")).isDisplayed()) {
					driver.findElement(By.xpath("//*[@id='user_email']"))
							.sendKeys(mobileProperties.getProperty("HockeyUserName"));
					driver.findElement(By.xpath("//*[@id='user_password']"))
							.sendKeys(mobileProperties.getProperty("HockeyPassword"));
					driver.findElement(By.xpath("//*[@id='sign_in']/div[2]/button")).click();

				} else {
					System.out.println("Already logged in");
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
			driver.context("WEBVIEW");
			WebDriverWait Andrints = new WebDriverWait(driver, 10);
			WebElement Andrintsele = Andrints.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//*[@class='btn btn-block btn-ha-primary']"))));

			// Click on the primary
			driver.findElement(By.xpath("//*[@class='btn btn-block btn-ha-primary']")).click();

			Map<String, Object> params12 = new HashMap<>();
			params12.put("content", mobileProperties.getProperty("HockeyappMOVEAndroidAPPVersion"));
			params12.put("timeout", "60");
			Object result12 = driver.executeScript("mobile:checkpoint:text", params12);
			// Click on Manulife iOS SIT Version
			Map<String, Object> params2 = new HashMap<>();
			params2.put("content", mobileProperties.getProperty("HockeyappMOVEAndroidAPPVersion"));
			Object result2 = driver.executeScript("mobile:text:select", params2);

			driver.context("NATIVE_APP");
			WebDriverWait dashwait = new WebDriverWait(driver, 20);
			WebElement dashelement = dashwait.until(ExpectedConditions.visibilityOf(
					driver.findElement(By.xpath("//*[@resource-id='de.codenauts.hockeyapp:id/button_download']"))));

			driver.context("NATIVE_APP");
			driver.findElement(By.xpath("//*[@resource-id='de.codenauts.hockeyapp:id/button_download']")).click();

			try {
				if (driver.findElement(By.xpath("//*[@resource-id='com.android.packageinstaller:id/ok_button']"))
						.isDisplayed()) {
					driver.findElement(By.xpath("//*[@resource-id='com.android.packageinstaller:id/ok_button']"))
							.click();
					System.out.println("App installation started");
					log.info("App installation started");
					driver.context("NATIVE_APP");
					WebDriverWait confirminst = new WebDriverWait(driver, 15);
					WebElement confirminstelement = confirminst
							.until(ExpectedConditions.visibilityOf(driver.findElement(
									By.xpath("//*[@resource-id='com.android.packageinstaller:id/confirm_button']"))));
					driver.findElement(By.xpath(("//*[@resource-id='com.android.packageinstaller:id/confirm_button']")))
							.click();
					System.out.println("App installation Completed");
					log.info("App installation Completed");

				} else {
					driver.context("NATIVE_APP");
					WebDriverWait allPremision = new WebDriverWait(driver, 15);
					WebElement allPremisionelement = allPremision.until(ExpectedConditions
							.visibilityOf(driver.findElement(By.xpath("//*[@content-desc='All permissions']"))));

					driver.context("NATIVE_APP");
					driver.findElement(By.xpath("//*[@content-desc='All permissions']")).click();

					driver.context("NATIVE_APP");
					WebDriverWait autoSwitch = new WebDriverWait(driver, 15);
					WebElement autoSwitchelement = autoSwitch.until(ExpectedConditions.visibilityOf(driver.findElement(
							By.xpath("//*[@resource-id='com.android.packageinstaller:id/autorunSwitch']"))));

					driver.context("NATIVE_APP");
					driver.findElement(By.xpath("//*[@resource-id='com.android.packageinstaller:id/autorunSwitch']"))
							.click();

					driver.context("NATIVE_APP");
					WebDriverWait confirmbt = new WebDriverWait(driver, 15);
					WebElement confirmbtelement = confirmbt.until(ExpectedConditions.visibilityOf(driver.findElement(
							By.xpath("//*[@resource-id='com.android.packageinstaller:id/autorunSwitch']"))));

					driver.context("NATIVE_APP");
					driver.findElement(By.xpath("//*[@resource-id='com.android.packageinstaller:id/confirm_button']"))
							.click();

				}
			} catch (Exception ex) {
				System.out.println(ex);
				log.error("Failed to install the App " + ex.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Failed to install the App " + e.getMessage());
		}
	}

	public static WebDriver getPerfectoRemoteWebDriverForDesktop(SeleniumTestParameters testParameters) {

		RemoteWebDriver driver = null;
		String browserName = getBrowerName(testParameters);
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("user", mobileProperties.getProperty("PerfectoUser"));
		desiredCapabilities.setCapability("password", mobileProperties.getProperty("PerfectoPassword"));
		desiredCapabilities.setCapability("platformName", "Windows");
		desiredCapabilities.setCapability("platformVersion", testParameters.getOsVersion());
		desiredCapabilities.setCapability("browserName", browserName);
		desiredCapabilities.setCapability("browserVersion", testParameters.getBrowserVersion());
		desiredCapabilities.setCapability("resolution", mobileProperties.getProperty("Resolution"));
		desiredCapabilities.setCapability("location", mobileProperties.getProperty("Location"));
		desiredCapabilities.setCapability("takesScreenshot", true);
		try {
			driver = new RemoteWebDriver(new URL(mobileProperties.getProperty("PerfectoHost")), desiredCapabilities);

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

	public static WebDriver getPerfectoRemoteDriver(SeleniumTestParameters testParameters) {

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
