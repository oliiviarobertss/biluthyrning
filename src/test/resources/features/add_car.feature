Feature: Add a car to the system

  Scenario: Successfully add a car
    Given a car with registration number "GHI789", brand "Toyota", and model "Corolla"
    When the car is added to the system
    Then the car should be retrievable in the list of available cars
