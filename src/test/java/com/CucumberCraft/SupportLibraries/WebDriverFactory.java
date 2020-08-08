package com.CucumberCraft.SupportLibraries;

import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.log4testng.Logger;

/**
 * Factory class for creating the {@link WebDriver} object as required
 * 
 */
public class WebDriverFactory {
	private static Properties properties;
	static Logger log = Logger.getLogger(WebDriverFactory.class);

	private WebDriverFactory() {
	}

	/**
	 * Function to return the appropriate {@link WebDriver} object based on the
	 * parameters passed
	 * 
	 * @param browser The {@link Browser} to be used for the test execution
	 * @return The corresponding {@link WebDriver} object
	 */
	public static WebDriver getWebDriver(Browser browser) throws Exception {
		WebDriver driver = null;
		properties = Settings.getInstance();

		switch (browser) {
		case CHROME:
			ChromeOptions options = new ChromeOptions();

			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");

				options.addArguments("start-maximized"); // open Browser in maximized mode

			} else if (System.getProperty("os.name").toLowerCase().contains("linux")) {
				options.addArguments("start-maximized"); // open Browser in maximized mode
				options.addArguments("disable-infobars"); // disabling infobars
				options.addArguments("--disable-extensions"); // disabling extensions
				options.addArguments("--disable-gpu"); // applicable to windows os only
				options.addArguments("--disable-dev-shm-usage"); // overcome limited resource
				options.addArguments("--no-sandbox"); // Bypass OS security model
				options.addArguments("--headless");
				options.addArguments("--test-type");
			}
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;

		case FIREFOX:
			FirefoxOptions ffOptions = new FirefoxOptions();

			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\geckodriver.exe");

			if (System.getProperty("os.name").toLowerCase().contains("windows"))
				ffOptions.addArguments("start-maximized"); // open Browser in maximized mode
			else if (System.getProperty("os.name").toLowerCase().contains("linux")) {
				ffOptions.addArguments("--disable-extensions"); // disabling extensions
				ffOptions.addArguments("--disable-gpu"); // applicable to windows os only
				ffOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource
				ffOptions.addArguments("--no-sandbox"); // Bypass OS security model
				ffOptions.addArguments("--headless");
				ffOptions.addArguments("--test-type");
			}
			driver = new FirefoxDriver(ffOptions);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;

		case INTERNET_EXPLORER: // !WARN: The Click() and SendKeys() event should be performed by JS
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				caps.setCapability("ignoreZoomSetting", true);

				driver = new InternetExplorerDriver(caps);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
			break;

		case EDGE: // !WARN: The Click() event should be performed by JS
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				driver = new EdgeDriver();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}

			break;

		default:
			log.warn("No such Brower implementation available");
		}

		return driver;
	}

	public static WebDriver getRemoteWebDriver(Browser browser) throws Exception {
		WebDriver driver = null;
		properties = Settings.getInstance();
		DesiredCapabilities capabilities = null;

		switch (browser) {
		case CHROME: //
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setPlatform(Platform.LINUX);
			driver = new RemoteWebDriver(new URL("http://" + properties.getProperty("RemoteURL") + "/wd/hub"),
					capabilities);
			break;

		case FIREFOX:
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.addArguments("--start-maximized");
			capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, ffOptions);
			capabilities.setPlatform(Platform.LINUX);
			driver = new RemoteWebDriver(new URL("http://" + properties.getProperty("RemoteURL") + "/wd/hub"),
					capabilities);

			break;
		default:
			log.warn("No Such Brower Implementation available");
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}