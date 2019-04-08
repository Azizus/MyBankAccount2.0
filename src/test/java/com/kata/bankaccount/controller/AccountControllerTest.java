package com.kata.bankaccount.controller;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
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
import com.kata.bankaccount.dto.AccountDto;
import com.kata.bankaccount.service.AccountService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

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
  public void should_save_account() {
    given().contentType("application/json").body(account)//
        .when().post("/accounts")//
        .then().statusCode(200)//
        .assertThat().body("accountId", is(equalTo(1))).assertThat()
        .body("balance", is(equalTo(100)));

  }

  @Test
  public void should_return_account_list_containing_one_value() throws Exception {
    accountService.save(account);

    AccountDto[] accountsDto = given().when().get("/accounts").as(AccountDto[].class);

    assertThat(accountsDto).hasSize(1);
    assertThat(accountsDto[0].getAccountId()).isEqualTo(1);
    assertThat(accountsDto[0].getBalance()).isEqualTo(100);
  }

  @Test
  public void should_find_account_by_id() {
    accountService.save(account);
    given().contentType("application/json")//
        .pathParam("accountId", "1")//
        .when().get("/accounts/{accountId}")//
        .then().statusCode(200)//
        .assertThat().body("accountId", is(equalTo(1)))//
        .assertThat().body("balance", is(equalTo(100)));
  }

  @Test
  public void should_not_allow_delete() {
    accountService.save(account);

    given().contentType(ContentType.JSON)//
        .pathParam("accountId", "1")//
        .when().delete("/accounts/{accountId}")//
        .then().statusCode(200);
  }

}
