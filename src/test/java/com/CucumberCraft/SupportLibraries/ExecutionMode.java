package com.CucumberCraft.SupportLibraries;

/**
 * Enumeration to represent the mode of execution
 * 
 */
public enum ExecutionMode {
	
	WEB("web"),
	
	REMOTE("remote"),
	
	MOBILE("mobile"),
	
	SEETEST("seetest")
	
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