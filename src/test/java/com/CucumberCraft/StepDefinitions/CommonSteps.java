package com.CucumberCraft.StepDefinitions;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.CucumberCraft.SupportLibraries.AppiumDriverUtil;
import com.CucumberCraft.SupportLibraries.TestController;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;

public class CommonSteps extends MasterStepDefs {

	static Logger log = Logger.getLogger(CommonSteps.class);

	@SuppressWarnings("rawtypes")
	private AppiumDriver driver = TestController.getAppiumDriver();
	private AppiumDriverUtil driverUtil = new AppiumDriverUtil(driver);
	private WebElement element;

//  --------------------!!!DO NOT REMOVE!!!--------------------
//	@And("^I load the input data of the test case \"([^\"]*)\" in json file \"([^\"]*)\"$")
//	public void i_load_the_input_data_of_the_test_case_from_file(String arg1, String arg2) throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		helper.loadTestDataFromJson(arg1, arg2);
//	}

	@Given("^user launchs the application$")
	public void user_launchs_the_application() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtil.getWebElement("LZP_LOGIN_WITH_PHONE_NUMBER_BUTTON");
		log.info("App was launched successfully");
	}

	@Then("^\"([^\"]*)\" shows up$")
	public void shows_up(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtil.verifyPageShowsUp(arg1);
		log.info(arg1 + " page shown up");
	}

	@When("^user clicks on \"([^\"]*)\"$")
	public void user_clicks_on(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		element = driverUtil.getWebElement(arg1);
		element.click();
	}

	@When("^user searches for OTP in SMS from \"([^\"]*)\" to \"([^\"]*)\" and types into \"([^\"]*)\"$")
	public void user_searches_for_OTP_in_SMS_from_to_and_types_into(String arg1, String arg2, String arg3)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String otp = driverUtil.retrieveOTPfromSMS(arg1, arg2);
		element = driverUtil.getWebElement(arg3);
		element.clear();
		element.sendKeys(otp);
	}

	@Then("^\"([^\"]*)\" is present$")
	public void is_present(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtil.verifyElementPresent(arg1);
	}

	@Then("^\"([^\"]*)\" is not present$")
	public void is_not_present(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtil.verifyElementNotPresent(arg1);
	}

	@When("^user waits until \"([^\"]*)\" is present in (\\d+) seconds$")
	public void user_waits_until_is_present_in_seconds(String arg1, int arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@When("^user waits for (\\d+) seconds$")
	public void user_waits_for_seconds(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtil.wait(arg1);
	}

	@When("^user types \"([^\"]*)\" into \"([^\"]*)\"$")
	public void user_types_into(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		element = driverUtil.getWebElement(arg2);
		element.sendKeys(arg1);
	}

	@When("^user navigates back$")
	public void user_navigates_back() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtil.navigateBack();
	}

	@When("^user inputs pin \"([^\"]*)\"$")
	public void user_inputs_pin(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtil.tapOnNumberPad(arg1);
	}

	@When("^user dismisses Alert$")
	public void user_dismisses_Alert() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtil.dismissAlert();
	}

	@When("^user swipes \"([^\"]*)\"$")
	public void user_swipes(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		if (arg1.toUpperCase().equals("UP"))
			driverUtil.swipeUp(0.8, 0.1, 0.5, 2000);
		else if (arg1.toUpperCase().equals("DOWN"))
			driverUtil.swipDown(1);
	}

	@When("^user scrolls down until \"([^\"]*)\" element is present$")
	public void user_scrolls_down_until_element_is_present(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtil.scrollToFindElement(arg1, 5);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@When("^user clicks on element by visible text \"([^\"]*)\"$")
	public void user_clicks_on_element_by_visible_text(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtil.clickElementByVisibleText(arg1);
	}
}
