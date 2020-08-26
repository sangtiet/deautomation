@ZP-Login2
Feature: ZP_Login 2

@ZP-Login2-01 
Scenario Outline: Login successfully 2
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
	Given "HOME_PAGE" shows up
	And user clicks on element by visible text "Liên Kết Ngân Hàng"
	And user clicks on element by visible text "Thêm liên kết"
	And "CHOSE_LINK_PAGE" shows up
	When user waits for 10 seconds
	When user scrolls down until "CLP_SHINHANBANK_CARD" element is present
	And user clicks on "CLP_SHINHANBANK_CARD"		
	Then "BANK_LINK_PAGE" shows up
	And user types "<card_number>" into "BLP_CARD_NUMBER_INPUT"
	And user types "<issue_date>" into "BLP_ISSUE_DATE_INPUT"
	And user types "<card_owner>" into "BLP_CARD_OWNER_INPUT"	
	And user clicks on "BLP_LINK_NOW_BUTTON"
	When user waits for 10 seconds
#	And "LZP_PHONE_NUMBER_INPUT" is not present
#	And user waits until "LZP_PHONE_NUMBER_INPUT" is present in 60 seconds
#	And user navigates back
	
	Examples:
      | phone_number | sender  | pin    | card_number | issue_date | card_owner     |
      | 0367260747   | ZaloPay | 123456 | 888888      | 02/19      | Thoi Thuc Phan |