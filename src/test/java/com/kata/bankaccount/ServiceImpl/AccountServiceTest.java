package com.kata.bankaccount.ServiceImpl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
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
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.repository.AccountRepository;
import com.kata.bankaccount.repository.TransactionRepository;
import com.kata.bankaccount.service.impl.AccountServiceImpl;
import com.kata.bankaccount.service.impl.TransactionPrinter;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  private AccountServiceImpl accountServiceImpl;
  @Mock
  TransactionRepository transactionRepo;
  @Mock
  TransactionPrinter transactionPrinter;
  @Mock
  AccountRepository accountRepo;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void initialize() {
    accountServiceImpl = new AccountServiceImpl(transactionRepo, transactionPrinter, accountRepo);
  }

  @Test
  public void should_deposit_money_in_account_and_create_transaction() throws AccountException {
    int amount = 100;
    long accountId = 1;
    int balance = 0;
    Account account = new Account(accountId, balance);

    when(accountRepo.findByAccountId(account.getAccountId())).thenReturn(account);

    accountServiceImpl.depositInAccount(accountId, amount);

    verify(accountRepo).save(account);

  }

  @Test
  public void should_withdraw_money_when_balance_is_sufficient()
      throws TransactionException, AccountException {
    int amount = -70;
    long accountId = 2;
    Account account = new Account(2, 100);

    when(accountRepo.findByAccountId(account.getAccountId())).thenReturn(account);

    accountServiceImpl.withdrawFromAccount(accountId, amount);

    verify(accountRepo).save(account);

  }

  @Test
  public void should_throw_exception_when_balance_unsufficient_to_withdraw()
      throws TransactionException, AccountException {
    int amount = 70;
    Account account = new Account(1, 50);
    given(accountRepo.findByAccountId(account.getAccountId())).willReturn(account);

    thrown.expect(TransactionException.class);
    thrown.expectMessage("Solde insuffisant!");
    accountServiceImpl.withdrawFromAccount(account.getAccountId(), amount);
  }

  @Test
  public void should_thow_exception_when_account_not_found() throws AccountException {
    Account account = new Account();
    thrown.expect(AccountException.class);
    thrown.expectMessage("Compte non trouv�!");
    accountServiceImpl.findByAccountId(account.getAccountId());
  }

  @Test
  public void should_print_statement() {
    Account account = new Account(1, 0);
    List<Transaction> transactions = Arrays.asList(new Transaction());

    given(transactionRepo.findAllByAccountId(account.getAccountId())).willReturn(transactions);

    accountServiceImpl.printStatement(account.getAccountId());

    verify(transactionPrinter).printLines(transactions);
  }
}