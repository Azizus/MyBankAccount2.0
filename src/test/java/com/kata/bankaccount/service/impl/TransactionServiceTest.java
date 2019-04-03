package com.kata.bankaccount.service.impl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
import com.kata.bankaccount.service.TransactionService;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

  private TransactionService transactionService;
  @Mock
  TransactionRepository transactionRepo;
  @Mock
  AccountServiceImpl accountServiceImpl;
  @Mock
  TransactionPrinter transactionPrinter;
  @Mock
  TransactionFactory transactionFactory;

  @Rule
  public ExpectedException thrown = ExpectedException.none();


  @Before
  public void initialize() {
    transactionService = new TransactionServiceImpl(transactionRepo, accountServiceImpl,
        transactionFactory, transactionPrinter);
  }

  @Test
  public void should_return_all_transactions_of_an_account() {
    long accountId = 1;
    transactionService.allTransactions(accountId);

    verify(transactionRepo).findAllByAccountId(accountId);
  }

  @Test
  public void should_create_deposit_transaction_after_deposit() throws AccountException {
    long accountId = 1;
    int balance = 100;
    int amount = 100;
    Date date = new Date();

    Account account = Account.builder().accountId(accountId).balance(balance).build();
    Transaction transaction = Transaction.builder().accountId(accountId).date(date).amount(amount)
        .balance(balance).type(TransactionType.DEPOSIT).build();

    when(accountServiceImpl.depositInAccount(accountId, amount)).thenReturn(account);

    when(transactionFactory.deposit(account, amount)).thenReturn(transaction);

    transactionService.deposit(accountId, amount);

    verify(transactionRepo).save(transaction);

  }

  @Test
  public void should_create_withdrawal_transaction_after_withdraw()
      throws AccountException, TransactionException {
    long accountId = 2;
    int balance = 30;
    int amount = -70;
    Date date = new Date();
    Account account = Account.builder().accountId(accountId).balance(balance).build();

    Transaction transaction = Transaction.builder().accountId(accountId).date(date).amount(amount)
        .balance(balance).type(TransactionType.WITHDRAWAL).build();

    when(accountServiceImpl.withdrawFromAccount(accountId, amount)).thenReturn(account);

    when(transactionFactory.withdraw(account, amount)).thenReturn(transaction);

    transactionService.withdraw(accountId, amount);

    verify(transactionRepo).save(transaction);

  }

  @Test
  public void should_print_statement() throws AccountException {
    long accountId = 1;
    List<Transaction> transactions = Arrays.asList(new Transaction());

    given(transactionRepo.findAllByAccountId(accountId)).willReturn(transactions);

    transactionService.printStatement(accountId);

    verify(transactionPrinter).printLines(transactions);
  }

}
