@filter
Feature: Validate Filter Functionality

  Scenario: apply filters and validated sorted results
    Given user is on RedBus homepage
    When user searches buses from "Bengaluru" to "Chennai" for tomorrow
    And user selects "AC" and "SLEEPER" and "Afternoon" filters
    And buses are sorted by price in "ascending" order
    Then at least one bus should be displayed
	And all displayed buses should be "AC" and "Sleeper"
	And all buses should have departure time Between "12:00" and "18:00"
	And bus prices should be sorted in ascending order
	
	
