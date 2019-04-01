package com.kata.bankaccount.ServiceImpl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Date;
import javax.security.auth.login.AccountException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.TransactionType;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.factory.TransactionFactory;
import com.kata.bankaccount.repository.TransactionRepository;
import com.kata.bankaccount.service.impl.AccountServiceImpl;
import com.kata.bankaccount.service.impl.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

  private TransactionServiceImpl transactionServiceImpl;
  @Mock
  TransactionRepository transactionRepo;
  @Mock
  AccountServiceImpl accountServiceImpl;
  @Mock
  TransactionFactory transactionFactory;

  @Rule
  public ExpectedException thrown = ExpectedException.none();


  @Before
  public void initialize() {
    transactionServiceImpl =
        new TransactionServiceImpl(transactionRepo, accountServiceImpl, transactionFactory);
  }

  @Test
  public void should_return_all_transactions() {
    transactionServiceImpl.allTransactions();

    verify(transactionRepo).findAll();
  }

  @Test
  public void should_create_deposit_transaction_after_deposit() throws AccountException {
    long accountId = 1;
    int balance = 100;
    int amount = 100;
    Account account = new Account(accountId, balance);
    Transaction transaction =
        new Transaction(1, new Date(), amount, balance, TransactionType.DEPOSIT);

    when(accountServiceImpl.depositInAccount(accountId, amount)).thenReturn(account);

    when(transactionFactory.instansiateDepositTransaction(account, amount)).thenReturn(transaction);

    transactionServiceImpl.createDepositTransaction(accountId, amount);

    verify(transactionRepo).save(transaction);

  }

  @Test
  public void should_create_withdrawal_transaction_after_withdraw()
      throws AccountException, TransactionException {
    long accountId = 2;
    int balance = 30;
    int amount = -70;
    Account account = new Account(accountId, balance);

    Transaction transaction =
        new Transaction(1, new Date(), amount, balance, TransactionType.DEPOSIT);

    when(accountServiceImpl.withdrawFromAccount(accountId, amount)).thenReturn(account);

    when(transactionFactory.instansiateWithdrawalTransaction(account, amount))
        .thenReturn(transaction);

    transactionServiceImpl.createWithdrawalTransaction(accountId, amount);

    verify(transactionRepo).save(transaction);

  }

}
