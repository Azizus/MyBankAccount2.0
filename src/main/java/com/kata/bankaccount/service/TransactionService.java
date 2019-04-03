package com.kata.bankaccount.service;

import java.util.List;
import javax.security.auth.login.AccountException;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.exceptions.TransactionException;

public interface TransactionService {

  public List<Transaction> allTransactions(long accountId);

  public Transaction deposit(long accountId, int amount) throws AccountException;

  public Transaction withdraw(long accountId, int amount)
      throws AccountException, TransactionException;

  void printStatement(long accountId) throws AccountException;


}
