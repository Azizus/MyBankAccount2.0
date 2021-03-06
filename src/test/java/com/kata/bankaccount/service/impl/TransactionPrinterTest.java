package com.kata.bankaccount.service.impl;

import static org.mockito.Mockito.verify;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.security.auth.login.AccountException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.TransactionType;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.utils.Printer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionPrinterTest {

  @InjectMocks
  private TransactionPrinter transactionPrinter = new TransactionPrinter();
  @Mock
  Printer printer;
  @Mock
  TransactionServiceImpl transactionServiceImpl;

  @Test
  public void should_print_transactions_with_header()
      throws AccountException, TransactionException {

    long accountId = 1;
    int balance = 0;
    int depositAmount = 100;
    int withdrawAmount = -70;
    LocalDate localDate = LocalDate.of(2019, 03, 27);
    String dateToString = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    Date date = new GregorianCalendar(2019, Calendar.MARCH, 27).getTime();

    List<Transaction> transactions = new ArrayList<Transaction>();

    transactions.add(Transaction.builder().accountId(accountId).date(date).amount(depositAmount)
        .balance(balance + depositAmount).type(TransactionType.DEPOSIT).build());
    transactions.add(Transaction.builder().accountId(accountId).date(date).amount(withdrawAmount)
        .balance(balance + depositAmount + withdrawAmount).type(TransactionType.WITHDRAWAL)
        .build());


    transactionPrinter.printLines(transactions);
    verify(printer).print("OPERATION || DATE || AMOUNT || BALANCE");
    verify(printer).print("DEPOSIT ||" + " " + dateToString + " " + "|| 100 || 100");
    verify(printer).print("WITHDRAWAL || 27/03/2019 || -70 || 30");

  }

}
