package com.kata.bankaccount.service.impl;

import java.util.List;
import javax.security.auth.login.AccountException;
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

  public TransactionServiceImpl(TransactionRepository transactionRepo,
      AccountServiceImpl accountServiceImpl, TransactionFactory transactionFactory) {
    this.transactionRepo = transactionRepo;
    this.accountServiceImpl = accountServiceImpl;
    this.transactionFactory = transactionFactory;
  }

  @Override
  public Transaction createDepositTransaction(long accountId, int amount) throws AccountException {
    return transactionRepo.save(transactionFactory.instansiateDepositTransaction(
        accountServiceImpl.depositInAccount(accountId, amount), amount));
  }

  @Override
  public Transaction createWithdrawalTransaction(long accountId, int amount)
      throws AccountException, TransactionException {
    return transactionRepo.save(transactionFactory.instansiateWithdrawalTransaction(
        accountServiceImpl.withdrawFromAccount(accountId, amount), amount));
  }

  @Override
  public List<Transaction> allTransactions() {
    return transactionRepo.findAll();
  }

}
