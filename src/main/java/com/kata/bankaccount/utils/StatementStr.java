package com.kata.bankaccount.utils;

public class StatementStr {

  public static final String HEADER = "OPERATION || DATE || AMOUNT || BALANCE";

  public static final String accountInformation(long accountId) {
    return "L'identifiant du compte est " + Long.toString(accountId);
  }
}
