@common 
Feature: Common Steps 

@common-01 
Scenario: User actions to interact with web element
	Given user launches application under test 
	And "LOGIN_PAGE" shows up
	And "LOGIN_PAGE" shows up in 30 seconds
	When user clicks on "LOGIN_PAGE_LOGIN_BUTTON"
	Then "HOME_PAGE_DASHBOARD_LABEL" is present
	Then "HOME_PAGE_DASHBOARD_LABEL" is present in 30 seconds
	And "LOGIN_PAGE_LOGIN_BUTTON" is not present
	And "LOGIN_PAGE_LOGIN_BUTTON" is not present in 30 seconds	
	And "HOME_PAGE_DASHBOARD_LABEL" shows text "Dashboard"
	And "HOME_PAGE_DASHBOARD_LABEL" contains text "Dashboard" 

      