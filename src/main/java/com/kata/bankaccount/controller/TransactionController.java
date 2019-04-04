package com.kata.bankaccount.controller;

import javax.security.auth.login.AccountException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kata.bankaccount.dto.TransactionDto;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.mapper.TransactionMapper;
import com.kata.bankaccount.service.TransactionService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

  private TransactionService transactionService;

  @PostMapping("/{accountId}/deposit/{amount}")
  public TransactionDto deposit(@PathVariable long accountId, @PathVariable int amount)
      throws AccountException {
    return TransactionMapper
        .transactionToTransactionDto(transactionService.deposit(accountId, amount));

  }

  @PutMapping("/withdraw/{accountId}/{amount}")
  public TransactionDto withdraw(@PathVariable long accountId, @PathVariable int amount)
      throws AccountException, TransactionException {
    return TransactionMapper
        .transactionToTransactionDto(transactionService.withdraw(accountId, amount));

  }

  @GetMapping("/{accountId}/print")
  public void printStatement(@PathVariable long accountId) throws AccountException {
    transactionService.printStatement(accountId);
  }


}
