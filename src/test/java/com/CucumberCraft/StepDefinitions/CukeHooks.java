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

	static Logger log = Logger.getLogger(CukeHooks.class);
	static Properties properties = Settings.getInstance();
	static String ExecutionMode = TestController.getTestParameters().getExecutionMode().toString();

	@Before
	public void setUp(Scenario scenario) {
		try {
			currentScenario = scenario;
			propertiesFileAccess = properties;
			currentTestParameters = TestController.getTestParameters();

			appiumDriver = TestController.getAppiumDriver();
			currentTestParameters.setScenarioName(scenario.getName());
		} catch (Throwable e) {
			log.error("Error at Before Scenario " + e.getMessage());
			TestController.getHelper().writeStepFAIL();
		}
	}

	@After
	public void embedScreenshot(Scenario scenario) throws Throwable {
		switch (ExecutionMode) {
		case "LOCAL":
			currentScenario.write("-> Browser: " + TestController.getTestParameters().getBrowser());
			break; // optional

		case "MOBILE":
			currentScenario.write(
					"-> MobileExecutionPlatform: " + TestController.getTestParameters().getMobileExecutionPlatform());
			currentScenario.write("-> DeviceName: " + TestController.getTestParameters().getDeviceName());
			currentScenario.write("-> OsVersion: " + TestController.getTestParameters().getMobileOSVersion());
			break; // optional
		}
		update(scenario);
	}

	private void update(Scenario scenario) throws Throwable {
		if (scenario.isFailed()) {
			byte[] screenshot = null;

			switch (ExecutionMode) {
			case "LOCAL":
				screenshot = ((TakesScreenshot) TestController.getWebDriver()).getScreenshotAs(OutputType.BYTES);
				break; // optional

			case "MOBILE":
				screenshot = ((TakesScreenshot) TestController.getAppiumDriver()).getScreenshotAs(OutputType.BYTES);
				break; // optional
			}
			scenario.embed(screenshot, "image/png");
		}
	}
}