package com.kata.bankaccount.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.security.auth.login.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.dto.AccountDto;
import com.kata.bankaccount.mapper.AccountMapper;
import com.kata.bankaccount.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  @Autowired
  private AccountService accountService;
  @Autowired
  private AccountMapper accountMapper;


  @PostMapping
  public AccountDto save(@RequestBody Account account) {
    return accountMapper.accountToAccountDto(accountService.save(account));
  }

  @GetMapping
  public List<AccountDto> find() {
    List<Account> accounts = new ArrayList<Account>();
    accounts = accountService.findAll();
    return accountMapper.accountListToAccountDtoList(accounts);
  }

  @GetMapping("/{accountId}")
  public AccountDto findById(@PathVariable long accountId) throws AccountException {
    Account account = accountService.findByAccountId(accountId);
    return accountMapper.accountToAccountDto(account);
  }

  @GetMapping("/{accountId}/print")
  public LinkedList<String> printStatement(@PathVariable long accountId) throws AccountException {
    return accountService.printStatement(accountId);
  }

  @DeleteMapping("/{accountId}")
  public void deleteById(@PathVariable long accountId) throws AccountException {
    accountService.deleteById(accountId);
  }

}
