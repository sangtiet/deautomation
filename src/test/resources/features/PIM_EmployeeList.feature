@TEST_PIM_EL
Feature: Employee List 

@TEST_PIM_EL_001
Scenario: TEST_PIM_EL_001 - Search employee by name  
	Given user launches application under test 
	And "LOGIN_PAGE" shows up 
	When user login with valid username and password 
	Then "HOME_PAGE" shows up 
	#And user clicks on menu following order "PIM > Employee List"
	And user clicks on menu following order "PIM > Configuration > Data Import"
#	And "PIM_EMPLOYEE_LIST_PAGE" shows up
#	And user scrolls down 200 pixel 
	And user wait for 5 seconds
	
      