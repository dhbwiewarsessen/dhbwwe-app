Feature: Register

  Scenario: Register successful
    When User navigates to "/Register"
    And User enters "max" into input field with id "username"
    And User enters "123456" into input field with id "password"
    And User enters "123456" into input field with id "password-confirm"
    And User clicks on Button with text "Register"
    Then User should be logged in as "max"

  Scenario: Wrong confirm password
    When User navigates to "/Register"
    And User enters "max" into input field with id "username"
    And User enters "123456" into input field with id "password"
    And User enters "654321" into input field with id "password-confirm"
    And User clicks on Button with text "Register"
    Then User should see error "the given passwords are not equal"

  Scenario: Username already exists
    Given User "max" is registered
    When And User enters "max" into input field with id "username"
    And User enters "123456" into input field with id "password"
    And User enters "123456" into input field with id "password-confirm"
    And User clicks on Button with text "Register"
    Then User should see error "this username already exits"