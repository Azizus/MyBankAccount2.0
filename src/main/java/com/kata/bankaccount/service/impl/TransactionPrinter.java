package com.kata.bankaccount.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.text.DateFormatter;

import org.springframework.stereotype.Service;

import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.utils.DateFormat;
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
		transactions.stream()
			.map(transaction -> printLine(transaction))
			.collect(Collectors.toCollection(LinkedList::new))
			.iterator()
			.forEachRemaining(printer::print);

				
	}
	
	private String printLine(Transaction transaction) {
		LocalDate date = transaction.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String output = transaction.getType()
				+ " "
				+"||"
				+ " "
				+ date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				+ " "
				+"||"
				+ " "
				+ transaction.getAmount()
				+ " "
				+"||"
				+ " "
				+transaction.getBalance();
		return output;
	}

}
