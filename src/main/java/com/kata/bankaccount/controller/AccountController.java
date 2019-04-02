package com.kata.bankaccount.controller;

import java.util.ArrayList;
import java.util.List;
import javax.security.auth.login.AccountException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.service.AccountService;
import com.kata.bankaccount.service.TransactionService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private AccountService accountService;
  private TransactionService transactionService;

  @PostMapping
  public Account save(@RequestBody Account account) {
    return accountService.save(account);
  }

  @GetMapping
  public List<Account> find() {
    List<Account> accounts = new ArrayList<Account>();
    accounts = accountService.findAll();
    return accounts;
  }


  @GetMapping("/{accountId}")
  public Account findById(@PathVariable long accountId) throws AccountException {
    Account account = new Account();
    account = accountService.findByAccountId(accountId);
    return account;
  }

  @DeleteMapping("/{accountId}")
  public boolean deleteById(@PathVariable long accountId) {
    return accountService.deleteById(accountId);
  }

  @PostMapping("/{accountId}/deposit/{amount}")
  public Transaction deposit(@PathVariable long accountId, @PathVariable int amount)
      throws AccountException {
    return transactionService.createDepositTransaction(accountId, amount);

  }

  @PutMapping("/withdraw/{accountId}/{amount}")
  public Transaction withdraw(@PathVariable long accountId, @PathVariable int amount)
      throws AccountException, TransactionException {
    return transactionService.createWithdrawalTransaction(accountId, amount);

  }

  @GetMapping("/{accountId}/print")
  public void printStatement(@PathVariable long accountId) throws AccountException {
    accountService.printStatement(accountId);
  }


}
