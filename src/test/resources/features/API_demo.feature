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