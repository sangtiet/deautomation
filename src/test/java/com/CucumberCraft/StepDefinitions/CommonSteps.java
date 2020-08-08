package com.CucumberCraft.StepDefinitions;

import org.openqa.selenium.WebElement;
import org.apache.log4j.Logger;

import com.CucumberCraft.SupportLibraries.AppiumDriverUtil;
import com.CucumberCraft.SupportLibraries.TestController;
import cucumber.api.java.en.*;
import io.appium.java_client.AppiumDriver;

public class CommonSteps extends MasterStepDefs {

	static Logger log = Logger.getLogger(CommonSteps.class);

	private AppiumDriver driver = TestController.getAppiumDriver();
	private AppiumDriverUtil driverUtil = new AppiumDriverUtil(driver);
	private WebElement element;

	@And("^I load the input data of the test case \"([^\"]*)\" in json file \"([^\"]*)\"$")
	public void i_load_the_input_data_of_the_test_case_from_file(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		TestController.getHelper().loadTestDataFromJson(arg1, arg2);
	}

	@Given("^user launchs the application$")
	public void user_launchs_the_application() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		log.info("App was launched successfully");
	}

	@Then("^\"([^\"]*)\" shows up$")
	public void shows_up(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtil.verifyPageShowsUp(arg1);
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

	}

	@Then("^\"([^\"]*)\" is present$")
	public void is_present(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@Then("^\"([^\"]*)\" is not present$")
	public void is_not_present(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@When("^user waits until \"([^\"]*)\" is present in (\\d+) seconds$")
	public void user_waits_until_is_present_in_seconds(String arg1, int arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@When("^user waits for (\\d+) seconds$")
	public void user_waits_for_seconds(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		TestController.getHelper().wait(arg1);
	}

	@When("^user types \"([^\"]*)\" into \"([^\"]*)\"$")
	public void user_types_into(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@When("^user navigates back$")
	public void user_navigates_back() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@When("^user inputs pin \"([^\"]*)\"$")
	public void user_inputs_pin(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@When("^user scrolls \"([^\"]*)\" until \"([^\"]*)\" is present$")
	public void user_scrolls_until_is_present(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}
}