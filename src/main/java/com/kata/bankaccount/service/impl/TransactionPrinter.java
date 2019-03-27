package com.kata.bankaccount.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.utils.DateFormat;
import com.kata.bankaccount.utils.Printer;

@Service
public class TransactionPrinter {

	private Printer printer;
	private DateFormat dateFormat;
	
	public TransactionPrinter(Printer printer, DateFormat dateFormat) {
		this.printer = printer;
		this.dateFormat = dateFormat;
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
	
	public String printLine(Transaction transaction) {
		String output = transaction.getType()
				+ " "
				+"||"
				+ " "
				+ dateFormat.formatDateToString(transaction.getDate())
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
