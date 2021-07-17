package com.CucumberCraft.StepDefinitions;

import org.openqa.selenium.WebDriver;

import com.CucumberCraft.SupportLibraries.DriverUtils;
import com.CucumberCraft.SupportLibraries.TestController;
import com.CucumberCraft.SupportLibraries.WebDriverUtils;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class CommonSteps extends MasterStepDefs {
	

	private WebDriver driver = TestController.getWebDriver();
	private DriverUtils driverUtils = new WebDriverUtils(driver);	
	
	
	@And("^user launches application under test$")
	public void userLaunchesApplicationUnderTest() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.launchAUT();
	}
	
	@And("^\"([^\"]*)\" shows up$")
	public void pageShowUp(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.assertPageShowUp(arg1);
	}
	
//	@And("^I load the input data of the test case \"([^\"]*)\" in json file \"([^\"]*)\"$")
//	public void i_load_the_input_data_of_the_test_case_from_file(String arg1, String arg2) throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		TestController.getHelper().loadTestDataFromJson(arg1, arg2);
//	}
//
//	@When("^user clicks on \"([^\"]*)\"$")
//	public void user_clicks_on(String arg1) throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		element = driverUtil.getElement(arg1);
//		element.click();
//	}
//
//	@Then("^\"([^\"]*)\" is present$")
//	public void is_present(String arg1) throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		driverUtil.verifyElementPresent(arg1);
//	}
//
//	@Then("^\"([^\"]*)\" is not present$")
//	public void is_not_present(String arg1) throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		driverUtil.verifyElementNotPresent(arg1);
//	}
//
//	@When("^user waits until \"([^\"]*)\" is present in (\\d+) seconds$")
//	public void user_waits_until_is_present_in_seconds(String arg1, int arg2) throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//
//	}
//
//	@When("^user waits for (\\d+) seconds$")
//	public void user_waits_for_seconds(int arg1) throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		driverUtil.wait(arg1);
//	}
//
//	@When("^user types \"([^\"]*)\" into \"([^\"]*)\"$")
//	public void user_types_into(String arg1, String arg2) throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		element = driverUtil.getElement(arg2);
//		element.clear();
//		element.sendKeys(arg1);
//	}
//
//	@When("^user navigates back$")
//	public void user_navigates_back() throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		driverUtil.navigateBack();
//	}
//
//	@When("^user inputs pin \"([^\"]*)\"$")
//	public void user_inputs_pin(String arg1) throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		driverUtil.tapOnNumberPad(arg1);
//	}
//
//	@When("^user dismisses Alert$")
//	public void user_dismisses_Alert() throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		driverUtil.dismissAlert();
//	}
//
//	@When("^user swipes \"([^\"]*)\"$")
//	public void user_swipes(String arg1) throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		if (arg1.toUpperCase().equals("UP"))
//			driverUtil.swipeUp(0.8, 0.1, 0.5, 2000);
//		else if (arg1.toUpperCase().equals("DOWN"))
//			driverUtil.swipeDown(1);
//	}
//
//	@When("^user scrolls down until \"([^\"]*)\" element is present$")
//	public void user_scrolls_down_until_element_is_present(String arg1) throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		driverUtil.scrollToFindElement(arg1, 5);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	}
//
//	@When("^user clicks on element by visible text \"([^\"]*)\"$")
//	public void user_clicks_on_element_by_visible_text(String arg1) throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		driverUtil.clickElementByVisibleText(arg1);
//	}
}
