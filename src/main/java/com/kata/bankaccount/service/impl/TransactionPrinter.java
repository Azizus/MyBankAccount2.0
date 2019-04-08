package com.kata.bankaccount.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.utils.Printer;
import com.kata.bankaccount.utils.StatementStr;

@Component
public class TransactionPrinter {

  @Autowired
  private Printer printer;

  public List<String> printLines(List<Transaction> transactions) {
    List<String> toPrint = new ArrayList<String>(Arrays.asList(new String[] {//
        printer.print(StatementStr.HEADER), //
        printer.print(StatementStr.accountInformation(transactions.get(0).getAccountId()))}));

    toPrint.addAll(transactions.stream()//
        .map(transaction -> printLine(transaction))//
        .collect(Collectors.toList()));//

    return toPrint;
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
    return printer.print(sb.toString());
  }

}
