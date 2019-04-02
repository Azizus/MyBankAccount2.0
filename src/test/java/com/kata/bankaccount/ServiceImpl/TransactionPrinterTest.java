package com.kata.bankaccount.ServiceImpl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.security.auth.login.AccountException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.TransactionType;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.service.impl.AccountServiceImpl;
import com.kata.bankaccount.service.impl.TransactionPrinter;
import com.kata.bankaccount.service.impl.TransactionServiceImpl;
import com.kata.bankaccount.utils.Printer;

@RunWith(MockitoJUnitRunner.class)
public class TransactionPrinterTest {

  private TransactionPrinter transactionPrinter;
  @Mock
  Printer printer;
  @Mock
  AccountServiceImpl accountServiceImpl;
  @Mock
  TransactionServiceImpl transactionServiceImpl;

  @Before
  public void initialize() {
    transactionPrinter = new TransactionPrinter(printer);
  }

  @Test
  public void should_print_transactions_with_header()
      throws AccountException, TransactionException {

    long accountId = 1;
    int balance = 0;
    int depositAmount = 100;
    int withdrawAmount = -70;
    LocalDate localDate = LocalDate.of(2019, 03, 27);
    Date date = new GregorianCalendar(2019, Calendar.MARCH, 27).getTime();

    Account account = Account.builder().accountId(accountId).balance(balance).build();
    List<Transaction> transactions = new ArrayList<Transaction>();

    transactions.add(Transaction.builder().accountId(accountId).date(date).amount(depositAmount)
        .balance(account.getBalance() + depositAmount).type(TransactionType.DEPOSIT).build());
    when(accountServiceImpl.depositInAccount(accountId, depositAmount)).thenReturn(account);
    transactions.add(Transaction.builder().accountId(accountId).date(date).amount(withdrawAmount)
        .balance(account.getBalance() + depositAmount + withdrawAmount)
        .type(TransactionType.WITHDRAWAL).build());


    transactionPrinter.printLines(transactions);
    verify(printer).print("OPERATION || DATE || AMOUNT || BALANCE");
    verify(printer).print("DEPOSIT ||" + " "
        + localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " + "|| 100 || 100");
    verify(printer).print("WITHDRAWAL || 27/03/2019 || -70 || 30");

  }

}
