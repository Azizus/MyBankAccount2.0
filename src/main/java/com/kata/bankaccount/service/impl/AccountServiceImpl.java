package com.kata.bankaccount.service.impl;

import org.springframework.stereotype.Service;

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

	public void deposit(int amount) {
		transactionRepo.addDeposit(amount);
	}

	public void withdraw(int amount) {
		transactionRepo.addWithdrawal(amount);
	}

	public void printStatement() {	
		transactionPrinter.print(transactionRepo.findAll());
	}

}
