package com.kata.bankaccount.cucumber.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import javax.security.auth.login.AccountException;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.repository.AccountRepository;
import com.kata.bankaccount.service.AccountService;
import com.kata.bankaccount.service.TransactionService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DepositSteps {

  @Autowired
  private AccountRepository accountRepo;
  @Autowired
  private AccountService accountService;
  @Autowired
  private TransactionService transactionService;

  private Account account = Account.builder().accountId(1).balance(0).build();

  private long accountId;
  private int amount;

  @Before
  public void init() {
    account = accountRepo.save(account);
  }

  @Given("the ID of the Account")
  public void the_ID_of_the_Account() {
    accountId = account.getAccountId();
  }

  @Given("the amount of deposit")
  public void the_amount_of_deposit() {
    amount = 100;
  }

  @When("account exists And it's successfully updated")
  public void account_exists_And_it_s_successfully_updated() throws AccountException {

    if (accountRepo.existsById(accountId))
      accountService.depositInAccount(accountId, amount);

  }

  @Then("create and save a deposit transaction")
  public void create_and_save_a_deposit_transaction() throws AccountException {
    Transaction transaction = transactionService.deposit(accountId, amount);

    assertThat(transactionService.allTransactions(accountId)).hasSize(1);
    assertThat(transaction.getAccountId(), is(equalTo(accountId)));

  }
}
