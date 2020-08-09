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
}