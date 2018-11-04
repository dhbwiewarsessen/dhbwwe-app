Feature: List ratings of users

  Scenario: navigation to overview of todays menu
    When User navigates to "/Overview"
    Then User should see ratings of todays menu
    Given todays menu exists

  Scenario: navigation to all ratings
    When User navigates to "/allRatings"
    Then User should see ratings of todays menu
    Given todays menu exists

  Scenario: Loading list unsuccessful
    When User navigates to "/Overview" OR "/allRatings"
    And loading list is unsuccessful
    Then User should see error "Loading ratings unsuccessful"