package com.kata.bankaccount.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import javax.security.auth.login.AccountException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.service.AccountService;
import com.kata.bankaccount.service.TransactionService;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTest {


  @Autowired
  private AccountService accountService;
  @Autowired
  private TransactionService transactionService;

  @Value("${local.server.port}")
  private int port;

  private Account account;


  @Before
  public void setUp() throws Exception {
    account = Account.builder().accountId(1).balance(100).build();
    RestAssured.port = port;
  }

  @Test
  public void should_create_deposit_transaction() {
    accountService.save(account);

    int depositAmount = 100;
    given().contentType("application/json")//
        .when().post("/transactions/" + account.getAccountId() + "/deposit/" + depositAmount + "")//
        .then().statusCode(200)//
        .assertThat().body("accountId", is(equalTo(1)))//
        .assertThat().body("balance", is(equalTo(200)))//
        .assertThat().body("amount", is(equalTo(100)))//
        .assertThat().body("type", is(equalTo("DEPOSIT")));//

  }

  @Test
  public void should_create_withdraw_transaction() throws AccountException {


    // Account account2 = new Account(2, 100);
    accountService.save(account);
    // long account2Id = account2.getAccountId();
    int withdrawAmount = 70;
    given().contentType("application/json")//
        .when().post("/transactions/" + account.getAccountId() + "/withdraw/" + withdrawAmount + "")//
        .then().statusCode(200)//
        .assertThat().body("accountId", is(equalTo(1)))//
        .assertThat().body("balance", is(equalTo(30)))//
        .assertThat().body("amount", is(equalTo(70)))//
        .assertThat().body("type", is(equalTo("WITHDRAWAL")));//

  }


  // @Test
  // public void should_print_statement_of_account() throws AccountException, TransactionException {
  // accountService.save(account);
  // transactionService.deposit(account.getAccountId(), 100);
  // transactionService.withdraw(account.getAccountId(), 50);
  // transactionService.deposit(account.getAccountId(), 200);
  //
  // given().contentType("application/json")//
  // .when().post("/transactions/" + account.getAccountId() + "")//
  // .then().statusCode(200);//
  // // .assertThat().body("accountId", is(equalTo(1)))//
  // // .assertThat().body("balance", is(equalTo(30)))//
  // // .assertThat().body("amount", is(equalTo(70)))//
  // // .assertThat().body("type", is(equalTo("WITHDRAWAL")));//
  // }
}
