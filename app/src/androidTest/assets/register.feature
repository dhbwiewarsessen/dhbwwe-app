Feature: Register
  As a User
  I want to create a new account and be logged in afterwards

  Scenario: Register successful
    When User navigates to "/Register"
    And User enters "Max" into input field with id "name"
    And User enters "max" into input field with id "username"
    And User enters "max@muster.com" into input field with id "email"
    And User enters "savePassword123" into input field with id "password"
    And User enters "savePassword123" into input field with id "password-confirm"
    And User clicks on Button with id "Register"
    Then User should be logged in as "max"

  Scenario: Passwords are not equal
    When User navigates to "/Register"
    And User enters "max" into input field with id "username"
    And User enters "123456" into input field with id "password"
    And User enters "654321" into input field with id "password-confirm"
    And User clicks on Button with id "Register"
    Then User should see error "the given passwords are not equal"

  Scenario: Username already exists
    Given User "max" is registered
    When And User enters "max" into input field with id "username"
    And User enters "123456" into input field with id "password"
    And User enters "123456" into input field with id "password-confirm"
    And User clicks on Button with id "Register"
    Then User should see error "this username already exits"