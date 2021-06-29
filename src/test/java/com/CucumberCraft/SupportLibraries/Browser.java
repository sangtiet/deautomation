package com.CucumberCraft.SupportLibraries;

/**
 * Enumeration to represent the browser to be used for execution
 */
public enum Browser {
	CHROME("chrome"),
	FIREFOX("firefox"),
	INTERNET_EXPLORER("internet explorer"),
	OPERA("opera"),
	API("api"),
	EDGE("edge");
	
	private String value;
	
	Browser(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}