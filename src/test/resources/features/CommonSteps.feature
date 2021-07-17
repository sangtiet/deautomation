@common 
Feature: Common Steps 

@common-01 
Scenario Outline: User actions to interact with web element
	Given user launches application under test 
	And "LOGIN_PAGE" shows up	
	And user types "<username>" into "LOGIN_PAGE_USERNAME_INPUT"
	And user types "<password>" into "LOGIN_PAGE_PASSWORD_INPUT"
	When user submits form "LOGIN_PAGE_LOGIN_FORM" 
	Then "HOME_PAGE" shows up in 30 seconds
	And "HOME_PAGE_DASHBOARD_LABEL" is present
	Then "HOME_PAGE_DASHBOARD_LABEL" is present in 30 seconds
	And "LOGIN_PAGE_LOGIN_BUTTON" is not present
	And "LOGIN_PAGE_LOGIN_BUTTON" is not present in 30 seconds	
	And "HOME_PAGE_DASHBOARD_LABEL" shows text "Dashboard"
	And "HOME_PAGE_DASHBOARD_LABEL" contains text "Dashb"
	When user clicks on "LOGIN_PAGE_LOGIN_BUTTON"
	And user wait for 10 seconds 
	Examples:
    | username | password |
    |    Admin | admin123 |
  

      