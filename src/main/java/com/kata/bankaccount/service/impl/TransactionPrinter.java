package com.kata.bankaccount.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.utils.Printer;

@Service
public class TransactionPrinter {

  @Autowired
  private Printer printer;


  public static final String HEADER = "OPERATION || DATE || AMOUNT || BALANCE";

  public void printLines(List<Transaction> transactions) {
    printer.print(HEADER);
    transactions.stream()//
        .map(transaction -> printLine(transaction))//
        .forEach(printer::print);
  }

  private String printLine(Transaction transaction) {
    LocalDate date = transaction.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    StringBuilder sb = new StringBuilder();
    sb.append(transaction.getType())//
        .append(" || ")//
        .append(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))//
        .append(" || ")//
        .append(transaction.getAmount())//
        .append(" || ")//
        .append(transaction.getBalance());
    return sb.toString();
  }

}
