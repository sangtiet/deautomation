package com.CucumberCraft.SupportLibraries;

public enum MobileExecutionPlatform {

	/**
	 * Execute on an Android Device
	 */
	ANDROID("Android"),

	/**
	 * Execute on an iOS Device
	 */
	IOS("iOS"),
	/**
	 * Execute on a browser in Android Device
	 */
	WEB_ANDROID("WEB_ANDROID"),

	/**
	 * Execute on browser in iOS Device
	 */
	WEB_IOS("WEB_IOS");
	
	;
	
	private String value;

	MobileExecutionPlatform(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
