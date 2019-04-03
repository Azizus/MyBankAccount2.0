package com.kata.bankaccount.service.impl;

import java.util.List;
import javax.security.auth.login.AccountException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.factory.TransactionFactory;
import com.kata.bankaccount.repository.TransactionRepository;
import com.kata.bankaccount.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

  private TransactionRepository transactionRepo;
  private AccountServiceImpl accountServiceImpl;
  private TransactionFactory transactionFactory;
  private TransactionPrinter transactionPrinter;

  public TransactionServiceImpl(TransactionRepository transactionRepo,
      AccountServiceImpl accountServiceImpl, TransactionFactory transactionFactory,
      TransactionPrinter transactionPrinter) {
    this.transactionRepo = transactionRepo;
    this.accountServiceImpl = accountServiceImpl;
    this.transactionFactory = transactionFactory;
    this.transactionPrinter = transactionPrinter;
  }

  @Override
  @Transactional
  public Transaction deposit(long accountId, int amount) throws AccountException {
    return transactionRepo.save(transactionFactory.deposit(
        accountServiceImpl.depositInAccount(accountId, amount), amount));
  }

  @Override
  @Transactional
  public Transaction withdraw(long accountId, int amount)
      throws AccountException, TransactionException {
    return transactionRepo.save(transactionFactory.withdraw(
        accountServiceImpl.withdrawFromAccount(accountId, amount), amount));
  }

  @Override
  public void printStatement(long accountId) throws AccountException {
    accountServiceImpl.findByAccountId(accountId);
    transactionPrinter.printLines(this.allTransactions(accountId));
  }

  @Override
  public List<Transaction> allTransactions(long accountId) {
    return transactionRepo.findAllByAccountId(accountId);
  }

}
