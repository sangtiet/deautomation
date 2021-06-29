package com.CucumberCraft.StepDefinitions;

import java.util.Properties;
import com.CucumberCraft.SupportLibraries.SeleniumTestParameters;
import cucumber.api.Scenario;

public abstract class MasterStepDefs {

	protected Scenario currentScenario;
	protected SeleniumTestParameters currentTestParameters;
	protected Properties propertiesFileAccess;
}