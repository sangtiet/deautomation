Feature: ZP_Login 

@ZP-Login-01 
Scenario Outline: Login successfully 
	Given user launchs the application 
	And "LOGIN_ZALOPAY_PAGE" shows up	   
	And user clicks on "LZP_LOGIN_WITH_PHONE_NUMBER_BUTTON"
	When user waits for 10 seconds
#	When user searches for OTP in SMS from '<sender>' to '<phone_number>' and types into "LZP_LOGIN_OTP_INPUT"
#	And "LZP_PHONE_NUMBER_INPUT" is present
#	And "LZP_PHONE_NUMBER_INPUT" is not present
#	And user waits until "LZP_PHONE_NUMBER_INPUT" is present in 60 seconds
#	And user types '<phone_number>' into "LZP_PHONE_NUMBER_INPUT"
#	And user navigates back
#	And user inputs pin '<pin>'
#	When user scrolls "HOME_APPS_LIST" until "HOME_APP_LINK_BANK_BUTTON" is present
	
	Examples:
      | phone_number | sender  | pin    |
      | 0367260747   | ZaloPay | 123456 |