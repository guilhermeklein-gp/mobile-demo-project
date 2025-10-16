Feature: MainFeatureTest.feature

  @Test
  Scenario: Test scenario
    Given I've installed the application
    When the user clicks on Get Started
    And the user enters valid login credentials
    And clicks to Login
    Then the user is taken to the Location screen
