package com.CucumberCraft.pageObjects;

public class HomePage {

	public String lnkAccounts = "xpath=//li[@data-name='Account']//child::a";
	public String lstStream = "xpath=//span[text()='Stream' and @title='Refresh']//following::ul[1]";

	public HomePage() {
	}
}
