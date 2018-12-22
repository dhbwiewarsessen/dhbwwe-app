Feature: Manage Rating
  As a User
  I want to be able to create a new rating, edit it and delete it

  Scenario: Create Rating
    When User navigates to "CreateRating"
    And User selects a random Menu from the Dropdown List
    And User selects "4" stars for the rating
    And User enters "test-354321" into input field with id "comment"
    Then Rating should be added on "MyRatings"

  Scenario: Edit Rating
    When User navigates to "MyRatings"
    And User longclicks entry with comment "test-354321"
    And User selects "edit" on the popup menu
    And User selects "3.5" stars for the rating
    And User confirms the changes
    Then the rating with comment "<string>" should have "3.5" stars

  Scenario: Delete Rating
    When User navigates to "MyRatings"
    And User longclicks entry with comment "test-354321"
    And User selects "delete" on the popup menu
    And User confirms on the Confirm Popup
    Then the rating with comment "test-354321" should be deleted


