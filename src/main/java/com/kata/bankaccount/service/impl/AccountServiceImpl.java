package com.kata.bankaccount.service.impl;

import javax.security.auth.login.AccountException;

import org.springframework.stereotype.Service;

import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.exceptions.TransactionException;
import com.kata.bankaccount.repository.AccountRepository;
import com.kata.bankaccount.repository.TransactionRepository;
import com.kata.bankaccount.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private TransactionRepository transactionRepo;
	private TransactionPrinter transactionPrinter;
	private AccountRepository accountRepo;

	public AccountServiceImpl(TransactionRepository transactionRepo, TransactionPrinter transactionPrinter, AccountRepository accountRepo) {
		this.transactionRepo = transactionRepo;
		this.transactionPrinter = transactionPrinter;
		this.accountRepo = accountRepo;
	}

	public void deposit(Transaction transaction) {
		 this.updateAccountBalance(transactionRepo.save(transaction));
	}
	
	public void withdraw(Transaction transaction) {
		this.updateAccountBalance(transactionRepo.save(transaction));
	}
	
	private void updateAccountBalance(Transaction transaction) {
		Account account = accountRepo.findByAccountId(transaction.getAccountId());
		account.setBalance(transaction.getBalance());
		accountRepo.save(account);	
	}

	public void printStatement() {	
		transactionPrinter.printLines(transactionRepo.findAll());
	}
	
	public boolean compare(int balance, int amount) throws TransactionException {
		if (balance >= amount) {
			return true;
		} else
			throw new TransactionException("Solde insuffisant!");
	}

	@Override
	public Account findByAccountId(long accountId) throws AccountException {
		Account account = accountRepo.findByAccountId(accountId);
		if (account !=null) {
			return account;
		}
		else {
			throw new AccountException("Compte non trouvé!");
		}	}
	

}
