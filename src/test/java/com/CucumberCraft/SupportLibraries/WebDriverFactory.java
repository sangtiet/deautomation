package com.CucumberCraft.supportLibraries;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.log4testng.Logger;

//import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Factory class for creating the {@link WebDriver} object as required
 * 
 */
public class WebDriverFactory {
	private static Properties properties;
	static Logger log = Logger.getLogger(WebDriverFactory.class);

	private WebDriverFactory() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the appropriate {@link WebDriver} object based on the
	 * parameters passed
	 * 
	 * @param browser The {@link Browser} to be used for the test execution
	 * @return The corresponding {@link WebDriver} object
	 */
	public static WebDriver getWebDriver(Browser browser) {
		WebDriver driver = null;
		properties = Settings.getInstance();
		boolean proxyRequired = Boolean.parseBoolean(properties.getProperty("ProxyRequired"));
		try {
			switch (browser) {
			case CHROME:
				ChromeOptions options = new ChromeOptions();

				if (System.getProperty("os.name").toLowerCase().contains("windows")) {

					// pnguyen38 - 03/25/2020: setup the chromedriver using WebDriverManager
					// WebDriverManager.chromedriver().setup();
					System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");

					options.addArguments("start-maximized"); // open Browser in maximized mode

				} else if (System.getProperty("os.name").toLowerCase().contains("linux")) {
					// pnguyen38 - 03/25/2020: setup the chromedriver using WebDriverManager
					// WebDriverManager.chromedriver().version("2.4").setup();

					options.addArguments("start-maximized"); // open Browser in maximized mode

					options.addArguments("disable-infobars"); // disabling infobars
					options.addArguments("--disable-extensions"); // disabling extensions
					options.addArguments("--disable-gpu"); // applicable to windows os only
					options.addArguments("--disable-dev-shm-usage"); // overcome limited resource
					options.addArguments("--no-sandbox"); // Bypass OS security model
					options.addArguments("--headless");
					options.addArguments("--test-type");

				}

				/**
				 * *****Run on Docker***** DesiredCapabilities cap = new DesiredCapabilities();
				 * cap.setBrowserName(BrowserType.CHROME); driver = new RemoteWebDriver(new
				 * URL("http://localhost:4546/wd/hub"),cap);
				 */

				driver = new ChromeDriver(options);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				break;

			case FIREFOX:
				FirefoxOptions ffOptions = new FirefoxOptions();

				// pnguyen38 - 03/26/2020: setup the firefoxdriver using WebDriverManager
				// WebDriverManager.firefoxdriver().setup();
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\geckodriver.exe");

				if (System.getProperty("os.name").toLowerCase().contains("windows")) {

					ffOptions.addArguments("start-maximized"); // open Browser in maximized mode

				} else if (System.getProperty("os.name").toLowerCase().contains("linux")) {

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
					// WebDriverManager.iedriver().arch32().setup();

					DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
					caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					caps.setCapability("ignoreZoomSetting", true);

					driver = new InternetExplorerDriver(caps);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				}

				break;

			case EDGE: // !WARN: The Click() event should be performed by JS
				if (System.getProperty("os.name").toLowerCase().contains("windows")) {
					// WebDriverManager.edgedriver().setup();

					driver = new EdgeDriver();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				}

				break;

			case OPERA:
				// Does not take the system proxy settings automatically!
				// NTLM authentication for proxy NOT supported
				if (System.getProperty("os.name").toLowerCase().contains("windows")) {
					// WebDriverManager.operadriver().setup();

					if (proxyRequired) {
						DesiredCapabilities desiredCapabilities = getProxyCapabilities();
						driver = new OperaDriver(desiredCapabilities);
					} else {
						driver = new OperaDriver();
					}

					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				}

				break;

			case SAFARI:
				// Takes the system proxy settings automatically

				driver = new SafariDriver();
				break;

			default:
				log.warn("No Such Brower Implementation available");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}

		return driver;
	}

	public static WebDriver getRemoteWebDriver(Browser browser) {
		WebDriver driver = null;
		properties = Settings.getInstance();
		DesiredCapabilities capabilities = null;
		try {
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
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	private static DesiredCapabilities getProxyCapabilities() {
		properties = Settings.getInstance();
		String proxyUrl = properties.getProperty("ProxyHost") + ":" + properties.getProperty("ProxyPort");

		Proxy proxy = new Proxy();
		proxy.setProxyType(ProxyType.MANUAL);
		proxy.setHttpProxy(proxyUrl);
		proxy.setFtpProxy(proxyUrl);
		proxy.setSslProxy(proxyUrl);

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);

		return desiredCapabilities;
	}

	/**
	 * Function to return the {@link RemoteWebDriver} object based on the parameters
	 * passed
	 * 
	 * @param browser        The {@link Browser} to be used for the test execution
	 * @param browserVersion The browser version to be used for the test execution
	 * @param platform       The {@link Platform} to be used for the test execution
	 * @param remoteUrl      The URL of the remote machine to be used for the test
	 *                       execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
	public static WebDriver getRemoteWebDriver(Browser browser, String browserVersion, Platform platform,
			String remoteUrl) {

		properties = Settings.getInstance();
		boolean proxyRequired = Boolean.parseBoolean(properties.getProperty("ProxyRequired"));

		DesiredCapabilities desiredCapabilities = null;
		if (browser.equals(Browser.OPERA) && proxyRequired) {
			desiredCapabilities = getProxyCapabilities();
		} else {
			desiredCapabilities = new DesiredCapabilities();
		}

		desiredCapabilities.setBrowserName(browser.getValue());

		if (browserVersion != null) {
			desiredCapabilities.setVersion(browserVersion);
		}
		if (platform != null) {
			desiredCapabilities.setPlatform(platform);
		}

		desiredCapabilities.setJavascriptEnabled(true); // Pre-requisite for
														// remote execution

		URL url = getUrl(remoteUrl);

		return new RemoteWebDriver(url, desiredCapabilities);
	}

	private static URL getUrl(String remoteUrl) {
		URL url = null;
		try {
			url = new URL(remoteUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();

		}
		return url;
	}

	/**
	 * Function to return the {@link RemoteWebDriver} object based on the parameters
	 * passed
	 * 
	 * @param browser   The {@link Browser} to be used for the test execution
	 * @param remoteUrl The URL of the remote machine to be used for the test
	 *                  execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
	public static WebDriver getRemoteWebDriver(Browser browser, String remoteUrl) {
		return getRemoteWebDriver(browser, null, null, remoteUrl);
	}
}