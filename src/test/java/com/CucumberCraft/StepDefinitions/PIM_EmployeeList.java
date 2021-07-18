package com.CucumberCraft.StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.CucumberCraft.SupportLibraries.DriverUtils;
import com.CucumberCraft.SupportLibraries.Helper;
import com.CucumberCraft.SupportLibraries.TestController;
import com.CucumberCraft.SupportLibraries.WebDriverUtils;

import cucumber.api.java.en.And;

public class PIM_EmployeeList extends MasterStepDefs {

	private WebDriver driver = TestController.getWebDriver();
	private DriverUtils driverUtils = new WebDriverUtils(driver);
	private Helper helper = TestController.getHelper();

	@And("^user login with valid username and password$")
	public void userLoginWithValidUsernameAndPassword() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String userName = helper.getConfig("web.username");
		String password = helper.getConfig("web.password");

		driverUtils.typeTextIntoElement(userName, "LOGIN_PAGE_USERNAME_INPUT");
		driverUtils.typeTextIntoElement(password, "LOGIN_PAGE_PASSWORD_INPUT");
		driverUtils.getElement("LOGIN_PAGE_LOGIN_FORM").submit();
	}
}