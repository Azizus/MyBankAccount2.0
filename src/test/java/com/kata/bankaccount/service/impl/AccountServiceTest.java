package com.kata.bankaccount.service.impl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import javax.security.auth.login.AccountException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.repository.AccountRepository;
import com.kata.bankaccount.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

  @InjectMocks
  private AccountService accountService = new AccountServiceImpl();
  @Mock
  AccountRepository accountRepo;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void should_deposit_money_in_account_and_create_transaction() throws AccountException {
    int amount = 100;
    long accountId = 1;
    int balance = 0;
    Account account = Account.builder().accountId(accountId).balance(balance).build();

    when(accountRepo.findByAccountId(account.getAccountId())).thenReturn(account);

    accountService.depositInAccount(accountId, amount);

    verify(accountRepo).save(account);

  }

  @Test
  public void should_withdraw_money_when_balance_is_sufficient()
      throws TransactionException, AccountException {
    int amount = -70;
    long accountId = 2;
    int balance = 30;
    Account account = Account.builder().accountId(accountId).balance(balance).build();

    when(accountRepo.findByAccountId(account.getAccountId())).thenReturn(account);

    accountService.withdrawFromAccount(accountId, amount);

    verify(accountRepo).save(account);

  }

  @Test
  public void should_throw_exception_when_balance_unsufficient_to_withdraw()
      throws TransactionException, AccountException {
    int amount = 70;
    long accountId = 1;
    int balance = 50;
    Account account = Account.builder().accountId(accountId).balance(balance).build();

    given(accountRepo.findByAccountId(account.getAccountId())).willReturn(account);

    thrown.expect(TransactionException.class);
    thrown.expectMessage("Solde insuffisant!");
    accountService.withdrawFromAccount(account.getAccountId(), amount);
  }

  @Test
  public void should_thow_exception_when_account_not_found() throws AccountException {
    Account account = new Account();
    thrown.expect(AccountException.class);
    thrown.expectMessage("Compte non trouv�!");
    accountService.findByAccountId(account.getAccountId());
  }

}
