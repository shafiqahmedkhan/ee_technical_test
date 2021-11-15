Feature: Hotel Booking Form
  
  Scenario: User is able to make a hotel booking
    When enter all valid information
    And save the hotel booking
    Then the hotel booking will be saved

  Scenario: User can delete a hotel booking
    Given I have a hotel booking
    When I delete the hotel booking
    Then then booking cannot be seen

