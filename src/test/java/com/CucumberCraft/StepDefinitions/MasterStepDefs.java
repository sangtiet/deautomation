package com.CucumberCraft.StepDefinitions;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import com.CucumberCraft.SupportLibraries.SeleniumTestParameters;

import cucumber.api.Scenario;
import io.appium.java_client.AppiumDriver;

public abstract class MasterStepDefs {

	protected Scenario currentScenario;
	protected SeleniumTestParameters currentTestParameters;
	protected Properties propertiesFileAccess;
	
	@SuppressWarnings("rawtypes")
	protected AppiumDriver appiumDriver;	
}