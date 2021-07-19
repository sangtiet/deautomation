@TEST_PIM_EL
Feature: PIM - Employee List 
  As a user
  I want to use PIM - Employee List to perform below activities
  	- Search an existing employee
  	- Create a new employee
  	- Delete an employee

@TEST_PIM_EL_001
Scenario: TEST_PIM_EL_001 - Search employee by name  
	Given user launches application under test 
	And "LOGIN_PAGE" shows up 
	When user login with valid username and password 
	Then "HOME_PAGE" shows up 
	And user clicks on menu following order "PIM > Employee List"	
	And "PIM_EMPLOYEE_LIST_PAGE" shows up
	And user clicks on "PIM_EMPLOYEE_LIST_PAGE_SEARCH_CRITERIA_EMPLOYEE_NAME_INPUT"
	And user wait for 1 seconds
	And user types "<data:employee_name>" into "PIM_EMPLOYEE_LIST_PAGE_SEARCH_CRITERIA_EMPLOYEE_NAME_INPUT"
	And user submits form "PIM_EMPLOYEE_LIST_PAGE_SEARCH_FORM"
	And user wait for 5 seconds

#@TEST_PIM_EL_002
#Scenario: TEST_PIM_EL_002 - Create a new employee  
#	Given user launches application under test 
#	And "LOGIN_PAGE" shows up 
#	When user login with valid username and password 
#	Then "HOME_PAGE" shows up 
#	And user clicks on menu following order "PIM > Employee List"	
#	And "PIM_EMPLOYEE_LIST_PAGE" shows up
#	
#@TEST_PIM_EL_003
#Scenario: TEST_PIM_EL_003 - Delete an employee  
#	Given user launches application under test 
#	And "LOGIN_PAGE" shows up 
#	When user login with valid username and password 
#	Then "HOME_PAGE" shows up 
#	And user clicks on menu following order "PIM > Employee List"	
#	And "PIM_EMPLOYEE_LIST_PAGE" shows up	
      