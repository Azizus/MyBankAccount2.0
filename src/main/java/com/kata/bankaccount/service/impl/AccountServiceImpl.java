package com.kata.bankaccount.service.impl;

import java.util.List;
import javax.security.auth.login.AccountException;
import org.springframework.stereotype.Service;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.repository.AccountRepository;
import com.kata.bankaccount.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {


  private AccountRepository accountRepo;

  public AccountServiceImpl(AccountRepository accountRepo) {
    this.accountRepo = accountRepo;
  }

  public Account depositInAccount(long accountId, int amount) throws AccountException {
    Account account = this.findByAccountId(accountId);
    int newBalance = account.getBalance() + amount;
    account.setBalance(newBalance);
    return accountRepo.save(account);
  }


  @Override
  public Account withdrawFromAccount(long accountId, int amount)
      throws TransactionException, AccountException {

    Account account = this.findByAccountId(accountId);
    if (!this.checkBalance(account.getBalance(), amount))
      throw new TransactionException("Solde insuffisant!");
    int newBalance = account.getBalance() - amount;
    account.setBalance(newBalance);
    return accountRepo.save(account);

  }


  @Override
  public Account findByAccountId(long accountId) throws AccountException {
    Account account = accountRepo.findByAccountId(accountId);
    if (account == null)
      throw new AccountException("Compte non trouv�!");
    return account;
  }


  private boolean checkBalance(int balance, int amount) {
    return balance >= amount;
  }

  @Override
  public List<Account> findAll() {
    return accountRepo.findAll();
  }

  @Override
  public Account save(Account account) {
    return accountRepo.save(account);
  }

  @Override
  public boolean deleteById(long accountId) {
    boolean deleted = false;
    accountRepo.deleteById(accountId);
    if (accountRepo.findByAccountId(accountId) == null)
      deleted = true;
    return deleted;
  }

}
