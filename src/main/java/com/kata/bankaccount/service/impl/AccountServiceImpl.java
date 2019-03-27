package com.kata.bankaccount.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.kata.bankaccount.domain.TransactionType;
import com.kata.bankaccount.repository.TransactionRepository;
import com.kata.bankaccount.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private TransactionRepository transactionRepo;
	private TransactionPrinter transactionPrinter;
	
	public AccountServiceImpl(TransactionRepository transactionRepo, TransactionPrinter transactionPrinter) {
		this.transactionRepo = transactionRepo;
		this.transactionPrinter = transactionPrinter;
	}

	public void deposit(int amount, Date date, TransactionType type, int balance) {
		transactionRepo.addDeposit(amount, new Date(), type, balance);
	}

	public void withdraw(int amount, Date date, TransactionType type, int balance) {
		transactionRepo.addWithdrawal(amount, new Date(), type, balance);
	}

	public void printStatement() {	
		transactionPrinter.printLines(transactionRepo.findAll());
	}

}
