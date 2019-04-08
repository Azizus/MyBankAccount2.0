package com.kata.bankaccount.utils;

import org.springframework.stereotype.Service;

@Service
public class Printer {

  public String print(String text) {
    System.out.println(text);
    return text;
  }
}
