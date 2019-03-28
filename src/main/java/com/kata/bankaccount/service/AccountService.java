package com.kata.bankaccount.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.kata.bankaccount.domain.Transaction;
import com.kata.bankaccount.domain.TransactionType;

@Service
public interface AccountService {
	
	public void deposit(Transaction transaction);
	
	public void withdraw(Transaction transaction);
	
	public void printStatement();

}
