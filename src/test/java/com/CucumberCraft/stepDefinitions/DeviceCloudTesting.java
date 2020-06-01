package com.CucumberCraft.stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.CucumberCraft.supportLibraries.TestController;

import cucumber.api.java.en.*;
import io.appium.java_client.AppiumDriver;

public class DeviceCloudTesting {

	@SuppressWarnings("rawtypes")
	private static AppiumDriver driver = TestController.getAppiumDriver();
	static Logger log = Logger.getLogger(DeviceCloudTesting.class);
	private String btnNew = "//*[@text='NEW TO MONEY LOVER']";
	private String btnAlready = "//*[@text='ALREADY USING MONEY LOVER']";
	private WebElement elem = null;

	@Given("^I launch app on device cloud$")
	public void Ilaunchapponrealdevice() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		elem = driver.findElement(By.xpath(btnNew));
		System.out.println("Launch app successfully!");
		Thread.sleep(5000);
	}

	@And("^I will be show Welcome page$")
	public void IclickAddNewbutton() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		elem = driver.findElement(By.xpath(btnAlready));
		System.out.println(elem.getText());
		Thread.sleep(5000);
	}
}
