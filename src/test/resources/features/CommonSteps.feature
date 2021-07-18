@common 
Feature: Common Steps 

@common-01 
Scenario Outline: User actions to interact with web element
	Given user launches application under test 
	And "LOGIN_PAGE" shows up	
	And user types "<username>" into "LOGIN_PAGE_USERNAME_INPUT"
	And user types "<password>" into "LOGIN_PAGE_PASSWORD_INPUT"
	#When user clicks on "LOGIN_PAGE_LOGIN_BUTTON"
	When user submits form "LOGIN_PAGE_LOGIN_FORM"
	#And user wait for 10 seconds  
	Then "HOME_PAGE" shows up
	#Then "HOME_PAGE" shows up in 30 seconds
	#And "HOME_PAGE_DASHBOARD_LABEL" is present
	#Then "HOME_PAGE_DASHBOARD_LABEL" is present in 30 seconds 
	#And "LOGIN_PAGE_LOGIN_BUTTON" is not present
	#And "LOGIN_PAGE_LOGIN_BUTTON" is not present in 30 seconds	
#	And "HOME_PAGE_DASHBOARD_LABEL" shows text "Dashboard"
#	And "HOME_PAGE_DASHBOARD_LABEL" contains text "Dashb"
	And "HOME_PAGE_SUBSCRIBE_BUTTON" is present
	And "HOME_PAGE_SUBSCRIBE_BUTTON" has attribute "class" with value "button" 
	And "HOME_PAGE_SUBSCRIBE_BUTTON" has attribute "id" contain value "_link"
	And user refreshes current page
	And user navigates to "<url>"
	And user navigates back  
	And user navigate forward 
	And user switch to "ANY_IFRAME"
	And user switch to default
	
	Examples:
    | username | password | url                        |
    |    Admin | admin123 | https://www.google.com.vn/ |
  

      