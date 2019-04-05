package com.kata.bankaccount.controller;

import java.util.ArrayList;
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


  @PostMapping
  public AccountDto save(@RequestBody Account account) {
    return AccountMapper.accountToAccountDto(accountService.save(account));
  }

  @GetMapping
  public List<AccountDto> find() {
    List<Account> accounts = new ArrayList<Account>();
    accounts = accountService.findAll();
    return AccountMapper.accountListToAccountDtoList(accounts);
  }

  @GetMapping("/{accountId}")
  public AccountDto findById(@PathVariable long accountId) throws AccountException {
    Account account = new Account();
    account = accountService.findByAccountId(accountId);
    return AccountMapper.accountToAccountDto(account);
  }

  @DeleteMapping("/{accountId}")
  public boolean deleteById(@PathVariable long accountId) throws AccountException {
    return accountService.deleteById(accountId);
  }

}
