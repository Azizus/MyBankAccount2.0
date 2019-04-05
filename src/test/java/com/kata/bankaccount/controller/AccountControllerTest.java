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
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

  @Value("${local.server.port}")
  private int port;

  private Account account = Account.builder().accountId(1).balance(100).build();

  @Autowired
  private AccountService accountService;

  @Before
  public void setUp() throws Exception {
    RestAssured.port = port;
  }

  @Test
  public void should_return_empty_list() throws Exception {
    given().when().get("/accounts")//
        .then().statusCode(200)//
        .assertThat().body("results.size()", is(equalTo(0)));
  }

  @Test
  public void should_save_account() {
    given().contentType("application/json").body(account)//
        .when().post("/accounts")//
        .then().statusCode(200)//
        .assertThat().body("accountId", is(equalTo(1))).assertThat()
        .body("balance", is(equalTo(100)));

  }



}
