package com.kata.bankaccount.controller;

import javax.security.auth.login.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kata.bankaccount.dto.TransactionDto;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.mapper.TransactionMapper;
import com.kata.bankaccount.service.TransactionService;

@RestController
@RequestMapping("/accounts/{accountId}")
public class TransactionController {

  @Autowired
  private TransactionService transactionService;
  @Autowired
  private TransactionMapper transactionMapper;


  @PostMapping("/deposit/{amount}")
  public TransactionDto deposit(@PathVariable long accountId, @PathVariable int amount)
      throws AccountException {
    return transactionMapper
        .transactionToTransactionDto(transactionService.deposit(accountId, amount));
  }

  @PostMapping("/withdraw/{amount}")
  public TransactionDto withdraw(@PathVariable long accountId, @PathVariable int amount)
      throws AccountException, TransactionException {
    return transactionMapper
        .transactionToTransactionDto(transactionService.withdraw(accountId, amount));
  }
}
