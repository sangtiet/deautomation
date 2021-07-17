package com.CucumberCraft.StepDefinitions;

import org.openqa.selenium.WebDriver;

import com.CucumberCraft.SupportLibraries.DriverUtils;
import com.CucumberCraft.SupportLibraries.Helper;
import com.CucumberCraft.SupportLibraries.TestController;
import com.CucumberCraft.SupportLibraries.WebDriverUtils;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class CommonSteps extends MasterStepDefs {

	private WebDriver driver = TestController.getWebDriver();
	private DriverUtils driverUtils = new WebDriverUtils(driver);
	private Helper helper = TestController.getHelper();

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

	@And("^user clicks on \"([^\"]*)\"$")
	public void userClicksOnElement(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.clickOnElement(arg1);
	}

	@And("^user submits form \"([^\"]*)\"$")
	public void userSubmitsForm(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.submitForm(arg1);
	}

	@And("^user types \"([^\"]*)\" into \"([^\"]*)\"$")
	public void userTypesTextIntoElement(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.typeTextIntoElement(arg1, arg2);
	}

	@And("^\"([^\"]*)\" is present$")
	public void elementIsPresent(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.assertElementIsPresent(arg1);
	}

	@And("^\"([^\"]*)\" is not present$")
	public void elementIsNotPresent(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.assertElementIsNotPresent(arg1);
	}

	@And("^\"([^\"]*)\" shows up in (\\d+) seconds$")
	public void pageShowsUpInSeconds(String arg1, int arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.assertPageShowUpInGivenTimeSeconds(arg1, arg2);
	}

	@And("^\"([^\"]*)\" is present in (\\d+) seconds$")
	public void elementIsPresentInSeconds(String arg1, int arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.assertElementIsPresentInGivenTimeSeconds(arg1, arg2);
	}

	@And("^\"([^\"]*)\" is not present in (\\d+) seconds$")
	public void elementIsNotPresentInSeconds(String arg1, int arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.assertElementIsNotPresentInGivenTimeSeconds(arg1, arg2);
	}

	@And("^\"([^\"]*)\" shows text \"([^\"]*)\"$")
	public void elementShowsText(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.assertElementShowText(arg1, arg2);
	}

	@And("^\"([^\"]*)\" contains text \"([^\"]*)\"$")
	public void elementContainsText(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.assertElementContainText(arg1, arg2);
	}

	@And("^user wait for (\\d+) seconds$")
	public void userWaitForNSeconds(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		helper.delaySynchronization(arg1);
	}

//	@And("^I load the input data of the test case \"([^\"]*)\" in json file \"([^\"]*)\"$")
//	public void i_load_the_input_data_of_the_test_case_from_file(String arg1, String arg2) throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		TestController.getHelper().loadTestDataFromJson(arg1, arg2);
//	}
//

//

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
//	td[count(//th[.='First Name']/preceding-sibling::th) +1][.='bat']
}
