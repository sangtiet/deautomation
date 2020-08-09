package com.CucumberCraft.StepDefinitions;

import java.util.Properties;

import com.CucumberCraft.SupportLibraries.SeleniumTestParameters;
import com.CucumberCraft.SupportLibraries.TestController;

import cucumber.api.Scenario;
import cucumber.api.java.en.And;

public abstract class MasterStepDefs {

	protected Scenario currentScenario;
	protected SeleniumTestParameters currentTestParameters;
	protected Properties propertiesFileAccess;
	
	@And("^I load the input data of the test case \"([^\"]*)\" in json file \"([^\"]*)\"$")
	public void i_load_the_input_data_of_the_test_case_from_file(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		TestController.getHelper().loadTestDataFromJson(arg1, arg2);
	}
}