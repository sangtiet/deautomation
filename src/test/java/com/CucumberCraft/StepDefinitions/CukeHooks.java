package com.CucumberCraft.StepDefinitions;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.log4testng.Logger;

import com.CucumberCraft.SupportLibraries.Settings;
import com.CucumberCraft.SupportLibraries.TestController;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CukeHooks extends MasterStepDefs {

	static Logger log;
	static Properties properties = Settings.getInstance();

	static {
		log = Logger.getLogger(CukeHooks.class);
	}

	@Before
	public void setUp(Scenario scenario) {
		try {
			currentScenario = scenario;
			propertiesFileAccess = properties;
			currentTestParameters = TestController.getTestParameters();
			currentTestParameters.setScenarioName(scenario.getName());
		} catch (Exception e) {
			log.error("Error at Before Scenario " + e.getMessage());
		}
	}

	@After
	public void embedScreenshot(Scenario scenario) {		
		try {
			String ExecutionMode = TestController.getTestParameters().getExecutionMode().toString();
			switch(ExecutionMode) {
			   case "LOCAL" :
			      currentScenario.write("-> Browser: " + TestController.getTestParameters().getBrowser());
			      break; // optional
			   
			   case "MOBILE" :
				   currentScenario.write("-> MobileExecutionPlatform: " + TestController.getTestParameters().getMobileExecutionPlatform());
				   currentScenario.write("-> DeviceName: " + TestController.getTestParameters().getDeviceName());
				   currentScenario.write("-> OsVersion: " + TestController.getTestParameters().getMobileOSVersion());
			      break; // optional			
			}
			
			update(scenario);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}

	/**
	 * Embed a screenshot in test report if test is marked as failed And Update Task
	 * in JIRA
	 */
	private void update(Scenario scenario) {
		if (scenario.isFailed()) {
			byte[] screenshot;
			try {
				screenshot = ((TakesScreenshot) TestController.getAppiumDriver()).getScreenshotAs(OutputType.BYTES);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				screenshot = ((TakesScreenshot) TestController.getWebDriver()).getScreenshotAs(OutputType.BYTES);
			}
			scenario.embed(screenshot, "image/png");
		}
	}
}