package com.CucumberCraft.pageObjects;

public class CreateAccountPage {

	public String btnSave = "xpath=//button[@data-action='save']";
	public String btnCancel = "xpath=//button[@data-action='cancel']";
	public String txtName = "xpath=//span[@class='label-text' and text()='Name']//following::input[1]";
	public String txtWebsite = "xpath=//span[@class='label-text' and text()='Website']//following::input[1]";
	public String txtEmail = "xpath=//span[@class='label-text' and text()='Email']//following::input[1]";
	public String txtPhone = "xpath=//span[@class='label-text' and text()='Phone']//following::input[1]";

	public CreateAccountPage() {
	}
}
