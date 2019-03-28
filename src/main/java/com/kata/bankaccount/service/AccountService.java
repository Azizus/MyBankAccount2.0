package com.kata.bankaccount.service;


import javax.security.auth.login.AccountException;

import org.springframework.stereotype.Service;

import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.exceptions.TransactionException;

@Service
public interface AccountService {
	
	public void deposit(Transaction transaction);
	
	public void withdraw(Transaction transaction) throws TransactionException;
	
	public void printStatement();
	
	public Account findByAccountId(long accountId) throws AccountException;

}
