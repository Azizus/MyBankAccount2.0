package com.kata.bankaccount.ServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.BDDMockito.*;


import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.TransactionType;
import com.kata.bankaccount.service.impl.TransactionPrinter;
import com.kata.bankaccount.utils.DateFormat;
import com.kata.bankaccount.utils.Printer;

@RunWith(MockitoJUnitRunner.class)
public class TransactionPrinterTest {

	private TransactionPrinter transactionPrinter;
	@Mock Printer printer;
	
	@Before
	public void initialize(){
		transactionPrinter = new TransactionPrinter(printer);
	}

	@Test
	public void should_print_transactions_with_header() {

		//Date date = new Date();
		 Date date = new GregorianCalendar(2019, Calendar.MARCH, 27).getTime();

		LocalDate localDate = LocalDate.of(2019, 03, 27);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(new Transaction(TransactionType.DEPOSIT, date, 100, 100));
		transactions.add(new Transaction(TransactionType.WITHDRAWAL, date, -70, 30));


		transactionPrinter.printLines(transactions);
		verify(printer).print("OPERATION || DATE || AMOUNT || BALANCE");
		verify(printer).print("DEPOSIT ||"+" " + localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +" " + "|| 100 || 100");
		verify(printer).print("WITHDRAWAL || 27/03/2019 || -70 || 30");

	}

}
