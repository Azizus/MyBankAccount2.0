package com.kata.bankaccount.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
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

// @RunWith(SpringRunner.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTest {

  // @Autowired
  // private TransactionMapper transactionMapper;
  @Autowired
  private AccountService accountService;
  @Autowired
  private TransactionService transactionService;

  @Value("${local.server.port}")
  private int port;

  private Account account = Account.builder().accountId(1).balance(0).build();

  @Before
  public void setUp() throws Exception {
    RestAssured.port = port;
  }

  @Test
  public void should_create_deposit_transaction() {

    accountService.save(account);
    long accountId = account.getAccountId();
    int amount = 100;
    given().contentType("application/json")//
        .when().post("/transactions/" + accountId + "/deposit/" + amount + "")//
        .then().statusCode(200)//
        .assertThat().body("accountId", is(equalTo(1)))//
        .assertThat().body("balance", is(equalTo(100)))//
        .assertThat().body("amount", is(equalTo(100)))//
        .assertThat().body("type", is(equalTo("DEPOSIT")));//

  }

}
