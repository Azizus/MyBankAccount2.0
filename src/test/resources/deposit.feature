#Author: Aziz Saidi

Feature: Deposit Feature
  In order to save money As a bank client I want to make a deposit in my account

  Scenario: deposit money in bank account 
    Given the ID of the Account
    And the amount of deposit
    When account exists
    Then create and save a deposit transaction
