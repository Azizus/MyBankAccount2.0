package com.kata.bankaccount.service;

import java.util.LinkedList;
import java.util.List;
import javax.security.auth.login.AccountException;
import org.springframework.stereotype.Service;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.exceptions.TransactionException;

@Service
public interface TransactionService {

  public List<Transaction> allTransactions(long accountId);

  public Transaction deposit(long accountId, int amount) throws AccountException;

  public Transaction withdraw(long accountId, int amount)
      throws AccountException, TransactionException;

  public LinkedList<String> printTransactions(long accountId);


}
