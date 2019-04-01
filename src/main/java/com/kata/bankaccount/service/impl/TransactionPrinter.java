package com.kata.bankaccount.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.utils.Printer;

@Service
public class TransactionPrinter {

  private Printer printer;

  public TransactionPrinter(Printer printer) {
    this.printer = printer;
  }

  public static final String header = "OPERATION || DATE || AMOUNT || BALANCE";


  public void printLines(List<Transaction> transactions) {
    printer.print(header);
    transactions.stream().map(transaction -> printLine(transaction))
        .collect(Collectors.toCollection(LinkedList::new)).iterator()
        .forEachRemaining(printer::print);


  }

  private String printLine(Transaction transaction) {
    LocalDate date = transaction.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    StringBuilder sb = new StringBuilder();
    sb.append(transaction.getType()).append(" ").append("||").append(" ")
        .append(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append(" ").append("||")
        .append(" ").append(transaction.getAmount()).append(" ").append("||").append(" ")
        .append(transaction.getBalance());

    return sb.toString();
  }

}
