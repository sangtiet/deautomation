@CarLogix
Feature: CarLogix BE services testing 

@CarLogix_01 
Scenario Outline: CarLogix_01 - Get Facility service
	Given user creates Get Facility request with "<mark_code>" 
	Then the response code of "GET_FACILITY" request should be 200
	And the Facilities list should contain "<expected_amount_of_facilities>" items
	Examples:
    | mark_code | expected_amount_of_facilities |
    | RAIL      | 1                             |
    
@CarLogix_02
Scenario Outline: CarLogix_02 - Search Work Order service
	Given user creates Search Work Order request with data 
		| markCode   | RAIL |
		| fsac       | 00014 |
	    | carIds     | null |
        | repairDate | null |
        | statuses   | DRAFT_PENDING |
        | user       | LAMTRAN | 
	Then the response code of "SEARCH_WORK_ORDER" request should be 200   
	And the Work Order list should contain "<expected_amount_of_work_orders>" items
	Examples:
    | expected_amount_of_work_orders |
    | 500                            | 
	


