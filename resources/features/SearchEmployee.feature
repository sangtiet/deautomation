Feature: Search employee 

Background: Clear search 
	Given I clear the Search field   
	
@SearchByName @Search_Regression
Scenario: Search by name 
	Given I navigate to the URL "http://apps.gamonoid.com/icehrm-open/login.php" 
	And   I will be shown the Login page 
	When  I enter Username as "admin" 
	And   I enter Password as "admin" 
	And   I click Login button 
	Then  I will be shown the Home page  
	And   I click Manage Employee
	And   I will be shown the Employee list
	When  I search employee by name as "Ryan" 
	Then  I will be shown the search result in Employee list  
	
@SearchAndView @Search_Regression  
Scenario: Search and view employee info  
	Given I will be shown the Employee list
	When  I search employee by name as "Ryan"
	Then  I will be shown the search result in Employee list
	And   I click to view Employee detail info
	And   I will be shown the Employee detail info displayed correctly  
	And   I end WedDriver session  