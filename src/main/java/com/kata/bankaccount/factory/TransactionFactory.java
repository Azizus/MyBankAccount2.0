package com.kata.bankaccount.factory;

import java.util.Date;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.TransactionType;

public class TransactionFactory {

  public Transaction deposit(Account account, int amount) {
    return Transaction.builder()//
        .accountId(account.getAccountId())//
        .date(new Date())//
        .amount(amount)//
        .balance(account.getBalance() + amount)//
        .type(TransactionType.DEPOSIT)//
        .build();

  }

  public Transaction withdraw(Account account, int amount) {
    return Transaction.builder()//
        .accountId(account.getAccountId())//
        .date(new Date())//
        .amount(amount).balance(account.getBalance() + amount)//
        .type(TransactionType.WITHDRAWAL)//
        .build();

  }
}
