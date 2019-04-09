#Author: Aziz Saidi

Feature: Withdrawal Feature
  In order to retrieve some or all of my savings As a bank client I want to make a withdrawal from my account

  Scenario: withdraw money from my account 
    Given the ID of the account
    And the amount to withdraw
    When the account exists
    And balance is sufficient
    Then create and save a withdrawal transaction
