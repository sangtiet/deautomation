package com.CucumberCraft.TestNGrunners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterTest;

import com.CucumberCraft.SupportLibraries.TestController;
import com.CucumberCraft.SupportLibraries.TimeStamp;
import com.CucumberCraft.SupportLibraries.Util;
import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@ExtendedCucumberOptions(jsonReport = "target/cucumber-report/Smoke/cucumber.json", 
	jsonUsageReport = "target/cucumber-report/Smoke/cucumber-usage.json", 
	outputFolder = "target/cucumber-report/Smoke", 
	detailedReport = true, 
	detailedAggregatedReport = true, 
	overviewReport = true, 
	usageReport = true)

@CucumberOptions(features = "src/test/resources/features", 	
	glue = { "com.CucumberCraft.StepDefinitions" }, 
	tags = {"@TEST_PIM_EL_001"}, //-----> define which tests you want to run here
	monochrome = true,
	//dryRun = true,
	plugin = {
		"pretty", 
		"pretty:target/cucumber-report/Smoke/pretty.txt",
		"html:target/cucumber-report/Smoke",
		"json:target/cucumber-report/Smoke/cucumber.json",
		"junit:target/cucumber-report/Smoke/cucumber-junitreport.xml" })

public class SmokeTestRunner extends AbstractTestNGCucumberTests {

	@AfterTest
	private void test() {				
		generateCustomReports();
		copyReportsFolder();
		
		String ExecutionMode = TestController.getTestParameters().getExecutionMode().toString();
		switch (ExecutionMode) {
		case "WEB":
			TestController.endWebDriver();
			break; // optional

		case "MOBILE":
		case "SEETEST":
			TestController.endAppiumDriver();
			break; // optional
		}
	}

	private void generateCustomReports() {
		CucumberResultsOverview overviewReports = new CucumberResultsOverview();
		overviewReports.setOutputDirectory("target");
		overviewReports.setOutputName("cucumber-results");		
		overviewReports
				.setSourceFile("target/cucumber-report/Smoke/cucumber.json");
		try {
			overviewReports.executeFeaturesOverviewReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void copyReportsFolder() {
		String timeStampResultPath = TimeStamp.getInstance();
		
		File source = new File(Util.getTargetPath());
		File dest = new File(timeStampResultPath);

		try {
			FileUtils.copyDirectory(source, dest);
			try {
				FileUtils.cleanDirectory(source);
			} catch (Exception e) {

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		TimeStamp.reportPathWithTimeStamp = null;
	}
}