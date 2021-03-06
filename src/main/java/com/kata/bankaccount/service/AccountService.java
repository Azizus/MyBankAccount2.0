package com.kata.bankaccount.service;


import java.util.LinkedList;
import java.util.List;
import javax.security.auth.login.AccountException;
import org.springframework.stereotype.Service;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.exceptions.TransactionException;

@Service
public interface AccountService {

  Account withdrawFromAccount(long accountId, int amount)
      throws TransactionException, AccountException;

  Account findById(long accountId) throws AccountException;

  Account depositInAccount(long accountId, int amount) throws AccountException;

  List<Account> findAll();

  Account save(Account account);

  void deleteById(long accountId) throws AccountException;

  LinkedList<String> printStatement(long accountId) throws AccountException;

}
