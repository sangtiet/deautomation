@API-demo
Feature: Demo for API Testing 

@API-demo-01 
Scenario: Get post by ID
	Given I get post by the ID "3"
	
@API-demo-02
Scenario: Create new post
	Given I create a new post with data
	| userId       | 1111                 |
    | title        | API automation demo  |
    | body         | Created by VuB       |
    
#@API-demo-03
#Scenario: MySMS - Message verification
#	Given I create a MySMS request with data   
#	| address       | VinaPhone |
#    | offset        | 0 |
#    | limit         | 20 |
#    | authToken     | ejMNsCikQpek12e5KfYYIONhlzC5W-_HzIxvvTUMcKVOs8hh9S-MWeOgLaLcFjR9BrQP3VIf38Y |
#    | apiKey        | pcervE-HEopWcVhQiXaNZQ |
#    And The response code should be "200"
#    And The response message should be displayed
#
#@API-demo-04
#Scenario: PKL - Get access token
#  Given I create a PKL request with data
#    | grantType     | client_credentials |
#    | clientId      | pkl_tester |
#    | clientSecret  | dee48923-3c47-4223-8744-e84873722818 |
#  And The response code should be "200"
#  And The access token should be displayed