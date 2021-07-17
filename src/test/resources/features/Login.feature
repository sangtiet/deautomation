@TEST-Login
Feature: Login 

@TEST-Login-001
Scenario: Login successfully 
	Given user launches application under test 
	And "LOGIN_PAGE" shows up 
	When user login with valid username and password 
	And "HOME_PAGE" shows up 
      