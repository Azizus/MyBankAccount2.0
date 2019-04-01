package com.kata.bankaccount.factory;

import java.util.Date;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.TransactionType;

public class TransactionFactory {

  public Transaction instansiateDepositTransaction(Account account, int amount) {
    return new Transaction(account.getAccountId(), new Date(), amount, account.getBalance(),
        TransactionType.DEPOSIT);
  }

  public Transaction instansiateWithdrawalTransaction(Account account, int amount) {
    return new Transaction(account.getAccountId(), new Date(), amount, account.getBalance(),
        TransactionType.WITHDRAWAL);
  }

}
