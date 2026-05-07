Feature: Student Registration Form

  Scenario: Fill and submit the registration form
    Given I open the registration page
    When I enter name "Paul Kerese"
    And I enter email "paul.kerese@example.com"
    And I select gender "Male"
    And I enter mobile "1234567890"
    And I enter date of birth "01/07/1916"
    And I enter subject "Chess"
    And I select hobby "Sports"
    And I enter current address "Narva, Estonia"
    And I select state "NCR"
    And I select city "Agra"
    And I click submit
    Then the form should be submitted successfully