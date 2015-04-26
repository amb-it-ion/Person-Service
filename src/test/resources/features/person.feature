Feature: create person
	In order to deal with connection problems
	as a developer
	I want to be able to send the same person post request twice
	
	Scenario: create a person
		Given I create a new person with name 'user1' and random UUID
		When I post to '/person'
		Then I should get code '200'
	
	Scenario: create a person twice
		Given I create a new person with name 'user2' and random UUID
		When I post to '/person'
		When I post to '/person'
		Then I should get code '200'
