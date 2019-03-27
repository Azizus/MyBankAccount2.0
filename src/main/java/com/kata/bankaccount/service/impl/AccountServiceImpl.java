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
	private TransactionServiceImpl transactionServiceImpl;
	
	public AccountServiceImpl(TransactionRepository transactionRepo, TransactionPrinter transactionPrinter, TransactionServiceImpl transactionServiceImpl) {
		this.transactionRepo = transactionRepo;
		this.transactionPrinter = transactionPrinter;
		this.transactionServiceImpl = transactionServiceImpl;
	}

	public void deposit(TransactionType type, Date date, int amount, int balance) {
		//balance = balance + transactionServiceImpl.getBalanceOfLastTransaction(transactionRepo.findAll());
		transactionRepo.addDeposit(type, new Date(), amount, balance);
	}

	public void withdraw(TransactionType type, Date date, int amount, int balance) {
		transactionRepo.addWithdrawal(type, new Date(), amount, balance);
	}

	public void printStatement() {	
		transactionPrinter.printLines(transactionRepo.findAll());
	}

}
