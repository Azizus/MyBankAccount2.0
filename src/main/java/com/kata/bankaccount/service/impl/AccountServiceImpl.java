package com.kata.bankaccount.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.TransactionType;
import com.kata.bankaccount.exceptions.TransactionException;
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

	public void deposit(TransactionType type, Date date, int amount, int balance) {
		transactionRepo.addDeposit(type, new Date(), amount, balance);
	}

	public void withdraw(TransactionType type, Date date, int amount, int balance) throws TransactionException {
		if (this.compare(this.getBalanceOfLastTransaction(transactionRepo.findAll()), amount));
		transactionRepo.addWithdrawal(type, new Date(), amount, balance);
	}

	public void printStatement() {	
		transactionPrinter.printLines(transactionRepo.findAll());
	}
	
	private boolean compare(int balance, int amount) throws TransactionException {
		if (balance >= amount) {
			return true;
		} else
			throw new TransactionException("Solde insuffisant!");
	}
	
	
	
	public int getBalanceOfLastTransaction(List<Transaction> transactions) {
		return transactions.get(transactions.size() - 1 ).getBalance();
	}

}
