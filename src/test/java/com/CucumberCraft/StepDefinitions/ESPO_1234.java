package com.CucumberCraft.StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.CucumberCraft.PageObjects.*;
import com.CucumberCraft.SupportLibraries.*;

import cucumber.api.java.en.*;

public class ESPO_1234 extends MasterStepDefs {

	/**
	 * default
	 */
	private WebElement elem = null;
	private String locator = null;
	static final int TIMEOUT = 60;

	/**
	 * WebDriver initialization
	 */
	private WebDriver driver = TestController.getWebDriver();
	private WebDriverUtil driverUtils = new WebDriverUtil(driver);
	private Helper helper = TestController.getHelper();

	/**
	 * Page Objects initialization
	 */
	private LoginPage login_page = new LoginPage();
	private HomePage home_page = new HomePage();
	private AccountPage account_page = new AccountPage();
	private CreateAccountPage createaccount_page = new CreateAccountPage();

	/**
	 * Step definitions
	 */
	@Given("^I login EspoCRM system$")
	public void IloginEspoCRMsystem() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String sURL = helper.readGlobalParam("env.url");
		driverUtils.goToURL(sURL);
		try {
			driverUtils.waitUntilElementLocated(login_page.btnLogin, TIMEOUT);
			elem = driverUtils.getWebElement(login_page.btnLogin);
			elem.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Skip login");
		}
	}

	@And("^I will be shown the Home page$")
	public void IwillbeshowntheHomepage() throws Throwable {
		driverUtils.waitForPageLoad(TIMEOUT);
		driverUtils.waitUntilElementLocated(home_page.lstStream, TIMEOUT);
	}

	@When("^I click on \"([^\"]*)\" menu$")
	public void Iclickonarg1menu(String arg1) throws Throwable {
		locator = "xpath=//li[@data-name='" + arg1 + "']//child::a";
		elem = driverUtils.getWebElement(locator);
		elem.click();
	}

	@And("^I click on \"([^\"]*)\" button$")
	public void Iclickonarg1button(String arg1) throws Throwable {
		switch (arg1) {
		case "Create Account":
			locator = account_page.btnCreateAccount;
			break;
		}

		elem = driverUtils.getWebElement(locator);
		elem.click();
	}

	@And("^I enter valid value as \"([^\"]*)\" and \"([^\"]*)\" on Create Account page$")
	public void IentervalidvalueasNameandWebsiteonCreateAccountpage(String arg1, String arg2) throws Throwable {
		elem = driverUtils.getWebElement(createaccount_page.txtName);
		elem.sendKeys(arg1);

		elem = driverUtils.getWebElement(createaccount_page.txtWebsite);
		elem.sendKeys(arg2);

		helper.getScenarioContext().setContext("Name", arg1);
	}

	@And("^I click Save button$")
	public void IclickSavebutton() throws Throwable {
		elem = driverUtils.getWebElement(createaccount_page.btnSave);
		elem.click();
	}

	@Then("^I will be shown the new account was created successfully$")
	public void Iwillbeshownthenewaccountwascreatedsuccessfully() throws Throwable {
		locator = "xpath=//span[@class='label-text' and text()='Name']//following::div[1]";
		driverUtils.waitUntilElementLocated(locator, TIMEOUT);
		helper.wait(5);
		elem = driverUtils.getWebElement(locator);

		String actual = elem.getText().trim();
		String expected = helper.getScenarioContext().getContext("Name").toString();
		helper.compare2Text(actual, expected);
		helper.wait(5);
	}

	@And("^I enter valid values to all input field on Create Account page$")
	public void IentervalidvaluestoallinputfieldonCreateAccountpage() throws Throwable {
		String name = helper.getTestData().get("name").toString();
		String website = helper.getTestData().get("website").toString();
		String email = helper.getTestData().get("email").toString();
		String phone = helper.getTestData().get("phone").toString();

		IentervalidvalueasNameandWebsiteonCreateAccountpage(name, website);

		elem = driverUtils.getWebElement(createaccount_page.txtEmail);
		elem.sendKeys(email);

		elem = driverUtils.getWebElement(createaccount_page.txtPhone);
		elem.sendKeys(phone);
	}
}
