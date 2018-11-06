Feature: List ratings of users

  Scenario: navigation to overview of todays menu
    Given Menu Entry exists for today
    When User navigates to "/Overview"
    Then User should see ratings of todays menu

  Scenario: navigation to all ratings
    Given Menu Entry exists for today
    When User navigates to "/allRatings"
    Then User should see ratings of todays menu

  Scenario: Loading list unsuccessful
    When User navigates to "/Overview" OR "/allRatings"
    But loading list is unsuccessful
    Then User should see error "Loading ratings unsuccessful"