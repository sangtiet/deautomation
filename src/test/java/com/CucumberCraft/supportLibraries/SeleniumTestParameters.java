package com.CucumberCraft.supportLibraries;

/**
 * Class to encapsulate various input parameters required for each test script
 * 
 */
public class SeleniumTestParameters {

	private ExecutionMode executionMode;
	private Browser browser;
	private String deviceName;
	private MobileExecutionPlatform mobileExecutionPlatform;
	private MobileToolName mobileToolName;
	private String mobileOsVersion;	
	private String manufacturer;
	private String modelName;
	private boolean isDeviceUdid;
	private String scenarioName;
	private String serialNumber;
	private String browserVersion;
	private String osVersion;

	public SeleniumTestParameters() {

	}
	
	/**
	 * Function to get the {@link ExecutionMode} for the test being executed
	 * 
	 * @return The {@link ExecutionMode} for the test being executed
	 */
	public ExecutionMode getExecutionMode() {
		return executionMode;
	}

	/**
	 * Function to set the {@link ExecutionMode} for the test being executed
	 * 
	 * @param executionMode
	 *            The {@link ExecutionMode} for the test being executed
	 */
	public void setExecutionMode(ExecutionMode executionMode) {
		this.executionMode = executionMode;
	}

	/**
	 * Function to get the {@link MobileExecutionPlatform} for the test being
	 * executed
	 * 
	 * @return The {@link MobileExecutionPlatform} for the test being executed
	 */
	public MobileExecutionPlatform getMobileExecutionPlatform() {
		return mobileExecutionPlatform;
	}

	/**
	 * Function to set the {@link MobileExecutionPlatform} for the test being
	 * executed
	 * 
	 * @param mobileExecutionPlatform
	 *            The {@link MobileExecutionPlatform} for the test being
	 *            executed
	 */
	public void setMobileExecutionPlatform(
			MobileExecutionPlatform mobileExecutionPlatform) {
		this.mobileExecutionPlatform = mobileExecutionPlatform;
	}

	/**
	 * Function to get the {@link MobileToolName} for the test being executed
	 * 
	 * @return The {@link MobileToolName} for the test being executed
	 */
	public MobileToolName getMobileToolName() {
		return mobileToolName;
	}

	/**
	 * Function to set the {@link MobileToolName} for the test being executed
	 * 
	 * @param toolName
	 *            The {@link MobileToolName} for the test being executed
	 */
	public void setMobileToolName(MobileToolName mobileToolName) {
		this.mobileToolName = mobileToolName;
	}

	/**
	 * Function to get the {@link Browser} on which the test is to be executed
	 * 
	 * @return The {@link Browser} on which the test is to be executed
	 */
	public Browser getBrowser() {
		return browser;
	}

	/**
	 * Function to set the {@link Browser} on which the test is to be executed
	 * 
	 * @param browser
	 *            The {@link Browser} on which the test is to be executed
	 */
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	/**
	 * Function to get the OS Version of device on which the test is to be
	 * executed
	 * 
	 * @return The OS Version of device Version on which the test is to be
	 *         executed
	 */
	public String getMobileOSVersion() {
		return mobileOsVersion;
	}

	/**
	 * Function to set the OS Version of device Version on which the test is to
	 * be executed
	 * 
	 * @param version
	 *            The OS Version of device Version on which the test is to be
	 *            executed
	 */
	public void setmobileOSVersion(String mobileOsVersion) {
		this.mobileOsVersion = mobileOsVersion;
	}

	/**
	 * Function to get the name of the mobile device on which the test is to be
	 * executed
	 * 
	 * @return The name of the mobile device on which the test is to be executed
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * 
	 * @param deviceName
	 *            The name of the mobile device on which the test is to be
	 *            executed
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * Function to get the name of the manufacturer Name on which the test is to
	 * be executed
	 * 
	 * @return The name of the manufacturer Name on which the test is to be
	 *         executed
	 */
	public String getManuFacturerName() {
		return manufacturer;
	}

	/**
	 * 
	 * @param manufacturer
	 *            The name of the manufacturer Name on which the test is to be
	 *            executed
	 */
	public void setManuFacturerName(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Function to get the name of the modelName on which the test is to be
	 * executed
	 * 
	 * @return The name of the modelName on which the test is to be executed
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * 
	 * @param modelName
	 *            The name of the modelName on which the test is to be executed
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * Function to get the boolean if Device is UDID
	 * 
	 * @return boolean if Device is UDID
	 */
	public boolean getIsDeviceUdid() {
		return isDeviceUdid;
	}

	/**
	 * set the boolean if the device is UDID
	 */
	public void setIsDeviceUdid(boolean isDeviceUdid) {
		this.isDeviceUdid = isDeviceUdid;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public String getScenarioName() {
		return scenarioName;
	}
	
	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}