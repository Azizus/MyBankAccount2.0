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
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTest {


  @Autowired
  private AccountService accountService;

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
        .pathParam("accountId", account.getAccountId())//
        .pathParam("amount", depositAmount)//
        .when().post("/accounts/{accountId}/deposit/{amount}")//
        .then().statusCode(200)//
        .assertThat().body("accountId", is(equalTo(1)))//
        .assertThat().body("balance", is(equalTo(200)))//
        .assertThat().body("amount", is(equalTo(100)))//
        .assertThat().body("type", is(equalTo("DEPOSIT")));//
  }

  @Test
  public void should_create_withdraw_transaction() throws AccountException {

    accountService.save(account);
    int withdrawAmount = 70;
    given().contentType("application/json")//
        .pathParam("accountId", account.getAccountId()).pathParam("amount", withdrawAmount).when()
        .post("/accounts/{accountId}/withdraw/{amount}")//
        .then().statusCode(200)//
        .assertThat().body("accountId", is(equalTo(1)))//
        .assertThat().body("balance", is(equalTo(30)))//
        .assertThat().body("amount", is(equalTo(70)))//
        .assertThat().body("type", is(equalTo("WITHDRAWAL")));//

  }
}
