package com.CucumberCraft.StepDefinitions;

import org.openqa.selenium.By;
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

	@And("^\"([^\"]*)\" has attribute \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void elementAttributeWithValue(String arg1, String arg2, String arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.assertElementAttributeHasValue(arg1, arg2, arg3);
	}

	@And("^\"([^\"]*)\" has attribute \"([^\"]*)\" contain value \"([^\"]*)\"$")
	public void elementAttributeContainValue(String arg1, String arg2, String arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.assertElementAttributeContainValue(arg1, arg2, arg3);
	}

	@And("^user wait for (\\d+) seconds$")
	public void userWaitForNSeconds(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		helper.delaySynchronization(arg1);
	}

	@And("^user refreshes current page$")
	public void userRefreshesCurrentPage() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.refresh();
	}

	@And("^user navigates to \"([^\"]*)\"$")
	public void userNavigatesToUrl(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.goToUrl(arg1);
	}

	@And("^user navigates back$")
	public void userNavigatesBack() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.navigateBack();
	}

	@And("^user navigate forward$")
	public void userNavigateForward() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.navigateForward();
	}

	@And("^user switch to default$")
	public void userSwitchToDefault() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.switchToDefault();
		;
	}

	@And("^user switch to \"([^\"]*)\"$")
	public void userSwitchToIframe(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.switchToIframe(arg1);
		;
	}

	@Then("^user scrolls down (\\d+) pixel$")
	public void userScrollsDownPixel(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.scrollDown(arg1);
	}

	@Then("^user scrolls down (\\d+) pixel (\\d+) times$")
	public void userScrollsDownPixelTimes(int arg1, int arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.scrollDownNTimes(arg1, arg2);
	}

	@Then("^user scrolls to \"([^\"]*)\"$")
	public void userScrollsToElement(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.scrollToElement(arg1);
	}

	@Then("^user presses ENTER key$")
	public void userPressesENTERKey() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.pressEnterKey();
	}

	@Then("^user clicks on \"([^\"]*)\" by JS$")
	public void userClicksOnElementByJS(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driverUtils.clickOnElementByJS(arg1);
	}

	@Then("^user clicks on menu following order \"([^\"]*)\"$")
	public void user_clicks_on_menu_following_order_will_be_removed(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String[] arr = arg1.split(">");
		String locator = null;
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				locator = "//li[@class='main-menu-first-level-list-item']//child::b[text()='" + arr[i].trim() + "']";
				driver.findElement(By.xpath(locator)).click();				
				locator = locator.replace("main-menu-first-level-list-item", "current main-menu-first-level-list-item");
			} else {
				locator += "//following::ul[1]//following::a[text()='" + arr[i].trim() + "']";				
				driver.findElement(By.xpath(locator)).click();				
			}
		}

	}
	
	@And("^user login with valid username and password$")
	public void userLoginWithValidUsernameAndPassword() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String userName = helper.getConfig("web.username");
		String password = helper.getConfig("web.password");

		driverUtils.typeTextIntoElement(userName, "LOGIN_PAGE_USERNAME_INPUT");
		driverUtils.typeTextIntoElement(password, "LOGIN_PAGE_PASSWORD_INPUT");
		driverUtils.getElement("LOGIN_PAGE_LOGIN_FORM").submit();
	}

//	@And("^I load the input data of the test case \"([^\"]*)\" in json file \"([^\"]*)\"$")
//	public void i_load_the_input_data_of_the_test_case_from_file(String arg1, String arg2) throws Throwable {
//		// Write code here that turns the phrase above into concrete actions
//		TestController.getHelper().loadTestDataFromJson(arg1, arg2);

//	td[count(//th[.='First Name']/preceding-sibling::th) +1][.='bat']
}
