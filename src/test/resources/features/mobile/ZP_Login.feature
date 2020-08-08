Feature: Android Testing 

@AndroidTesting 
Scenario: Launch app on real device 
	Given I launch app on real device 
	And I click Add New button	   
	And I verify the Accounts label