package com.kata.bankaccount.controller;

import static io.restassured.RestAssured.given;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

  @Value("${local.server.port}")
  private int port;

  @Before
  public void setUp() throws Exception {
    RestAssured.port = port;
  }

  @Test
  public void should_return_all_accounts() throws Exception {
    given().when().get("/accounts").then().statusCode(200);
  }

}
