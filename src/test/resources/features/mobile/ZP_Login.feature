Feature: ZP_Login 

@ZP-Login-01 
Scenario Outline: Login successfully 
	Given user launchs the application 
	And "LOGIN_ZALOPAY_PAGE" shows up	   
	And user clicks on "LZP_LOGIN_WITH_PHONE_NUMBER_BUTTON"
	And "LZP_PHONE_NUMBER_INPUT" is present
	And user types "<phone_number>" into "LZP_PHONE_NUMBER_INPUT"
	And user clicks on "LZP_CONTINUE_BUTTON"	
	And user dismisses Alert
	And "LZP_LOGIN_OTP_INPUT" is present	
	When user searches for OTP in SMS from "<sender>" to "<phone_number>" and types into "LZP_LOGIN_OTP_INPUT"
	And "ZALOPAY_PIN_PAGE" shows up	
	And user inputs pin "<pin>"
	And "HOME_PAGE" shows up
	#When user swipes "UP"		
	
#	And "LZP_PHONE_NUMBER_INPUT" is not present
#	And user waits until "LZP_PHONE_NUMBER_INPUT" is present in 60 seconds
	
#	When user waits for 10 seconds 
	 
#	And user navigates back
	
	#When user scrolls down until "HOME_APP_LINK_BANK_BUTTON" element is present 
	
	Examples:
      | phone_number | sender  | pin    |
      | 0367260747   | ZaloPay | 123456 |