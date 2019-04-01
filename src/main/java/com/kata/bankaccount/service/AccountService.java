package com.kata.bankaccount.service;


import javax.security.auth.login.AccountException;
import org.springframework.stereotype.Service;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.exceptions.TransactionException;

@Service
public interface AccountService {

  public Account withdrawFromAccount(long accountId, int amount)
      throws TransactionException, AccountException;

  public void printStatement(long accountId);

  public Account findByAccountId(long accountId) throws AccountException;

  public Account depositInAccount(long accountId, int amount) throws AccountException;


}
