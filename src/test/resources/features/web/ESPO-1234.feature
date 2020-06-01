@ESPO-1234
Feature: Accounts functionalities 

@ESPO-1234-01 
Scenario Outline: Create new account - mandatory fields 
	Given I login EspoCRM system   
	And I will be shown the Home page 	  
	When I click on "Account" menu 
	And I click on "Create Account" button 	
	And I enter valid value as "<Name>" and "<Website>" on Create Account page 
	And I click Save button         
	Then I will be shown the new account was created successfully 
	Examples:
    | Name        | Website                              |
    | Bach The Vu | www.linkedin.com/in/vu-bach-17448a58 |    
	
@ESPO-1234-02
Scenario: Create new account - all fields 
	Given I login EspoCRM system   
	And I load the input data of the test case "ESPO-1234-02" in json file "common_data"
	And I will be shown the Home page 	  
	When I click on "Account" menu  
	And I click on "Create Account" button 	
	And I enter valid values to all input field on Create Account page 
	And I click Save button         
	Then I will be shown the new account was created successfully
	
@ESPO-1234-03
Scenario Outline: Create new account - data driven 
	Given I login EspoCRM system   
	And I will be shown the Home page 	  
	When I click on "Account" menu 
	And I click on "Create Account" button 	
	And I enter valid value as "<Name>" and "<Website>" on Create Account page 
	And I click Save button         
	Then I will be shown the new account was created successfully	
	Examples:
    | Name        | Website                              |
    | Bach The Vu | www.linkedin.com/in/vu-bach-17448a58 |
    | Quan Dang   | www.linkedin.com/in/quan-dang-dade33 |    	 		