package com.kata.bankaccount.service;


import javax.security.auth.login.AccountException;

import org.springframework.stereotype.Service;

import com.kata.bankaccount.domain.Account;
import com.kata.bankaccount.domain.Transaction;

@Service
public interface AccountService {
	
	public void deposit(Transaction transaction);
	
	public void withdraw(Transaction transaction);
	
	public void printStatement();
	
	public Account findByAccountId(long accountId) throws AccountException;

}
