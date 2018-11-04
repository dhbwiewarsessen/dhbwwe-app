Feature: SignUp

  Scenario: Sign up successful
    When User navigates to "/signUp"
    And User enters "max" into input field with id "username"
    And User enters "123456" into input field with id "password"
    And User enters "123456" into input field with id "password-confirm"
    And User clicks on Button with text "Sign up"
    Then User should be logged in as "max"

  Scenario: Wrong confirm password
    When User navigates to "/signUp"
    And User enters "max" into input field with id "username"
    And User enters "123456" into input field with id "password"
    And User enters "654321" into input field with id "password-confirm"
    And User clicks on Button with text "Sign up"
    Then User should see error "the given passwords are not equal"

  Scenario: Username already exists
    Given User "max" is signed up
    When And User enters "max" into input field with id "username"
    And User enters "123456" into input field with id "password"
    And User enters "123456" into input field with id "password-confirm"
    And User clicks on Button with text "Sign up"
    Then User should see error "this username already exits"