package com.kata.bankaccount.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.repository.TransactionRepository;

@Service
public class TransactionServiceImpl {

	private TransactionRepository transactionRepo;
	
	public TransactionServiceImpl(TransactionRepository transactionRepo) {
		this.transactionRepo = transactionRepo;
	}

	public List<Transaction> allTransactions() {
		return transactionRepo.findAll();
	}

	public int getBalanceOfLastTransaction(List<Transaction> transactions) {
		return transactions.get(transactions.size() - 1 ).getBalance();
	}
	
	

}
