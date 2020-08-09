package com.CucumberCraft.SupportLibraries;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class MySMS {
	private String mysms_account;
	private String mysms_password;
	private String mysms_sender;
	private String mysms_url = "https://www.mysms.com/";
	private WebDriver driver;
	private WebDriverUtil driverUtils;
	private WebElement elem;
	private List<WebElement> lstElem;
	private String locator;
	private int timeout = 60;
	private Helper helper = new Helper();

	public MySMS(String mysmsAccount, String mysmsPassword, String mysmsSender) {
		mysms_account = mysmsAccount;
		mysms_password = mysmsPassword;
		mysms_sender = mysmsSender;

		ChromeOptions options = new ChromeOptions();
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");
		options.addArguments("--start-maximized");
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		driverUtils = new WebDriverUtil(driver);
	}

	public String getOTP() throws Exception {
		String OTP = null;
		driverUtils.goToURL(mysms_url);
		driverUtils.waitForPageLoad(timeout);

		locator = "xpath=//a[text()='Login']";
		elem = driverUtils.getWebElement(locator);
		elem.click();
		driverUtils.switchToWindowHandlesOpen();
		helper.wait(1);

		locator = "xpath=//button[@type='button']//child::div[text()='Login']";
		elem = driverUtils.getWebElement(locator);
		elem.click();
		locator = "xpath=//a[text()='Sign in with your mobile number']";
		elem = driverUtils.getWebElement(locator);
		elem.click();

		locator = "xpath=//select[@class='list']";
		elem = driverUtils.getWebElement(locator);
		Select drpCountry = new Select(elem);
		drpCountry.selectByVisibleText("Vietnam (+84)");
		helper.wait(1);

		locator = "xpath=//input[@placeholder='Your mobile number']";
		elem = driverUtils.getWebElement(locator);
		elem.sendKeys(mysms_account);
		locator = "xpath=//input[@placeholder='Your password']";
		elem = driverUtils.getWebElement(locator);
		elem.sendKeys(mysms_password);

		locator = "xpath=//button[@class='styledButton login']";
		elem = driverUtils.getWebElement(locator);
		elem.click();

		locator = "xpath=//img[@class='avatar']//following::span[text()='" + mysms_sender + "']";
		driverUtils.waitUntilElementLocated(locator, timeout);
		elem = driverUtils.getWebElement(locator);
		elem.click();
		helper.wait(1);

		locator = "xpath=//span[@class='message']";
		lstElem = driverUtils.getListOfWebElement(locator);
		elem = lstElem.get(0);
		System.out.println(elem.getText());
		OTP = elem.getText().trim().substring(27, 33);

		driver.close();
		driver.quit();

		return OTP;
	}
}
