package com.CucumberCraft.stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.CucumberCraft.supportLibraries.TestController;

import cucumber.api.java.en.*;
import io.appium.java_client.AppiumDriver;

public class AndroidTesting {

	@SuppressWarnings("rawtypes")
	private static AppiumDriver driver = TestController.getAppiumDriver();
	static Logger log = Logger.getLogger(AndroidTesting.class);
	private String btnAddNew = "com.realbyteapps.moneymanagerfree:id/addFab";
	private String lblAccounts = "com.realbyteapps.moneymanagerfree:id/subTitleName";
	private WebElement elem = null;

	@Given("^I launch app on real device$")
	public void Ilaunchapponrealdevice() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		elem = driver.findElement(By.id(btnAddNew));
		System.out.println("Launch app successfully!");
		Thread.sleep(5000);
	}

	@And("^I click Add New button$")
	public void IclickAddNewbutton() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		elem.click();
		Thread.sleep(3000);
	}

	@And("^I verify the Accounts label$")
	public void IverifytheAccountslabel() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		elem = driver.findElement(By.id(lblAccounts));		
		TestController.getHelper().compare2Text(elem.getText().trim(), "Account");
	}
}
