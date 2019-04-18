package com.kata.bankaccount.cucumber.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import javax.security.auth.login.AccountException;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.repository.AccountRepository;
import com.kata.bankaccount.service.AccountService;
import com.kata.bankaccount.service.TransactionService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Ignore
public class WithdrawalSteps {

  @Autowired
  private AccountRepository accountRepo;
  @Autowired
  private AccountService accountService;
  @Autowired
  private TransactionService transactionService;

  private long accountId;
  private int amount;


  @Given("the ID of the account")
  public void the_ID_of_the_account() {
    accountId = 1L;
  }

  @Given("the amount to withdraw")
  public void the_amount_to_withdraw() {
    amount = 50;
  }

  @When("the account exists")
  public void theAccountExists() throws AccountException {
    accountRepo.findById(accountId);
  }

  @When("balance is sufficient")
  public void balanceIsSufficient() throws AccountException {

    Account account = accountRepo.findById(accountId).get();
    account.hasBalanceAbove(amount);
  }

  @Then("create and save a withdrawal transaction")
  public void create_and_save_a_withdrawal_transaction()
      throws AccountException, TransactionException {

    Transaction transaction = transactionService.withdraw(accountId, amount);

    assertThat(transactionService.allTransactions(accountId)).hasSize(2);
    assertThat(transaction.getAccountId(), is(equalTo(accountId)));
  }

}
