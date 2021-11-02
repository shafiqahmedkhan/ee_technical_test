Feature: Hotel Booking Form

  Background:
    Given I navigate to the hotel booking form page

  Scenario: User is able to make a hotel booking
    When enter all valid information
    And save the hotel booking
    Then the hotel booking will be saved

#  Scenario: User is able to delete a hotel booking
#    And there is a hotel booking
#    When I delete a hotel booking
#    Then the hotel booking will be removed
