package com.CucumberCraft.stepDefinitions;

import com.CucumberCraft.supportLibraries.TestController;

import cucumber.api.java.en.And;

public class Common {

	@And("^I load the input data of the test case \"([^\"]*)\" in json file \"([^\"]*)\"$")
	public void i_load_the_input_data_of_the_test_case_from_file(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		TestController.getHelper().loadTestDataFromJson(arg1, arg2);
	}
}
