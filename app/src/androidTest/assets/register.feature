Feature: Register
  As a User
  I want to create a new account and be logged in afterwards

  Scenario: Register successful
    When User navigates to "Register"
    And User enters "Max" into input field with id "name"
    And User enters an unique username into input field with id "username"
    And User enters "max@muster.com" into input field with id "email"
    And User enters "securePassword123" into input field with id "password"
    And User enters "securePassword123" into input field with id "password-confirm"
    And User clicks on Button with id "register"
    Then User should be logged in as "Max"

  Scenario: Passwords are not equal
    When User navigates to "Register"
    And User enters "Max" into input field with id "name"
    And User enters an unique username into input field with id "username"
    And User enters "max@muster.com" into input field with id "email"
    And User enters "onePassword123" into input field with id "password"
    And User enters "anotherPassword123" into input field with id "password-confirm"
    And User clicks on Button with id "register"
    Then User should see error "Passwords don't match!" on input field with id "password"

  Scenario: Username already exists
    Given User "max" is registered
    When User navigates to "Register"
    And User enters "Max" into input field with id "name"
    And User enters "max" into input field with id "username"
    And User enters "max@muster.com" into input field with id "email"
    And User enters "securePassword123" into input field with id "password"
    And User enters "securePassword123" into input field with id "password-confirm"
    And User clicks on Button with id "register"
    Then User should see error
          """
          Register Failed:
          username not available
          """