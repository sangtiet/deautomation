package com.CucumberCraft.stepDefinitions;

import java.util.Properties;

import com.CucumberCraft.supportLibraries.SeleniumTestParameters;

import cucumber.api.Scenario;

public abstract class MasterStepDefs {

	protected Scenario currentScenario;
	protected SeleniumTestParameters currentTestParameters;
	protected Properties propertiesFileAccess;
}