Feature: User manages HOCS teams and units
  
  Background: 
    Given that I have navigated to the Management UI as the user "EAMON"

    Scenario: User navigates to a management page
      When I navigate to the "TEAM" Management page
      Then I should be taken to the "TEAM" Management page