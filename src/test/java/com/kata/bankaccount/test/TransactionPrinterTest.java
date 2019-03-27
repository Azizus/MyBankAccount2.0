package com.kata.bankaccount.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.TransactionType;
import com.kata.bankaccount.service.impl.TransactionPrinter;
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
	public void should_print_transaction_with_header() {
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(new Transaction(TransactionType.DEPOSIT, new Date(), 100, 100));
		transactions.add(new Transaction(TransactionType.WITHDRAWAL, new Date(), 70, 30));

		transactionPrinter.printLines(transactions);
		verify(printer).print("OPERATION || DATE || AMOUNT || BALANCE");
		verify(printer).print("DEPOSIT || 27/03/2019 || 100 || 100");
		verify(printer).print("WITHDRAWAL || 27/03/2019 || -70 || 30");



	}

}
