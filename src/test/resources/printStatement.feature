#Author: Aziz Saidi

Feature: Statement Printing Feature
  In order to check my transactions As a bank client I want to see the history (operation, date, amount, balance)


  Scenario: print account statement
    Given ID of the Account
    When account exists
    And has transactions
    Then print list of transactions with a header

 