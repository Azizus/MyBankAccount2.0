package com.kata.bankaccount.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.kata.bankaccount.domain.TransactionType;
import com.kata.bankaccount.exceptions.TransactionException;

@Service
public interface AccountService {
	
	public void deposit(TransactionType type, Date date, int amount, int balance);
	
	public void withdraw(TransactionType type, Date date, int amount, int balance) throws TransactionException;
	
	public void printStatement();

}
