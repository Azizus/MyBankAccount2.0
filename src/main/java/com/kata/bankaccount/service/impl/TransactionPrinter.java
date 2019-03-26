package com.kata.bankaccount.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.service.TransactionPrinterService;

@Service
public class TransactionPrinter implements TransactionPrinterService{

	public void print(List<Transaction> transactions) {
	}

}
