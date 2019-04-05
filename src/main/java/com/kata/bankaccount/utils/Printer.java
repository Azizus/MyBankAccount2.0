package com.kata.bankaccount.utils;

import org.springframework.stereotype.Service;

@Service
public class Printer {

  public void print(String text) {
    System.out.println(text);
  }
}
