package com.kata.bankaccount.cucumber.steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.List;
import javax.security.auth.login.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.repository.AccountRepository;
import com.kata.bankaccount.service.AccountService;
import com.kata.bankaccount.service.TransactionService;
import com.kata.bankaccount.utils.StatementStr;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StatementPrintSteps {

  @Autowired
  private AccountRepository accountRepo;
  @Autowired
  private AccountService accountService;
  @Autowired
  private TransactionService transactionService;

  Account account = Account.builder().accountId(2).balance(0).build();

  long accountId;


  // @Before
  // public void init() throws AccountException, TransactionException {
  // account = accountService.save(account);
  // transactionService.deposit(account.getAccountId(), 100);
  // transactionService.deposit(account.getAccountId(), 50);
  // transactionService.withdraw(account.getAccountId(), 20);
  // }

  @Given("ID of the Account")
  public void id_of_the_Account() {
    account = accountService.save(account);
    accountId = account.getAccountId();
  }

  @When("account exists")
  public void account_exists() throws AccountException {
    accountRepo.findById(accountId);
  }

  @When("has transactions")
  public void has_transactions() throws AccountException, TransactionException {
    transactionService.deposit(accountId, 100);
    transactionService.withdraw(accountId, 40);
    if (!transactionService.allTransactions(accountId).isEmpty())
      ;
  }

  @Then("print list of transactions with a header")
  public void print_list_of_transactions_with_a_header() throws AccountException {

    List<String> output = accountService.printStatement(accountId);

    assertThat(output.get(0), is(equalTo(StatementStr.HEADER)));
    assertThat(output.get(1), is(equalTo(StatementStr.accountInformation(accountId))));
    assertThat(output.size(), is(equalTo(4)));

  }
}
