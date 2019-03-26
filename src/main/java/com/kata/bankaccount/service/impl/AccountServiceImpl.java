package com.kata.bankaccount.service.impl;

import com.kata.bankaccount.repository.TransactionRepository;
import com.kata.bankaccount.service.AccountService;

public class AccountServiceImpl implements AccountService {

	private TransactionRepository transactionRepo;
	
	public AccountServiceImpl(TransactionRepository transactionRepo) {
		this.transactionRepo = transactionRepo;
	}

	public void deposit(int amount) {
		transactionRepo.deposit(amount);
	}

	public void withdraw(int amount) {
		transactionRepo.withdraw(amount);
	}

	public void printStatement() {		
	}

}
