@ESPO-1234-FF
Feature: Accounts functionalities 

@ESPO-1234-01-FF 
Scenario Outline: Create new account - mandatory fields 
	Given I login EspoCRM system   
	And I will be shown the Home page 	  
	When I click on "Account" menu 
	And I click on "Create Account" button 	
	And I enter valid value as "<Name>" and "<Website>" on Create Account page 
	And I click Save button         
	Then I will be shown the new account was created successfully 
	Examples:
    | Name      | Website                              |
    | Quan Dang | www.linkedin.com/in/quan-dang-17448a5|    