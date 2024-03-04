Feature: Capturing Error Message Of Wrong Phone Number

  Scenario: Capturing Error Message Of Wrong Phone Number
    Given user is on the freeListing Page
    When user need to enter wrong phone number
    Then user need to capture error message
