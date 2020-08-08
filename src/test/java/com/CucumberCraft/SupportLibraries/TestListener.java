package com.CucumberCraft.SupportLibraries;

import java.lang.reflect.Field;
import java.util.Properties;

//import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.BaseTestMethod;
import org.testng.log4testng.Logger;

/**
 * Will be called before every TestNG Method
 */
public class TestListener implements IInvokedMethodListener {

	static Logger log = Logger.getLogger(TestListener.class);

	private static Properties properties;

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

		SeleniumTestParameters testParameters = new SeleniumTestParameters();		
		Helper helper = new Helper();

		log.debug("BEGINNING: com.CucumberCraft.supportLibraries.WebDriverListener-beforeInvocation");

		if (method.isTestMethod()) {

			try {
				properties = Settings.getInstance();
				setDefaultTestParameters(method, testParameters);
				TestController.setTestParameters(testParameters);
				TestController.setHelper(helper);
			} catch (Exception ex) {
				log.error(ex.getMessage());
				ex.printStackTrace();
			}

		} else {
			log.warn("Provided method is NOT a TestNG testMethod!!!");
		}
		log.debug("END: org.stng.jbehave.LocalWebDriverListener.beforeInvocation");

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

		log.debug("BEGINNING: WebDriverListener.afterInvocation");
		/*
		 * change the name of the test method that will appear in the report to one that
		 * will contain very handy when analysing results.
		 */
		if (method.isTestMethod()) {
			try {
				BaseTestMethod bm = (BaseTestMethod) testResult.getMethod();
				Field f = bm.getClass().getSuperclass().getDeclaredField("m_methodName");
				f.setAccessible(true);
				String newTestName = testResult.getTestContext().getCurrentXmlTest().getName() + " - "
						+ bm.getMethodName() + " - ";
				log.info("Renaming test method name from: '" + bm.getMethodName() + "' to: '" + newTestName);
				f.set(bm, newTestName);

			} catch (Exception ex) {
				System.out.println("afterInvocation exception:\n" + ex.getMessage());
				ex.printStackTrace();
			}
		}
		log.debug("END: WebDriverListener.afterInvocation");
	}

	private void setDefaultTestParameters(IInvokedMethod method, SeleniumTestParameters testParameters) {
		try {
			String executionMode = method.getTestMethod().getXmlTest().getLocalParameters().get("ExecutionMode");
			String mobileExecutionPlatform = null;
			switch (executionMode) {

			case "LOCAL":
			case "REMOTE":

				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName") == null) {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));

				} else {
					testParameters.setBrowser(Browser
							.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName")));
				}

				break;

			case "MOBILE":
				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
					testParameters
							.setMobileToolName(MobileToolName.valueOf(properties.getProperty("DefaultMobileToolName")));
				} else {
					mobileExecutionPlatform = method.getTestMethod().getXmlTest().getLocalParameters()
							.get("MobileExecutionPlatform");
					testParameters.setMobileExecutionPlatform(MobileExecutionPlatform.valueOf(mobileExecutionPlatform));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("DeviceName") == null) {
					testParameters
							.setMobileToolName(MobileToolName.valueOf(properties.getProperty("DefaultDeviceName")));

				} else {
					testParameters
							.setDeviceName(method.getTestMethod().getXmlTest().getLocalParameters().get("DeviceName"));
				}
				
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("OSVersion") == null) {
					testParameters
							.setMobileToolName(MobileToolName.valueOf(properties.getProperty("DefaultOSVersion")));

				} else {
					testParameters.setmobileOSVersion(method.getTestMethod().getXmlTest().getLocalParameters().get("OSVersion"));
				}
				
				break;
			case "SEETEST":
				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
				mobileExecutionPlatform = method.getTestMethod().getXmlTest().getLocalParameters()
						.get("MobileExecutionPlatform");
				testParameters.setMobileExecutionPlatform(MobileExecutionPlatform.valueOf(mobileExecutionPlatform));

				testParameters
						.setDeviceName(method.getTestMethod().getXmlTest().getLocalParameters().get("DeviceName"));
				testParameters.setOsVersion(method.getTestMethod().getXmlTest().getLocalParameters().get("OSVersion"));
				testParameters
						.setSerialNumber(method.getTestMethod().getXmlTest().getLocalParameters().get("SerialNumber"));
				break;
			case "PERFECTO":

				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
					testParameters.setMobileToolName(
							MobileToolName.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
				} else {
					mobileExecutionPlatform = method.getTestMethod().getXmlTest().getLocalParameters()
							.get("MobileExecutionPlatform");
					testParameters.setMobileExecutionPlatform(MobileExecutionPlatform.valueOf(mobileExecutionPlatform));
				}

				if (testParameters.getIsDeviceUdid()) {

					testParameters
							.setDeviceName(method.getTestMethod().getXmlTest().getLocalParameters().get("DeviceName"));

				} else {
					testParameters
							.setModelName(method.getTestMethod().getXmlTest().getLocalParameters().get("ModelName"));
					testParameters.setManuFacturerName(
							method.getTestMethod().getXmlTest().getLocalParameters().get("ManufacturerName"));

				}

				break;

			case "PERFECTODESKTOP":

				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("OSVersion") == null) {
					testParameters.setOsVersion(properties.getProperty("DefaultOSVersion"));
				} else {
					String osVersion = method.getTestMethod().getXmlTest().getLocalParameters().get("OSVersion");
					testParameters.setOsVersion(osVersion);
				}
				testParameters.setBrowserVersion(
						method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserVersion"));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName") == null) {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));

				} else {
					testParameters.setBrowser(Browser
							.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName")));
				}

				break;

			case "BROWSERSTACK":
				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName") == null) {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));

				} else
					testParameters.setBrowser(Browser
							.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName")));
				break;
			default:
				testParameters.setExecutionMode(ExecutionMode.valueOf(properties.getProperty("DefaultExecutionMode")));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName") == null) {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));

				} else {
					testParameters.setBrowser(Browser
							.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName")));
				}
				break;
			}

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}
}