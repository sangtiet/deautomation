package com.CucumberCraft.supportLibraries;

/**
 * Enumeration to represent the mode of execution
 * 
 */
public enum ExecutionMode {
	
	LOCAL("local"),
	
	REMOTE("remote"),
	
	SEETEST("seetest"),
	
	MOBILE("mobile"),

	PERFECTO("perfecto"),

	PERFECTODESKTOP("perfectodesktop"),
	
	BROWSERSTACK("browserstack"),
	
	/**
	 * Execute on a mobile device using Appium
	 */

	;

	private String value;

	ExecutionMode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}