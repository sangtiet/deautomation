@TF-Login
Feature: Login 

@TF-Login-01 
Scenario Outline: Login successfully 
# 	Given user starts performance transaction
#	When user launchs the application
	Given "LANDING_PAGE" shows up 
	And user clicks on element by visible text "Tiếp"
	And user clicks on element by visible text "Tiếp"
	And user clicks on element by visible text "Bắt đầu"
	When user waits for 5 seconds
#	Then "PRE_LOGIN_PAGE" shows up	   
#	And user clicks on "PLP_LOGIN_BUTTON"
#	And "LOGIN_PAGE" shows up
##	When user waits for 5 seconds
##	And user ends performance transaction
##	And user clicks on "LP_CLOSE_POPUP_BUTTON"
#	And user types "<user_name>" into "LP_USERNAME_INPUT"
#	And user types "<password>" into "LP_PASSWORD_INPUT"
#	And user clicks on "LP_LOGIN_BUTTON"	
#	And "HOME_PAGE" shows up
#	When user waits for 5 seconds
#	And user clicks on "HP_UTILITY_POTENTIAL_CLIENT_LABEL"
#	And user clicks on element by visible text "Tạo khách hàng tiềm năng"
#	When user waits for 10 seconds
#	And user ends performance transaction
	
	Examples:
      | user_name  | password |
      | 0400000000 | Test@123 |
      